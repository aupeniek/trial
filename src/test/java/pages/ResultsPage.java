package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultsPage extends Common {

    public static void sortByPrice() {
        if ($$("#head_line td").size() > 0 ) {
            $$("#head_line").last().click();
        }
    }

    public static void setDealType(int id) {
        $$("div.filter_second_line_dv span").last().$("select").selectOption(id);
    }

    public static ElementsCollection getResults() {
        SelenideElement resultsTable = $("#page_main tbody tr td").find(byAttribute("align", "center"));
        ElementsCollection col = getSectionResults(resultsTable);
        return col;
    }

    public static ElementsCollection getSectionResults(SelenideElement sectionTable) {
        ElementsCollection col =sectionTable.$$("tbody tr");
        col = col.exclude(Condition.id("head_line"));
        col = col.exclude(Condition.id(""));
        col = col.exclude(Condition.id("tr_bnr_712"));
        return col;
    }

    public static void openExtendedSearch() {
        SelenideElement td = $$("#page_main tbody tr td table").first().$("tbody td td.td7 a");
        td.click();
    }

    public static void selectResult(int id) {
        System.out.println("- Clicking: "+ id);
        SelenideElement row = getResults().get(id);
        row.$$("td").first().$("input").setSelected(true);
    }

    public static void addSelectedToFavourites() {
        $("#clear_selected_a").shouldBe(visible);
        $("#a_fav_sel").click();
    }

    public static void showFavourites() {
        $("#clear_selected_a").shouldBe(visible);
        $("#show_selected_a").click();
    }

    public static List<String> getCheckedIds() {
        List<String> list = new ArrayList<String>();
        for (SelenideElement element : getResults()) {
            if (element.$("td input").isSelected()) {
                list.add(element.getAttribute("id"));
            }
        }
        return list;
    }

    public static ElementsCollection getFavouritesSections() {
        ElementsCollection sectionTables = $$("#filter_frm table");
        return sectionTables;
    }
}
