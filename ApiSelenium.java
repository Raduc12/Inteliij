package ApiAndSelenium;

import SeleniumTests.SeleniumHelper;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class ApiSelenium extends SeleniumHelper {
    public ApiSelenium(WebDriver driver) {
        this.driver = driver;
    }
    String username = "Dragos";
    String name = "Dragos";

    public void newUserApiCall(String generatedEmail, String password) {
        given()
                .baseUri("http://104.199.88.84/api/v1/users/signup")
                .contentType("application/json")
                .body("{\n" +
                        "    \"username\": \"" + username + "\",\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"email\": \"" + generatedEmail + "\",\n" +
                        "    \"password\": \"" + password + "\",\n" +
                        "    \"password_confirmation\": \"" + password + "\",\n" +
                        "}")
                .post()
                .getBody().prettyPrint();
    }
}
