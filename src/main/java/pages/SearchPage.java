package pages;

import com.codeborne.selenide.SelenideElement;
import elements.Button;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage {
    public static SelenideElement CITY_INPUT = $(By.name("ss"));
    public static SelenideElement DATE_START_BUTTON = $x("//button[@data-testid='date-display-field-start']");
    public static String CHOSEN_DATE_START = "//td//span[text()='%s']/ancestor::td";
    public static String CHOSEN_DATE_END = "//td//span[text()='%s']/ancestor::td";
    public static SelenideElement FIND_BUTTON = $x("//button[@type='submit']");

    public SearchPage openSearchPage(String url) {
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public SearchPage writeCity(String city) {
        CITY_INPUT.setValue(city);
        return this;
    }

    public SearchPage selectDateStart(String number) {
        new Button().click(DATE_START_BUTTON);
        $x(String.format(CHOSEN_DATE_START, number)).click();
        return this;
    }

    public SearchPage selectDateEnd(String number) {
        $x(String.format(CHOSEN_DATE_END, number)).click();
        return this;
    }

    public SearchResultsPage clickOnFindButton(){
        new Button().click(FIND_BUTTON);
        return new SearchResultsPage();
    }
}
