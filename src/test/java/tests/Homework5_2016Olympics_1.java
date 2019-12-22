package tests;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.BrowserUtils;
import java.util.*;

public class Homework5_2016Olympics_1 {

    private WebDriver driver;



    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
    }

    @Test(description = "Verify that by default the Medal table is sorted by rank")
    public void test1(){
        List<WebElement> ranks = driver.findElements(By.xpath("//table[contains(@class, \"wikitable sortable\")]//tr//td[1]"));
        Set<Integer> actualNums = new LinkedHashSet<>();//insertion order
        Set<Integer> sortedNums = new TreeSet<>();//sorted order
        for(int i = 0; i <= ranks.size()-3; i++){
            actualNums.add(Integer.parseInt(ranks.get(i).getText()));
            sortedNums.add(Integer.parseInt(ranks.get(i).getText()));
        }
        Assert.assertEquals(actualNums, sortedNums);
    }


    @Test(description = "1. Verify that the table is now sorted by the country names" +
                        "2. Verify that Rank column is not in ascending order anymore")
    public void test1part2(){
        driver.findElement(By.xpath("//th[text()=\"NOC\"]")).click();
        BrowserUtils.wait(1);
        List<WebElement> noc = driver.findElements(By.xpath("//table[contains(@class, \"wikitable sortable\")]//tr//a"));
        Set<String> actualCountries = new LinkedHashSet<>();
        Set<String> sortedCountries = new TreeSet<>();
        for(WebElement each: noc){
            if(!each.getText().equalsIgnoreCase("Remaining NOCs")) {
                actualCountries.add(each.getText());
                sortedCountries.add(each.getText());
            }
        }
      //  actualCountries.remove("Remaining NOCs");
       // sortedCountries.remove("Remaining NOCs");
        System.out.println(actualCountries);
        System.out.println(sortedCountries);
        Assert.assertEquals(actualCountries, sortedCountries);  //1


        List<WebElement> ranks = driver.findElements(By.xpath("//table[contains(@class, \"wikitable sortable\")]//tr//td[1]"));
        ArrayList<String> actualNums = new ArrayList<>();
        ArrayList<String> sortedNums = new ArrayList<>();

        for(int i = 0; i <= ranks.size()-2; i++) {
                actualNums.add(ranks.get(i).getText());
                sortedNums.add(ranks.get(i).getText());
        }
       actualNums.remove(7);
       sortedNums.remove(7);
       Collections.sort(sortedNums);
       Assert.assertNotEquals(actualNums, sortedNums);//2
    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }

}
