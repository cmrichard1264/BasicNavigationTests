package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.StringUtility;


public class HomeworkForBasicNavigation_1to8 {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }



    @Test(description = "Following message should be displayed: " +
            "“Thank you for signing up. Click the button below to return to the home page.” " +
            "Home button should be displayed.")
    public void test1(){
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("Madina Turtbayeva");
        driver.findElement(By.name("email")).sendKeys("arichard2020@gmail.com");
        driver.findElement(By.name("wooden_spoon")).click();
        String expectedResult = "Thank you for signing up. Click the button " +
                "below to return to the home page.";
        String actualResult = driver.findElement(By.name("signup_message")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
        WebElement home = driver.findElement(By.id("wooden_spoon"));
        //Home button should be displayed.
        System.out.println("Home button should be displayed: "+home.isDisplayed());



    }

    @Test(description = "Verify that number of listed examples is equals to 48")
    public void test2() {
    int actualElements = driver.findElements(By.xpath("//li [@class='list-group-item']")).size();
    int expectedElements = 48;
    if(actualElements==expectedElements){
        System.out.println("Passed!");
        System.out.println("Number of listed examples are: "+actualElements);
    }else{
        System.out.println("Failed!");
    }
    }

    @Test(description = "Verify that result message is displayed: “Clicked on button one!”")
    public void test3(){
        driver.findElement(By.xpath("//a[@href='/multiple_buttons']")).click();
        driver.findElement(By.xpath("//*[text()='Button 1']")).click();
        String expectedResult = "Clicked on button one!";
        String actualResult = driver.findElement(By.cssSelector("#result")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
    }

    @Test(description = "Verify that warning message is displayed: " +
            "“first name can only consist of alphabetical letters”")
    public void test4(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("firstname")).sendKeys("123");
        String expectedResult = "first name can only consist of alphabetical letters";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[3]")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
    }

    @Test(description = "“The last name can only consist of alphabetical letters and dash”")
    public void test5(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("lastname")).sendKeys("123");
        String expectedResult = "The last name can only consist of alphabetical letters and dash";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[3]")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
    }

    @Test(description = "Verify that warning message is displayed: " +
            "“The username must be more than 6 and less than 30 characters long”")
    public void test6(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("username")).sendKeys("user");
        String expectedResult = "The username must be more than 6 and less than 30 characters long";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[3]/div/small[2]")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
    }

    @Test(description = "Verify that warning message is displayed: " +
            "“email address is not a validEmail format is not correct”")
    public void test7(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("email")).sendKeys("testers@email.com");
        String expectedResult1 = "email address is not a valid";
        String actualResult1 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[4]/div/small[2]")).getText();
        StringUtility.verifyEquals(expectedResult1, actualResult1);
        String expectedResult2 = "Email format is not correct";
        String actualResult2 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[4]/div/small[3]")).getText();
        StringUtility.verifyEquals(expectedResult2, actualResult2);
    }

    @Test(description = "Verify that warning message is displayed: “Phone format is not correct”")
    public void test8(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("phone")).sendKeys("5711234354");
        String expectedResult = "Phone format is not correct";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[6]/div/small[2]")).getText();
        StringUtility.verifyEquals(expectedResult, actualResult);
    }


    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}
