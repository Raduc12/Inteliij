package SeleniumTests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class LoginRegisterAuthors extends SeleniumHelper {
    public LoginRegisterAuthors(WebDriver driver) {
        this.driver = driver;
    }

    //TEST - Register User
    public void createNewAccount(String generatedEmail, String password) throws InterruptedException {
        driver.get(url);
        //Click on register button
        WebElement registerButton = driver.findElement(By.id("register"));
        registerButton.click();
        //Input UserName Details
        WebElement userName = driver.findElement(By.id("register_username"));
        userName.sendKeys("Dragos");
        WebElement name = driver.findElement(By.id("register_name"));
        name.sendKeys("Dragos");
        WebElement userEmail = driver.findElement(By.id("register_email"));
        userEmail.sendKeys(generatedEmail);
        System.out.println(generatedEmail);
        WebElement userPassword = driver.findElement(By.id("register_password"));
        userPassword.sendKeys(password);
        WebElement confirmPassword = driver.findElement(By.id("register_password_confirmed"));
        confirmPassword.sendKeys(password);
        //Click on register button
        WebElement clickRegister = driver.findElement(By.xpath("//*[@id='register']/span"));
        clickRegister.click();
        Thread.sleep(2000);
        //Successful registration assert on URL
        String urlRegistration = driver.getCurrentUrl();
        Assert.assertEquals("http://lib.academiatestarii.ro/dashboard", urlRegistration);


    }

    //TEST - User Login
    public void userlogin(String generatedEmail, String password, String firstNameAuthor, String lastNameAuthor, String idAuthorStr, String generatedAuthor) throws InterruptedException {
        driver.get(url);
        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();
        Thread.sleep(2000);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        WebElement loginEmail = driver.findElement(By.id("login_email"));
        loginEmail.sendKeys(generatedEmail);
        System.out.println(generatedEmail);
        WebElement loginPassword = driver.findElement(By.id("login_password"));
        loginPassword.sendKeys(password);
        WebElement clickLogin = driver.findElement(By.xpath("//*[@id='login']/span"));
        clickLogin.click();
        Thread.sleep(2000);
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals("http://lib.academiatestarii.ro/dashboard", urlLogin);
        Thread.sleep(2000);

        //TEST - Create Authors
        WebElement authorsClick = driver.findElement(By.id("authors"));
        authorsClick.click();
        Thread.sleep(2000);
        String urlAuthors = driver.getCurrentUrl();
        Assert.assertEquals("http://lib.academiatestarii.ro/Dragos/authors", urlAuthors);
        WebElement authorFirst = driver.findElement(By.id("create_firstname"));
        authorFirst.sendKeys(firstNameAuthor);
        WebElement authorLast = driver.findElement(By.id("create_lastname"));
        authorLast.sendKeys(lastNameAuthor);
        WebElement createId = driver.findElement(By.id("create_id"));
        createId.sendKeys(idAuthorStr);
        WebElement createAuthor = driver.findElement(By.id("create_author"));
        createAuthor.click();
        Thread.sleep(2000);
        //Assert the author creation
        /*WebElement authorCreated = driver.findElement(By.xpath(
                "//*[@class='mat-list-text']//h4/a)[last()]"));
        String randomAuthor = authorCreated.getText();
        System.out.println(authorCreated);
        Assert.assertEquals(randomAuthor, generatedAuthor);*/

        //TEST - Create Book
        WebElement goToDashboard = driver.findElement(By.id("dashboard"));
        goToDashboard.click();
        Thread.sleep(2000);
        WebElement goToBooks = driver.findElement(By.id("books"));
        goToBooks.click();
        Thread.sleep(2000);
        WebElement bookName = driver.findElement(By.id("create_name"));
        bookName.sendKeys("Book");
        Select select = new Select(driver.findElement(By.id("select_authors")));
        WebElement selectUser2 = driver.findElement(By.xpath("//*[@id='select_authors']/option"));
        select.selectByValue("9807");
        System.out.println(selectUser2);
        WebElement numberOfBooks = driver.findElement(By.id("create_total"));
        numberOfBooks.sendKeys("2");
        WebElement numberOfAvailable = driver.findElement(By.id("create_available"));
        numberOfAvailable.sendKeys("2");
        WebElement bookId = driver.findElement(By.id("create_id"));
        bookId.sendKeys("12345");
        WebElement createBook = driver.findElement(By.id("create_book"));
        createBook.click();
        //Lend Book
        WebElement clickLendBook = driver.findElement(By.xpath("//*[@id='edit_12345']/mat-icon"));
        clickLendBook.click();
        Thread.sleep(2000);
        Select select3 = new Select(driver.findElement(By.xpath("//*[@id='select_lender']"))); // Find the select element
        select3.selectByVisibleText("Dragos_tester1");
        WebElement clickLendButton = driver.findElement(By.id("lend_book"));
        clickLendButton.click();
        Thread.sleep(2000);
        //8. Verifying if the book can be lended
        Alert alert = driver.switchTo().alert();
        String alertMessage = driver.switchTo().alert().getText(); // capture alert message
        alert.accept();
        System.out.println(alertMessage);
        Assert.assertEquals(alertMessage, "Book was loaned");


        //TEST - CreateEmptyBook Test
        //In the same class "BookTest" create a new @Test
        //Login in the application with Selenium
        //Go to Books
        WebElement backToBooks = driver.findElement(By.id("dashboard"));
        backToBooks.click();
        Thread.sleep(2000);
        WebElement clickOnBooks = driver.findElement(By.id("books"));
        clickOnBooks.click();
        Thread.sleep(2000);
        Select select1 = new Select(driver.findElement(By.id("select_authors")));
        //WebElement selectUser = driver.findElement(By.xpath("//*[@id='select_authors']/option"));
        select1.selectByValue("9807");
        //Try to create a book while having only the author selected (the other fields are empty)
        //Verify the book cannot be created (hint: fields go red)
        WebElement createEmptyBook = driver.findElement(By.id("create_book"));
        createEmptyBook.click();
        Thread.sleep(2000);
        WebElement emptyField = driver.findElement(By.id("create_name"));
        String messageEmpty = emptyField.getText();
        System.out.println(messageEmpty);
        //Verifying that the book cannot be created hint fields go red
        Assert.assertEquals(messageEmpty, "");

        //In the same class "BookTest" create a new @Test
        //Create a new book
        WebElement bookName3 = driver.findElement(By.id("create_name"));
        bookName3.sendKeys("Book");
        Select select2 = new Select(driver.findElement(By.id("select_authors")));
        WebElement selectUser = driver.findElement(By.xpath("//*[@id='select_authors']/option"));
        select2.selectByValue("9807");
        System.out.println(selectUser);
        WebElement numberOfBooks3 = driver.findElement(By.id("create_total"));
        numberOfBooks3.sendKeys("2");
        WebElement numberOfAvailable3 = driver.findElement(By.id("create_available"));
        numberOfAvailable3.sendKeys("2");
        WebElement bookId3 = driver.findElement(By.id("create_id"));
        bookId3.sendKeys("12345");
        WebElement createBook3 = driver.findElement(By.id("create_book"));
        createBook3.click();

        //Verify the book can be deleted
        List<WebElement> elementsList = driver.findElements(By.xpath("//*[@class='mat-list-text']/h4"));
        int numberofElements = elementsList.size();
        //List of buttons
        System.out.println(numberofElements);
        //Parcurgere lista de butoane pentru a sterge cartea
        //SE FOLOSESC TABELE? SAU PUN SELECTOR O CLASA DE MAI SUS?
        if (numberofElements > 0) {
            for (int i = numberofElements - 1; i >= 0; i--) {
                WebElement currentElemenet = elementsList.get(i);
                System.out.println(currentElemenet);

                if (i == numberofElements - 1) {
                    WebElement buttonDelete = driver.findElement(By.xpath(("//*[@style='color:red']")));
                    buttonDelete.click();
                    break;

                }


            }
        } else {
            System.out.println("No more elements");
        }
        Thread.sleep(2000);
        //Asserting that the book was deleted successfully
        Alert alertDeleteBookAccept = driver.switchTo().alert();
        String alertDeleteBook = driver.switchTo().alert().getText(); // capture alert message
        alertDeleteBookAccept.accept();
        System.out.println(alertDeleteBook);
        Assert.assertEquals(alertDeleteBook, "Book was deleted");


        //In the same class "BookTest" create a new Test
        //Create a new user using the API call

        //Create a new author via Selenium
        //Create a new book via Selenium
        //Edit the book created at step 4.
        //Verify the update is present in the application through Selenium



    }




}

