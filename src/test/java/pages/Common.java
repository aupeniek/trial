package pages;

import org.openqa.selenium.NoSuchElementException;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;

public class Common {

    public static void switchLanguage() {
        getMainMenuElements().last().click();
    }

    public static SearchPage openSearch() {
        return open(getMainMenuElements().get(2).getAttribute("href"), SearchPage.class);

    }

    public static void openFavourites() {
        getMainMenuElements().get(3).click();
    }

    public static ElementsCollection getMainMenuElements() {
        return $$(".a_menu");
    }

    public static boolean inRussian() {
        if (getMainMenuElements().last().text().startsWith("LV")) return true;
        else return false;
    }

    public static void clickAlertOK() {
        SelenideElement alert = $("#alert_dv");
        alert.shouldBe(visible);
        if (alert.isDisplayed()) {
            alert.$("#alert_ok").click();
        }
    }

    public static SelenideElement checkTextInCollection(ElementsCollection col,
                                                        String filter) {
        for (SelenideElement element : col) {
            if (element.findAll(byText(filter)).size() > 0) {
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    public static SelenideElement getElementByTextIgnoreCase(ElementsCollection col,
                                                             String filter) {
        for (SelenideElement element : col) {
            if (element.getText().equalsIgnoreCase(filter)) {
                return element;
            }
        }
        return null;
    }

}
