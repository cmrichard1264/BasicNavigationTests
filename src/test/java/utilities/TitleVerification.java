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
        String expectedUrl = "http://practice.cybertekschool.com/";
        String expectedTitle = "Practice";
        for(String each: urls){
            driver.get(each);
            StringUtility.verifyEquals(expectedTitle, driver.getTitle());
            if(driver.getCurrentUrl().contains(expectedUrl)){
                System.out.println("Passed - URL starts with: "+expectedUrl);
            }else {
                System.out.println("Failed");
            }



        }
        driver.quit();
    }
}
