package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;
import java.util.List;

public class Homework3_ForSynchronization_1to6 {

    private WebDriver driver;


    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.name("_username")).sendKeys("storemanager85");
        driver.findElement(By.name("_password")).sendKeys("UserUser123");
        driver.findElement(By.name("_submit")).click();
        BrowserUtils.wait(4);
        driver.findElement(By.xpath("//*[normalize-space()='Activities' and @class='title title-level-1']")).click();
        driver.findElement(By.xpath("//*[normalize-space()='Calendar Events' and @class='title title-level-2']")).click();
        BrowserUtils.wait(2);



    }


    @Test(description = "Verify that page subtitle 'Options' is displayed")
    public void test1(){

        WebElement options = driver.findElement(By.xpath("//div[@href='#']"));
        String text = driver.findElement(By.xpath("//div[@href='#']")).getText();
        if(options.isDisplayed()){
            System.out.println("Passed "+text+" is displayed!");
        }else{
            System.out.println("Failed");
        }

    }

    @Test(description = "Verify that page number is equals to '1' ")
    public void test2(){

        String expectedResult = "1";
        String actualResult = driver.findElement(By.xpath("//input[@value=\"1\"]")).getAttribute("value");
        Assert.assertEquals(expectedResult, actualResult);
        //StringUtility.verifyEquals(expectedResult, actualResult);
    }

    @Test(description = "Verify that view per page number is equals to '25' ")
    public void test3(){

        String expectedResult = "25";
        String actualResult = driver.findElement(By.xpath("//button[contains(text(), \"25\")]")).getText();
        Assert.assertEquals(expectedResult, actualResult);
        //StringUtility.verifyEquals(expectedResult, actualResult);
    }



    @Test(description = "Verify that number of calendar events (rows in the table) is equals to number of records")
    public void test4(){

        List<WebElement> list = driver.findElements(By.xpath("//table[@class=\"grid table-hover table table-bordered table-condensed\"]/tbody/tr"));
        String rows = ""+ list.size();   //24
        String records = driver.findElement(By.xpath("//label[text()=\"Total of 24 records\"]")).getText(); //
        if(records.contains(rows)){
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
        }
    }


    @Test(description = "Verify that all calendar events were selected")
    public void test5(){

        driver.findElement(By.xpath("//div[@class=\"btn-group dropdown\"]/button/input")).click();
        BrowserUtils.wait(1);
        List<WebElement> checkboxes = driver.findElements(By.xpath("//td[@data-column-label=\"Selected Rows\"]/input"));
        /*int index = 1;
        for(WebElement checkbox: checkboxes){
            if(checkbox.isSelected()){
                System.out.println("Checkbox # "+index+" clicked");
            }else {
                System.out.println("Failed");
            }index++;
        }*/
        for (WebElement checkbox: checkboxes){
            Assert.assertTrue(checkbox.isSelected(), "Failed");
        }

    }


    @Test(description = "Verify that following data is displayed:")
    public void test6(){

        driver.findElement(By.cssSelector("[class='grid-row row-click-action']:nth-of-type(15)")).click();
        BrowserUtils.wait(2);
        List<WebElement> labels = driver.findElements(By.xpath("//label[@class=\"control-label\"]"));
        List<WebElement> divs = driver.findElements(By.xpath("//div[@class=\"control-label\"]"));
        divs.add(1, driver.findElement(By.xpath("//p[text()=\"This is a a weekly testers meeting\"]")));
       // BrowserUtils.wait(10);
        //solution 1
        for(WebElement label: labels){
            if(label.isDisplayed()){
                System.out.println("Passed "+ label.getText());
            }
        }
        for (WebElement div: divs){
            if(div.isDisplayed()){
                System.out.println("Passed "+div.getText());
            }
        }
        //solution 2
        for(WebElement label: labels){
            if(driver.getPageSource().contains(label.getText())){
                System.out.println("Passed "+label.getText());
            }
        }
        for (WebElement div: divs){
            if(driver.getPageSource().contains(div.getText())){
                System.out.println("Passed "+div.getText());
            }
        }

    }






    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
