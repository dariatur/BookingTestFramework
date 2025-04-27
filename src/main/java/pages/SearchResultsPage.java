package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    private static SelenideElement MODAL_TEXT = $x("//div[text()='Sign in, save money']");
    private static SelenideElement MODAL_CLOSE_BUTTON = $x("//button[@aria-label=\"Dismiss sign in information.\"]");
    private SelenideElement resultText = $x("//h1");
    private SelenideElement priceInputRangeMin = $x("//input[@aria-label='Min.']");
    private SelenideElement priceInputRangeMax = $x("//input[@aria-label='Max.']");
    private SelenideElement priceInputRangeMinDot = $x("//input[@aria-label='Min.']/following-sibling::div[1]");
    private SelenideElement priceInputRangeMaxDot = $x("//input[@aria-label='Max.']/following-sibling::div[1]");
    private SelenideElement reviewCheckbox = $x("(//div[contains(@data-filters-item, 'review')]//input[@type='checkbox' and contains(@aria-label, 'Very good')])[1]");
    private ElementsCollection reviewScores = $$(By.xpath("//div[contains(text(), 'Scored')]"));
    private ElementsCollection priceElements = $$(By.xpath("span[data-testid='price-and-discounted-price']"));
    private SelenideElement sortByLowestPriceButton = $(byText("Price (lowest first)"));
    private SelenideElement sortByHighestPriceButton = $(byText("Price (highest first)"));
    private SelenideElement sortByLowestRateButton = $(byText("Property rating (low to high)"));
    private SelenideElement sortByHighestRateButton = $(byText("Property rating (high to low)"));
    private SelenideElement sortButton = $x("//button[@data-testid='sorters-dropdown-trigger']");

    public SearchResultsPage isOpened() {
        MODAL_TEXT.shouldBe(Condition.visible);
        return this;
    }

    public SearchResultsPage closeModalPage(){
        if(MODAL_TEXT.is(Condition.visible)){
            MODAL_CLOSE_BUTTON.click();
        }
        return this;
    }

    public String getTextFromResultsPage(){
        return resultText.getText();
    }

    public SearchResultsPage setPriceInputRangeMin(String value){
//        priceInputRangeMin.shouldBe(Condition.interactable);
//        Actions actions = new Actions(WebDriverRunner.getWebDriver());
//        int attempts = 0;
//        int offset = 2;
//        while (!priceInputRangeMin.getValue().equals(value) && attempts < 100) {
//            actions.dragAndDropBy(priceInputRangeMinDot, offset, 0).perform();
//            offset += 2;
//            attempts++;
//        }

        return this;
    }

    public SearchResultsPage setPriceInputRangeMax(String value){
//        priceInputRangeMax.shouldBe(Condition.interactable);
//        Actions actions = new Actions(WebDriverRunner.getWebDriver());
//        int attempts = 0;
//        int offset = -1;
//        while (!priceInputRangeMax.getValue().equals(value) && attempts < 100) {
//            actions.dragAndDropBy(priceInputRangeMaxDot, -4, 0).perform();
//            offset -= 1;
//            attempts++;
//        }
        return this;
    }

    public SearchResultsPage clickOnCheckbox(){
        reviewCheckbox.click();
        return this;
    }

    public ElementsCollection getReviewScores(){
        return reviewScores;
    }

    public List<Integer> getPrices(){
        List<Integer> prices = new ArrayList<>();
        for(SelenideElement elem: priceElements){
            prices.add(Integer.parseInt(elem.getText().replaceAll("[^0-9]", "")));
        }
        return prices;
    }

    public List<Double> getRates(){
        List<Double> rates = new ArrayList<>();
        for(SelenideElement elem: reviewScores){
            rates.add(Double.parseDouble(elem.getText().replaceAll("[^0-9]", "")));
        }
        return rates;
    }

    public List<Double> getSortedRatesAsc(){
        List<Double> rates = getRates();
        rates.sort(Comparator.naturalOrder());
        return rates;
    }

    public List<Double> getSortedRatesDesc(){
        List<Double> rates = getRates();
        rates.sort(Comparator.reverseOrder());
        return rates;
    }

    public List<Integer> getSortedPricesAsc(){
        List<Integer> prices = getPrices();
        prices.sort(Comparator.naturalOrder());
        return prices;
    }

    public List<Integer> getSortedPricesDesc(){
        List<Integer> prices = getPrices();
        prices.sort(Comparator.reverseOrder());
        return prices;
    }

    public SearchResultsPage clickOnSortByLowestPriceButton(){
        new Button().click(sortButton);
        sortByLowestPriceButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByHighestPriceButton(){
        new Button().click(sortButton);
        sortByHighestPriceButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByLowestRateButton(){
        new Button().click(sortButton);
        sortByLowestRateButton.click();
        return this;
    }

    public SearchResultsPage clickOnSortByHighestRateButton(){
        new Button().click(sortButton);
        sortByHighestRateButton.click();
        return this;
    }
}
