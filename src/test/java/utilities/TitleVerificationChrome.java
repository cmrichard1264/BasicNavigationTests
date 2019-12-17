package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.Arrays;
import java.util.List;

public class TitleVerificationChrome {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("https://www.luluandgeorgia.com/",
                "https://wayfair.com/", "https://walmart.com", "https://westelm.com/",
                "https://www.facebook.com/");
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        for (String each: urls){
            driver.get(each);
            if(driver.getCurrentUrl().contains(driver.getTitle().toLowerCase().replace(" ", ""))){
                System.out.println("Passed " + each);
                System.out.println(driver.getTitle().toLowerCase().replace(" ", ""));
            }else {
                System.out.println("Failed "+each);
                System.out.println(driver.getTitle().toLowerCase().replace(" ", ""));
            }
        }
        driver.quit();



    }
}
