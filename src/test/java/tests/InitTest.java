package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.remote.DesiredCapabilities;
import pages.StartPage;
import util.TestWrapper;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InitTest extends TestWrapper{
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = "chrome".equals(System.getProperty("selenide.browser")) ?
                new ChromeDriver() : new FirefoxDriver();

        timeout = BASE_TIMEOUT;
        System.out.println("-----Starting test on: " + BASE_URL);
        System.out.println("-----baseUrl: " + BASE_URL);
        System.out.println("-----timeout: " + timeout);

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void canSwitchLanguage() {
        open("http://ss.lv");
        if (!StartPage.inRussian()) StartPage.switchLanguage();
        assertTrue(StartPage.inRussian());
        $("#main_table").should(exist);
        $("#page_main_full").should(exist);
        $("#page_footer").should(exist);

        //StartPage.clickSection("dv_6");

        //StartPage.getSectionsList().find()
//        GooglePage page = PageFactory.initElements(driver, GooglePage.class);
//        SearchResultsPage results = page.searchFor("Selenide");
//        assertTrue(results.getResults().get(0).getText().startsWith("Selenide: concise UI tests in Java"));
    }

}
