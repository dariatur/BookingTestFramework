package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import org.openqa.selenium.By;
import utils.DateParser;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage {
    public static SelenideElement CITY_INPUT = $(By.name("ss"));
    public static SelenideElement DATE_START_BUTTON = $x("//button[@data-testid='searchbox-dates-container']");
    public static String CHOSEN_DATE_START = "//*[@data-date='%s']";
    public static String CHOSEN_DATE_END = "//*[@data-date='%s']";
    public static SelenideElement FIND_BUTTON = $x("//button[@type='submit']");
    public static SelenideElement ENTER_CITY_FIELD_ALERT = $x("//*[@data-testid='searchbox-alert']/div");

    public SearchPage openSearchPage(String url) {
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public SearchPage writeCity(String city) {
        CITY_INPUT.setValue(city);
        return this;
    }

    public SearchPage selectDateStart(LocalDate date) {
        SelenideElement dateElem = $x(String.format(CHOSEN_DATE_START, date.toString()));

        new Button().click(DATE_START_BUTTON);
        if(dateElem.is(Condition.interactable)){
            dateElem.click();
        } else {
            while(!dateElem.is(Condition.interactable)){
                new Button().click($x("//div[@data-testid='searchbox-datepicker-calendar']/button"));
            }
            dateElem.click();
        }

        return this;
    }

    public SearchPage selectDateEnd(LocalDate date) {
        SelenideElement dateElem = $x(String.format(CHOSEN_DATE_END, date.toString()));
        dateElem.click();

        return this;
    }

    public SearchResultsPage clickOnFindButton(){
        new Button().click(FIND_BUTTON);
        return new SearchResultsPage();
    }
}
