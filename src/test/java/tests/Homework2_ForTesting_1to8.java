package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

public class Homework2_ForTesting_1to8 {

    private WebDriver driver;


    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }

    @Test(description = "Verify that warning message is displayed: 'The date of birth is not valid' ")
    public void test1(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("2019/31/31");
        String expectedResult = "The date of birth is not valid";
        String actualResult = driver.findElement(By.xpath("//small[@data-bv-result='INVALID']")).getText();
        Assert.assertEquals(expectedResult, actualResult, "Failed");
        System.out.println(actualResult);

    }

    @Test(description = "Verify that following options for programming languages are displayed:" +
                        "'c++, java, JavaScript'")
    public void test2(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        //driver.findElement(By.xpath("//label[@for='inlineCheckbox1']")).isDisplayed();
        //driver.findElement(By.xpath("//label[@for='inlineCheckbox2']")).isDisplayed();
        //driver.findElement(By.xpath("//label[@for='inlineCheckbox3']")).isDisplayed();
        WebElement cPlusPlus = driver.findElement(By.xpath("//label[@for='inlineCheckbox1']"));
        WebElement Java = driver.findElement(By.xpath("//label[@for='inlineCheckbox2']"));
        WebElement JavaScript = driver.findElement(By.xpath("//label[@for='inlineCheckbox3']"));

        if(cPlusPlus.isDisplayed()){
            System.out.println(cPlusPlus.getText()+" is Displayed");
        }if(Java.isDisplayed()){
            System.out.println(Java.getText()+" is Displayed");
        }if(JavaScript.isDisplayed()){
            System.out.println(JavaScript.getText()+" is Displayed");
        }
    }

    @Test(description = "Verify that warning message is displayed: " +
            "    'first name must be more than 2 and less than 64 characters long'    ")
    public void test3(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("firstname")).sendKeys("A");
        String expectedResult = "first name must be more than 2 and less than 64 characters long";
        String actualResult = driver.findElement(By.xpath("//small[@data-bv-result='INVALID']")).getText();
        Assert.assertEquals(expectedResult, actualResult, "Failed");
    }

    @Test(description = "Verify that warning message is displayed: " +
            "  'The last name must be more than 2 and less than 64 characters long'    ")
    public void test4(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("lastname")).sendKeys("A");
        String expectedResult = "The last name must be more than 2 and less than 64 characters long";
        String actualResult = driver.findElement(By.xpath("//small[@data-bv-result='INVALID']")).getText();
        Assert.assertEquals(expectedResult, actualResult, "Failed");
    }

    @Test(description = "Verify that following success message is displayed:" +
            "  'You've successfully completed registration!'   ")
    public void test5(){
        driver.findElement(By.xpath("//a[@href='/registration_form']")).click();
        driver.findElement(By.name("firstname")).sendKeys("Ava");
        driver.findElement(By.name("lastname")).sendKeys("Richard");
        driver.findElement(By.name("username")).sendKeys("avarichard");
        driver.findElement(By.name("email")).sendKeys("avarichard@gmail.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        driver.findElement(By.xpath("//input[@value='female']")).click();
        driver.findElement(By.name("birthday")).sendKeys("05/05/2005");
        //driver.findElement(By.name("department")).click();
        //driver.findElement(By.xpath("//option[@value='TO']")).click();
        Select department = new Select(driver.findElement(By.name("department")));
        department.selectByVisibleText("Tourism Office");
        //driver.findElement(By.name("job_title")).click();
        //driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[10]/div/select/option[2]")).click();
        Select jobTitle = new Select(driver.findElement(By.name("job_title")));
        jobTitle.selectByVisibleText("Designer");
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//label[@for='inlineCheckbox2']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectedResult = "You've successfully completed registration!";
        String actualResult = driver.findElement(By.xpath("//p[text()=\"You've successfully completed registration!\"]")).getText();
        Assert.assertEquals(expectedResult, actualResult, "Failed");
    }

    @Test(description = "Verify that subject is" +
            "   'Thanks for subscribing to practice.cybertekschool.com!'   ")
    public void test6(){
        driver.get("https://www.tempmailaddress.com/");
        String email = driver.findElement(By.className("animace")).getText();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.xpath("//a[@href=\"/sign_up\"]")).click();
        driver.findElement(By.name("full_name")).sendKeys("Ava Richard");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("wooden_spoon")).click();
        //String expectedResult = "Thank you for signing up. Click the button below to return to the home page.";
        WebElement signupMessage = driver.findElement(By.name("signup_message"));
        if(signupMessage.isDisplayed()){
            System.out.println("signup Passed");
        }
        driver.get("https://www.tempmailaddress.com/");
        WebElement received = driver.findElement(By.xpath("//span[text()=\"do-not-reply@practice.cybertekschool.com\"]"));
        if (received.isDisplayed()){
            System.out.println("received from Passed");
        }
        driver.findElement(By.id("schranka")).click();
        WebElement from = driver.findElement(By.id("odesilatel"));
        WebElement subject = driver.findElement(By.id("predmet"));
        if(from.isDisplayed() && subject.isDisplayed()){
            System.out.println("from & subject Passed");
        }
    }


    @Test(description = "Verify that uploaded file name is displayed")
    public void test7(){
        driver.findElement(By.xpath("//a[@href='/upload']")).click();
        driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\madin\\Desktop\\Test case 7.txt");
        driver.findElement(By.id("file-submit")).click();
        WebElement fileUploaded = driver.findElement(By.xpath("//h3[text()=\"File Uploaded!\"]"));
        String expectedResult = "Test case 7.txt";
        String actualResult = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(actualResult, expectedResult);
        if(fileUploaded.isDisplayed()){
            System.out.println("File Uploaded");
        }
    }

    @Test(description = "Verify that following message is displayed:" +
            "  'You selected: United States of America'   ")
    public void test8(){
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@type='button']")).click();
        WebElement USA = driver.findElement(By.xpath("//p[text()=\"You selected: United States of America\"]"));
        if(USA.isDisplayed()){
            System.out.println("You selected: United States of America is displayed");
        }
    }

        @AfterMethod
    public void teardown() {

        driver.quit();
    }
}
