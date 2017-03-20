package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage extends Common {

    public static void setSearchText(String searchString) {
        SelenideElement searchField =  $("#ptxt");
        //searchText.sendKeys(searchString); //* Doesn't seem to work right

        for (String chr : searchString.split("(?!^)")) {
            searchField.sendKeys(chr);
        }
        $("#ptxt").shouldHave(value(searchString));

        ElementsCollection vaaa = $$("#preload_txt div");
        SelenideElement topResult = StartPage.getElementByTextIgnoreCase($$("#preload_txt div"), searchString);
        if (topResult != null) {
            topResult.click();
        }
        $("#preload_txt").shouldNotBe(visible);
    }

    public static void selectRegion(int id) {
        SelenideElement region = $("#s_region_select");
        region.selectOption(id);
    }

    public static void setPriceRange(String priceLow, String priceHigh) {
        SelenideElement min =  $(byName("topt[8][min]"));
        min.setValue(priceLow);
        SelenideElement max =  $(byName("topt[8][max]"));
        max.setValue(priceHigh);
    }

    public static void startSearch () {
        SelenideElement searchButton =  $("#sbtn");
        searchButton.click();
    }
}
