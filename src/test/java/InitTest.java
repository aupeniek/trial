
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class InitTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = "chrome".equals(System.getProperty("selenide.browser")) ?
                new ChromeDriver() : new FirefoxDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userCanSearch() {
        driver.get("http://ss.lv/");
//        GooglePage page = PageFactory.initElements(driver, GooglePage.class);
//        SearchResultsPage results = page.searchFor("Selenide");
//        assertTrue(results.getResults().get(0).getText().startsWith("Selenide: concise UI tests in Java"));
    }

}
