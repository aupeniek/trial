package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.title;

public class StartPage extends Common {

    public static ElementsCollection getSections() {
        return $$("td.main_img_td1");
    }

    public static void clickSection(String sectionId) {
        SelenideElement sectionHeader =  $("#" + sectionId + " div.main_img_div1 div.main_head div.main_head2 h2");
        String headerText = sectionHeader.text();
        sectionHeader.click();
        System.out.println ("== Click section: === " + headerText);
    }
}
