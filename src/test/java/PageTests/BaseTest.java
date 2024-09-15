package PageTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeMethod
    public static WebDriver baseBefore() {
        driver = new ChromeDriver();
        driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    @AfterMethod
    public static void baseAfter() {
        System.out.println("Test passed");
        driver.quit();
    }
}
