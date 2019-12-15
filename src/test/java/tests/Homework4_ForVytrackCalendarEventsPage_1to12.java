package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class Homework4_ForVytrackCalendarEventsPage_1to12 {

    private WebDriver driver;


    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.name("_username")).sendKeys("storemanager85");
        driver.findElement(By.name("_password")).sendKeys("UserUser123", Keys.ENTER);
        //driver.findElement(By.name("_submit")).click();
        //BrowserUtils.waitUntilLoaderMaskDisappear();
        //driver.findElement(By.linkText("Activities")).click();
        //driver.findElement(By.linkText("Calendar Events")).click();
        BrowserUtils.wait(3);
        driver.findElement(By.xpath("//*[normalize-space()='Activities' and @class='title title-level-1']")).click();
        driver.findElement(By.xpath("//*[normalize-space()='Calendar Events' and @class='title title-level-2']")).click();
        BrowserUtils.wait(2);

    }

    @Test(description = "Verify that “view”, “edit” and “delete” options are available")
    public void test1(){
        Actions action = new Actions(driver);
        WebElement hover  = driver.findElement(By.xpath("//tr[4]//td[9]"));
        action.moveToElement(hover).build().perform();
        BrowserUtils.wait(1);
        List<WebElement> options = hover.findElements(By.xpath("//ul[@class=\"nav nav-pills icons-holder launchers-list\"]"));
        for (WebElement option: options){
            Assert.assertTrue(option.isDisplayed());
        }
    }

    @Test(description = "Verify that “Title” column still displayed")
    public void test2(){
        driver.findElement(By.xpath("//div[@class=\"column-manager dropdown\"]")).click();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//tbody//tr//td//input[@id]"));
        for(WebElement checkbox: checkboxes){
            if(checkbox == checkboxes.get(0)){
                continue;
            }
            else {
                checkbox.click();
                //BrowserUtils.wait(1);
            }
        }
        WebElement title = driver.findElement(By.xpath("//th//a//span[@class='grid-header-cell__label']"));
        Assert.assertTrue(title.isDisplayed());
    }



    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}



