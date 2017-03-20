package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.Common;
import pages.ResultsPage;
import pages.SearchPage;
import pages.StartPage;
import util.TestWrapper;

import java.util.List;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static org.junit.Assert.assertTrue;

public class TrialTest extends TestWrapper {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        startMaximized = true;

        timeout = BASE_TIMEOUT;
        System.out.println("-----Starting test on: " + BASE_URL);
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
        //driver.get("http://ss.lv");

        StartPage startpage = open("http://ss.lv", StartPage.class);
        if (!StartPage.inRussian()) StartPage.switchLanguage();
        assertTrue(StartPage.inRussian());

        StartPage.clickSection("dv_6");
        SearchPage searchpage = Common.openSearch();

        SearchPage.setPriceRange("0", "500");
        SearchPage.selectRegion(1);
        SearchPage.setSearchText("Компьютер");
        SearchPage.startSearch();

        ResultsPage.sortByPrice();
        ResultsPage.setDealType(1);
        ResultsPage.openExtendedSearch();
        SearchPage.setPriceRange("0", "300");
        SearchPage.startSearch();
        ElementsCollection results = ResultsPage.getResults();

        int num = 3;

        for(int n = 0; n < num; n++) {
            int id = (int) (Math.random() * ((results.size())));
            ResultsPage.selectResult(id);
        }

        List<String> checkedlist = ResultsPage.getCheckedIds();
        System.out.println("checked items :" + checkedlist);

        ResultsPage.addSelectedToFavourites();
        Common.clickAlertOK();
        ResultsPage.showFavourites();

        ElementsCollection favouritesSections = ResultsPage.getFavouritesSections();
        System.out.println("Categories found in Favourites: " + favouritesSections.size());

        for (SelenideElement section : favouritesSections) {
            for (SelenideElement element : ResultsPage.getSectionResults(section)) {
                 assertTrue("Element id: " + element.getAttribute("id") +
                         " was not found in the list: " + checkedlist.toString(),
                         checkedlist.toString().matches("\\[.*\\b" + element.getAttribute("id") + "\\b.*]"));
            }
        }

        screenshot("final");

    }

}
