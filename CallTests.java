package CallTestsMethods;

import ApiAndSelenium.ApiSelenium;
import SeleniumTests.LoginRegisterAuthors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CallTests {


    //Generates User Details
    static String user = "Dragos";
    static String generatedEmail = generateEmail();
    static String password = "Parola123";

    public static String generateEmail() {
        return user + generateNumber() + "@mm.zz";
    }

    public static int generateNumber() {
        return new Random().nextInt(9999);
    }

    //Generates random data for the Author
    static String firstNameAuthor = generateUserName();
    static String lastNameAuthor = generateLastName();

    static int idAuthor = new Random().nextInt(9999);
    String idAuthorStr = String.valueOf(idAuthor);

    public static String generateUserName() {
        return "Author" + generateNumber();
    }

    public static String generateLastName() {
        return "AuthorLast" + generateNumber();
    }

    //Author name
    String generatedAuthor = generatedAuthorAccount();

    public static String generatedAuthorAccount() {
        return firstNameAuthor + " " + lastNameAuthor;
    }

    @Test
    public void testMethods() throws InterruptedException {
        WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver object
        LoginRegisterAuthors loginRegisterAuthors = new LoginRegisterAuthors(driver); // Pass the WebDriver object

        loginRegisterAuthors.createNewAccount(generatedEmail, password);
        Thread.sleep(2000);
        loginRegisterAuthors.userlogin(generatedEmail, password, firstNameAuthor, lastNameAuthor, idAuthorStr,
                generatedAuthor);

    }

    @Test
    public void testMethods2() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        ApiSelenium apiSelenium = new ApiSelenium(driver);
        apiSelenium.newUserApiCall(generatedEmail, password);


    }
}






