package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    //only one private static instance
    private static WebDriver driver;

    //to prevent class instantiation
    private Driver() {

    }

    public static WebDriver get() {
        if(driver==null){
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    //if browser type is wrong, stop tests and throw exception
                    throw new RuntimeException("Wrong browser type!");
            }
        }
        return driver;
    }

    public static void close(){
        //if driver still exist
        if(driver!=null){
            //close all browsers
            driver.quit();
            //destroy driver object, ready for garbage collector
            driver = null;
        }
    }
}
