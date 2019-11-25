package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.Arrays;
import java.util.List;

public class TitleVerificationFireFox {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("https://www.luluandgeorgia.com/",
                "https://wayfair.com/", "https://walmart.com", "https://westelm.com/");
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();

        for (String each: urls){
            driver.get(each);
            if(driver.getCurrentUrl().contains(driver.getTitle().toLowerCase().replace(" ", ""))){
                System.out.println("Passed");
            }else {
                System.out.println("Failed");
            }


        }
        driver.quit();



    }
}
