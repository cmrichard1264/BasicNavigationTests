package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.StringUtility;

public class NavigationTests {

    private WebDriver driver;

    @Test(description = "Verify titles(chrome)")
    public void testChrome(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://google.com");
        String googleTitle = driver.getTitle();
        driver.get("https://etsy.com");
        String etsyTitle = driver.getTitle();
        driver.navigate().back();
        StringUtility.verifyEquals(googleTitle, driver.getTitle());
        driver.navigate().forward();
        StringUtility.verifyEquals(etsyTitle, driver.getTitle());

        driver.quit();

    }

    @Test(description = "Verify titles(fireFox)")
    public void testFirefox(){
        driver = BrowserFactory.getDriver("firefox");
        driver.get("https://google.com");
        String googleTitle = driver.getTitle();
        driver.get("https://etsy.com");
        String etsyTitle = driver.getTitle();
        driver.navigate().back();

        StringUtility.verifyEquals(googleTitle, driver.getTitle());
        driver.navigate().forward();

        StringUtility.verifyEquals(etsyTitle, driver.getTitle());

        driver.quit();

    }

    @Test(description = "Verify titles(Opera)")
    public void testOpera(){
        driver = BrowserFactory.getDriver("opera");
        driver.get("https://google.com");
        String googleTitle = driver.getTitle();
        driver.get("https://etsy.com");
        String etsyTitle = driver.getTitle();
        driver.navigate().back();

        StringUtility.verifyEquals(googleTitle, driver.getTitle());
        driver.navigate().forward();

        StringUtility.verifyEquals(etsyTitle, driver.getTitle());

        driver.quit();

    }
}
