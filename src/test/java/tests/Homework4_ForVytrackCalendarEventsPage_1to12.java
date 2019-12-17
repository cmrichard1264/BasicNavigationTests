package tests;

import org.apache.http.impl.io.IdentityInputStream;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class Homework4_ForVytrackCalendarEventsPage_1to12 {

    private WebDriver driver;
    private WebDriverWait wait;



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
        BrowserUtils.wait(4);
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

    @Test(description = "Verify that “Save And Close”, “Save And New” " +
                         "and “Save” options are available")
    public void test3(){
        driver.findElement(By.xpath("//a[@title=\"Create Calendar event\"]")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("[class='btn-success btn dropdown-toggle']")).click();
        List<WebElement> options = driver.findElements(By.xpath("//li//button"));
        for(WebElement option: options){
            Assert.assertTrue(option.isDisplayed());
        }
    }

    @Test(description = "Verify that “All Calendar Events” page subtitle is displayed")
    public void test4(){
        driver.findElement(By.xpath("//a[@title=\"Create Calendar event\"]")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("Cancel")).click();
        WebElement events = driver.findElement(By.className("oro-subtitle"));
        Assert.assertTrue(events.isDisplayed());
    }

    @Test(description = "Verify that difference between end and start time is exactly 1 hour")
    public void test5(){
        driver.findElement(By.xpath("//a[@title=\"Create Calendar event\"]")).click();
        BrowserUtils.wait(3);
        String startTime = driver.findElement(By.xpath("//input[@name=\"oro_calendar_event_form[start]\"]")).getAttribute("value");
        String endTime = driver.findElement(By.xpath("//input[@name=\"oro_calendar_event_form[end]\"]")).getAttribute("value");
        System.out.println("Start time and date: "+ startTime);
        System.out.println("End time and date: "+ endTime);
        startTime = startTime.substring(11, 16).replace(":", "");
        endTime = endTime.substring(11, 16).replace(":", "");
        int start = Integer.parseInt(startTime);
        int end = Integer.parseInt(endTime);
        if(end-start == 100){
            System.out.println("Passed ");
        }else {
            System.out.println("Failed");
        }
    }

    @Test(description = "Verify that end time is equals to “10:00 PM”")
    public void test6(){
        driver.findElement(By.xpath("//a[@title=\"Create Calendar event\"]")).click();
        BrowserUtils.wait(3);
        driver.findElement(By.xpath("//input[contains(@class, \"start u\")]")).click();
        driver.findElement(By.xpath("//li[text()=\"9:00 PM\"]")).click();
        driver.findElement(By.xpath("//input[contains(@class, \"end u\")]")).click();
        WebElement endTime = driver.findElement(By.xpath("//li[3][text()=\"10:00 PM\"]"));
        Assert.assertEquals(endTime.getText(), "10:00 PM", "Test Failed");
    }

    @Test(description = "1. Verify that “All-Day Event” checkbox is selected" +
                        "2. Verify that start and end time input boxes are not displayed" +
                        "3. Verify that start and end date input boxes are displayed")
    public void test7(){
        driver.findElement(By.xpath("//a[@title=\"Create Calendar event\"]")).click();
        BrowserUtils.wait(3);
        WebElement allDay = driver.findElement(By.name("oro_calendar_event_form[allDay]"));
        allDay.click();
        BrowserUtils.wait(1);
        Assert.assertTrue(allDay.isSelected());
        List<WebElement> time = driver.findElements(By.xpath("//input[@placeholder=\"time\"]"));
        for(WebElement each: time){
            Assert.assertTrue(!each.isDisplayed());
        }
        List<WebElement> date = driver.findElements(By.xpath("//input[@placeholder=\"Choose a date\"]"));
        for(WebElement each: date){
            Assert.assertTrue(each.isDisplayed());
        }
    }



    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}



