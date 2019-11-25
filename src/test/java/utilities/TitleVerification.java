package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                "http://practice.cybertekschool.com/dropdown",
                "http://practice.cybertekschool.com/login");
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        String actualUrl = "http://practice.cybertekschool.com/";
        String actualTitle = "Practice";
        for(String each: urls){
            driver.get(each);
            StringUtility.verifyEquals(actualTitle, driver.getTitle());
            if(driver.getCurrentUrl().contains(actualUrl)){
                System.out.println("Passed - URL starts with: "+actualUrl);
            }else {
                System.out.println("Failed");
            }



        }
        driver.quit();
    }
}
