package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BasePage {
    public static SelenideElement DATE_START_BUTTON = $x("//button[@data-testid='searchbox-dates-container']");
    public static String CHOSEN_DATE_START = "//*[@data-date='%s']";
    public static String CHOSEN_DATE_END = "//*[@data-date='%s']";
    public static SelenideElement FIND_BUTTON = $x("//button[@type='submit']");
    public static SelenideElement ENTER_CITY_FIELD_ALERT = $x("//*[@data-testid='searchbox-alert']/div");
    public static SelenideElement ARROW_FOR_CHOOSING_MONTH = $x("//div[@data-testid='searchbox-datepicker-calendar']/button");
    public static SelenideElement CHOOSE_AMOUNT_OF_GUESTS_BUTTON = $x("//button[@data-testid='occupancy-config']");
    public static SelenideElement DECREASE_AMOUNT_OF_ADULTS_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Adults')]/parent::div/following-sibling::div/button[1]");
    public static SelenideElement INCREASE_AMOUNT_OF_ADULTS_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Adults')]/parent::div/following-sibling::div/button[2]");
    public static SelenideElement DECREASE_AMOUNT_OF_CHILDREN_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Children')]/parent::div/following-sibling::div/button[1]");
    public static SelenideElement INCREASE_AMOUNT_OF_CHILDREN_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Children')]/parent::div/following-sibling::div/button[2]");
    public static SelenideElement AMOUNT_OF_GUESTS = $x("//button[@data-testid='occupancy-config']/span");
    public static ElementsCollection SELECT_CHILDREN_AGE_INPUTS = $$(By.xpath("//select[@name='age']"));

    public SearchPage openSearchPage(String url) {
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public SearchPage writeCity(String city) {
        new Input().writeCity(city);
        return this;
    }

    public SearchPage selectDateStart(LocalDate date) {
        SelenideElement dateElem = $x(String.format(CHOSEN_DATE_START, date.toString()));

        new Button().click(DATE_START_BUTTON);
        if(dateElem.is(Condition.interactable)){
            dateElem.click();
        } else {
            while(!dateElem.is(Condition.interactable)){
                new Button().click(ARROW_FOR_CHOOSING_MONTH);
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

    public SearchPage clickOnChooseAmountOfGuestsButton(){
        new Button().click(CHOOSE_AMOUNT_OF_GUESTS_BUTTON);
        return this;
    }

    public SearchPage increaseAmountOfAdultsUntilLimit(){
        Button button = new Button();
        while (INCREASE_AMOUNT_OF_ADULTS_BUTTON.isDisplayed()){
            button.click(INCREASE_AMOUNT_OF_ADULTS_BUTTON);
        }

        return this;
    }

    public SearchPage decreaseAmountOfAdultsUntilLimit(){
        Button button = new Button();
        while (DECREASE_AMOUNT_OF_ADULTS_BUTTON.isEnabled()){
            button.click(DECREASE_AMOUNT_OF_ADULTS_BUTTON);
        }

        return this;
    }

    public SearchPage increaseAmountOfChildrenUntilLimit(){
        Button button = new Button();
        while (INCREASE_AMOUNT_OF_CHILDREN_BUTTON.isDisplayed()){
            button.click(INCREASE_AMOUNT_OF_CHILDREN_BUTTON);
        }

        return this;
    }

    public SearchPage decreaseAmountOfChildrenUntilLimit(){
        Button button = new Button();
        while (DECREASE_AMOUNT_OF_CHILDREN_BUTTON.isEnabled()){
            button.click(DECREASE_AMOUNT_OF_CHILDREN_BUTTON);
        }

        return this;
    }

    public int getAmountOfAdults(){
        Pattern pattern = Pattern.compile("(\\d+)\\s+adult[s]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(AMOUNT_OF_GUESTS.getText());
        int amount = matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;

        return amount;
    }

    public int getAmountOfChildren(){
        Pattern pattern = Pattern.compile("(\\d+)\\s+children", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(AMOUNT_OF_GUESTS.getText());
        int amount = matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;

        return amount;
    }

    public int getAmountOfSelectChildrenAgeInputs(){
        return SELECT_CHILDREN_AGE_INPUTS.size();
    }
}
