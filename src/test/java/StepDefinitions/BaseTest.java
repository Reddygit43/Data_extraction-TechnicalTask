package StepDefinitions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    protected WebDriver setUp() {

        if (driver == null) {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", "src/test/Chromedriver/chromedriver_mac64/chromedriver");
            driver = new ChromeDriver();

        }
        return driver;
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
        System.out.println("done AfterTest");
    }


}
