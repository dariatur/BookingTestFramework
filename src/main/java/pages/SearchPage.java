package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class SearchPage extends BasePage {
    private static SelenideElement DATE_START_BUTTON = $x("//button[@data-testid='searchbox-dates-container']");
    private static String CHOSEN_DATE_START = "//*[@data-date='%s']";
    private static String CHOSEN_DATE_END = "//*[@data-date='%s']";
    private static SelenideElement FIND_BUTTON = $x("//button[@type='submit']");
    private static SelenideElement ARROW_FOR_CHOOSING_MONTH = $x("//button[@aria-label='Next month']");
    private static SelenideElement CHOOSE_AMOUNT_OF_GUESTS_BUTTON = $x("//button[@data-testid='occupancy-config']");
    private static SelenideElement DECREASE_AMOUNT_OF_ADULTS_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Adults')]/parent::div/following-sibling::div/button[1]");
    private static SelenideElement INCREASE_AMOUNT_OF_ADULTS_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Adults')]/parent::div/following-sibling::div/button[2]");
    private static SelenideElement DECREASE_AMOUNT_OF_CHILDREN_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Children')]/parent::div/following-sibling::div/button[1]");
    private static SelenideElement INCREASE_AMOUNT_OF_CHILDREN_BUTTON = $x("//div[@data-testid='occupancy-popup']//label[contains(text(), 'Children')]/parent::div/following-sibling::div/button[2]");
    private static SelenideElement AMOUNT_OF_GUESTS = $x("//button[@data-testid='occupancy-config']/span");
    private static ElementsCollection SELECT_CHILDREN_AGE_INPUTS = $$(By.xpath("//select[@name='age']"));
    private static SelenideElement ENTER_CITY_FIELD_ALERT = $x("//*[@data-testid='searchbox-alert']/div");

    public SearchPage openSearchPage(String url) {
        log.info("Open url {}", url);
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public SearchPage writeCity(String city) {
        log.info("Add city {} to input", city);
        new Input().writeCity(city);
        return this;
    }

    public SearchPage selectDateStart(LocalDate date) {
        SelenideElement dateElem = $x(String.format(CHOSEN_DATE_START, date.toString()));
        log.info("Click on calendar to select date ");
        new Button().click(DATE_START_BUTTON);
        if(dateElem.is(Condition.interactable)){
            log.info("Click on selected date if it's visible, date {}", date);
            dateElem.click();
        } else {
            while(!dateElem.is(Condition.interactable)){
                log.info("Choose another month");
                new Button().click(ARROW_FOR_CHOOSING_MONTH);
            }
            dateElem.click();
        }

        return this;
    }

    public SearchPage selectDateEnd(LocalDate date) {
        SelenideElement dateElem = $x(String.format(CHOSEN_DATE_END, date.toString()));
        log.info("Select end date {}", date);
        dateElem.click();

        return this;
    }

    public SearchResultsPage clickOnFindButton(){
        log.info("Click on 'Find' button");
        new Button().click(FIND_BUTTON);
        return new SearchResultsPage();
    }

    public SearchPage clickOnChooseAmountOfGuestsButton(){
        log.info("Click on 'choose amount of guests' button");
        new Button().click(CHOOSE_AMOUNT_OF_GUESTS_BUTTON);
        return this;
    }

    public SearchPage increaseAmountOfAdultsUntilLimit(){
        Button button = new Button();
        log.info("Increase amount of adults until maximum");
        while (INCREASE_AMOUNT_OF_ADULTS_BUTTON.isDisplayed()){
            button.click(INCREASE_AMOUNT_OF_ADULTS_BUTTON);
        }

        return this;
    }

    public SearchPage decreaseAmountOfAdultsUntilLimit(){
        Button button = new Button();
        log.info("Decrease amount of adults until minimum");
        while (DECREASE_AMOUNT_OF_ADULTS_BUTTON.isEnabled()){
            button.click(DECREASE_AMOUNT_OF_ADULTS_BUTTON);
        }

        return this;
    }

    public SearchPage increaseAmountOfChildrenUntilLimit(){
        Button button = new Button();
        log.info("Increase amount of children until maximum");
        while (INCREASE_AMOUNT_OF_CHILDREN_BUTTON.isDisplayed()){
            button.click(INCREASE_AMOUNT_OF_CHILDREN_BUTTON);
        }

        return this;
    }

    public SearchPage decreaseAmountOfChildrenUntilLimit(){
        Button button = new Button();
        log.info("Decrease amount of children until minimum");
        while (DECREASE_AMOUNT_OF_CHILDREN_BUTTON.isEnabled()){
            button.click(DECREASE_AMOUNT_OF_CHILDREN_BUTTON);
        }

        return this;
    }

    public int getAmountOfAdults(){
        Pattern pattern = Pattern.compile("(\\d+)\\s+adult[s]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(AMOUNT_OF_GUESTS.getText());
        log.info("Get amount of adults");
        int amount = matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
        log.info(amount);
        return amount;
    }

    public int getAmountOfChildren(){
        Pattern pattern = Pattern.compile("(\\d+)\\s+children", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(AMOUNT_OF_GUESTS.getText());
        log.info("Get amount of children");
        int amount = matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
        log.info(amount);
        return amount;
    }

    public int getAmountOfSelectChildrenAgeInputs(){
        log.info("Get amount of 'select children age' inputs");
        return SELECT_CHILDREN_AGE_INPUTS.size();
    }

    public String getEnterCityFieldAlertText(){
        log.info("Error message in 'city' field: {}", ENTER_CITY_FIELD_ALERT.getText());
        return ENTER_CITY_FIELD_ALERT.getText();
    }
}
