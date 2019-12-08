package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

public class HomeworkForTesting_9to12 {

    private WebDriver driver;
    private WebElement message;

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }

    @DataProvider(name = "testData")
    public static Object [][] testData(){
        return new Object[][]{   {200}, {301}, {404}, {500}};
    }

    @Test(dataProvider = "testData")
    public void test9to12(int code){
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.xpath("//a[text()='"+code+"']")).click();
        message = driver.findElement(By.xpath("//p[contains(text()," +code+ ")]"));
        String actualResult = message.getText();
        //System.out.println(actualResult);
        String[] messageSplit = actualResult.split("\\n");
        actualResult = messageSplit[0];
        String expectedResult = "This page returned a " + code + " status code.";
        Assert.assertEquals(actualResult, expectedResult, "Failed");

    }








    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
