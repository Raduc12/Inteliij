package SeleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class SeleniumHelper {

    protected WebDriver driver;
    protected String url = "http://lib.academiatestarii.ro/";


    @BeforeMethod
    public void driverStart() {

        //System.setProperty("webdriver.chrome.driver", "D:\\Testare software\\chromedriver-win64\\chromedriver.exe");
        System.setProperty
                ("webdriver.gecko.driver", "D:\\Testare software\\geckodriver-v0.33.0-win64\\geckodriver.exe");

        driver = new FirefoxDriver();
        //driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void driverStop(){

        if (driver != null) {
            driver.quit();
        }

    }
}

