package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

import java.util.*;

public class Homework5_2to5 {
    private WebDriver driver;

    @Test(description = " ")
    public void getCountryWithLeastGold(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        BrowserUtils.wait(1);
        List<WebElement> noc = driver.findElements(By.xpath("//table[contains(@class, \"wikitable sortable\")]//tr//a"));

        ArrayList<String> actualCountries = new ArrayList<>();
        for(WebElement each: noc) {
            if(!each.getText().equalsIgnoreCase("Remaining NOCs"))
            actualCountries.add(each.getText());
        }
        List<WebElement> goldElements = driver.findElements(By.xpath("//table[contains(@class, \"wikitable sortable\")]//tr//td[2]"));
        ArrayList<Integer> goldNums = new ArrayList<>();
        for(int i = 0; i <= goldElements.size()-3; i++){
            goldNums.add(Integer.parseInt(goldElements.get(i).getText()));
        }


        Map<Integer, String> goldCount = new LinkedHashMap<>();
        for(int i = 0; i < actualCountries.size()-1 && i < goldNums.size()-1; i++){
            goldCount.put(goldNums.get(i), actualCountries.get(i));
        }

        System.out.println(goldCount.get(8));

    }
}
