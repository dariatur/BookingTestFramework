package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import elements.Button;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Data
public class SearchResultsPage {
    private static final SelenideElement MODAL_TEXT = $x("//div[text()='Sign in, save money']");
    private static final SelenideElement MODAL_CLOSE_BUTTON = $x("//button[@aria-label=\"Dismiss sign in information.\"]");
    private SelenideElement resultText = $x("//h1");
    private SelenideElement priceInputRangeMin = $x("//input[@aria-label='Min.']");
    private SelenideElement priceInputRangeMax = $x("//input[@aria-label='Max.']");
    private SelenideElement priceInputRangeMinDot = $x("//input[@aria-label='Min.']/following-sibling::div[1]");
    private SelenideElement priceInputRangeMaxDot = $x("//input[@aria-label='Max.']/following-sibling::div[1]");
    private String reviewCheckbox = "(//div[contains(@data-filters-item, 'review')]//input[@type='checkbox' and contains(@aria-label, '%s')])[1]";
    private String reviewCheckboxLabel = "(//div[@data-testid='filters-group-label-content' and contains(text(), '%s')])[1]";
    private ElementsCollection reviewScores = $$(By.xpath("//div[contains(text(), 'Scored')]"));
    private ElementsCollection priceElements = $$(By.xpath("span[data-testid='price-and-discounted-price']"));
    private SelenideElement sortByLowestPriceButton = $(byText("Price (lowest first)"));
    private SelenideElement sortByHighestPriceButton = $(byText("Price (highest first)"));
    private SelenideElement sortByLowestRateButton = $(byText("Property rating (low to high)"));
    private SelenideElement sortByHighestRateButton = $(byText("Property rating (high to low)"));
    private SelenideElement sortButton = $x("//button[@data-testid='sorters-dropdown-trigger']");

    public SearchResultsPage isOpened() {
        log.info("Checking if search results page is opened");
        MODAL_TEXT.shouldBe(Condition.visible);
        return this;
    }

    public SearchResultsPage closeModalPage(){
        if (MODAL_TEXT.exists()) {
            MODAL_TEXT.shouldBe(Condition.visible, Duration.ofSeconds(3));
            log.info("Close modal page");
            MODAL_CLOSE_BUTTON.click();
        }
        return this;
    }

    public String getTextFromResultsPage(){
        try {
            log.info("Get text from results page {}", resultText.getText());
            return resultText.getText();
        } catch (Exception e) {
            log.info("Failed to get text from results page");
            return "";
        }
    }

    public SearchResultsPage setPriceInputRange(SelenideElement locator, SelenideElement locatorDot, String value){
        locator.shouldBe(Condition.interactable);

        int currentValue = Integer.parseInt(locator.getValue());
        int targetValue = Integer.parseInt(value);
        log.info("Current value of min price {}", currentValue);

        int direction = (currentValue < targetValue) ? 1 : -1;
        log.info("Direction is {}", direction);
        Actions actions = new Actions(WebDriverRunner.getWebDriver());

        int steps = Math.abs(targetValue - currentValue) / 10;

        for(int i = 0; i < steps; i++) {
            actions.clickAndHold(locatorDot)
                    .moveByOffset(direction * 5, 0)
                    .release()
                    .perform();

            currentValue = Integer.parseInt(locator.getValue());
            log.info("Current value after moving {}", currentValue);
            if(currentValue == targetValue) break;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

        return this;
    }

    public SearchResultsPage setPriceInputRangeMin(String value){
        setPriceInputRange(priceInputRangeMin, priceInputRangeMinDot, value);
        return this;
    }

    public SearchResultsPage setPriceInputRangeMax(String value){
        setPriceInputRange(priceInputRangeMax, priceInputRangeMaxDot, value);
        return this;
    }

    public SearchResultsPage clickOnReviewCheckbox(int score){
        String labelText = "";
        if (score == 9 || score == 8 || score == 7 || score == 6){
            labelText = $x(String.format(reviewCheckboxLabel, score)).getText();
            log.info("Click on {} (score is 9 or 8 or 7 or 6)", labelText);
            $x(String.format(reviewCheckbox, labelText)).click();
        } else {
            labelText = $x(String.format(reviewCheckboxLabel, 9)).getText();
            log.info("Click on {} (score is not 9 or 8 or 7 or 6)", labelText);
            $x(String.format(reviewCheckbox, labelText)).click();
        }

        return this;
    }

    public List<Integer> getPrices(){
        List<Integer> prices = new ArrayList<>();
        for(SelenideElement elem: priceElements){
            prices.add(Integer.parseInt(elem.getText().replaceAll("[^0-9]", "")));
        }
        log.info("Get prices collection {}", prices);
        return prices;
    }

    public List<Double> getRates(){
        List<Double> rates = new ArrayList<>();
        for(SelenideElement elem: reviewScores){
            rates.add(Double.parseDouble(elem.getText().replaceAll("[^0-9]", "")));
        }
        log.info("Get rates collection {}", rates);
        return rates;
    }

    public List<Double> getSortedRatesAsc(){
        List<Double> rates = getRates();
        rates.sort(Comparator.naturalOrder());
        log.info("Get sorted(acs) rates collection {}", rates);
        return rates;
    }

    public List<Double> getSortedRatesDesc(){
        List<Double> rates = getRates();
        rates.sort(Comparator.reverseOrder());
        log.info("Get sorted(desc) rates collection {}", rates);
        return rates;
    }

    public List<Integer> getSortedPricesAsc(){
        List<Integer> prices = getPrices();
        prices.sort(Comparator.naturalOrder());
        log.info("Get sorted(asc) prices collection {}", prices);
        return prices;
    }

    public List<Integer> getSortedPricesDesc(){
        List<Integer> prices = getPrices();
        prices.sort(Comparator.reverseOrder());
        log.info("Get sorted(desc) prices collection {}", prices);
        return prices;
    }

    public SearchResultsPage clickOnSortByLowestPriceButton(){
        log.info("Click on 'sort' button");
        new Button().click(sortButton);
        log.info("Click on button 'sort by lowest price'");
        sortByLowestPriceButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByHighestPriceButton(){
        log.info("Click on 'sort' button");
        new Button().click(sortButton);
        log.info("Click on button 'sort by highest price'");
        sortByHighestPriceButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByLowestRateButton(){
        log.info("Click on 'sort' button");
        new Button().click(sortButton);
        log.info("Click on button 'sort by lowest rate'");
        sortByLowestRateButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByHighestRateButton(){
        log.info("Click on 'sort' button");
        new Button().click(sortButton);
        log.info("Click on button 'sort by highest rate'");
        sortByHighestRateButton.click();
        return this;
    }
}
