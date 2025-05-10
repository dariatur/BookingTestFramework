package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.DateParser;

import java.time.LocalDate;
import java.util.List;

public class SearchResultsSteps {
    private SearchResultsPage searchResultsPage;
    private SearchPage searchPage;

    public SearchResultsSteps() {
        searchResultsPage = new SearchResultsPage();
        searchPage = new SearchPage();
    }

    @Step("Search in {city}, on {dateStart} for {duration} days filter by min price {minPrice} and max price {maxPrice} and check results")
    public void filterByPriceAndCheckResults(String url, String city, String dateStart, int duration, String minPrice, String maxPrice){
        LocalDate date = DateParser.parseDate(dateStart);
        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .setPriceInputRangeMin(minPrice)
                .setPriceInputRangeMax(maxPrice);

        List<Integer> prices = searchResultsPage.getPrices();
        int maxPriceForDuration = Integer.parseInt(maxPrice) * duration;
        SoftAssert softAssert = new SoftAssert();
        for(Integer price : prices){
            softAssert.assertTrue(price <= maxPriceForDuration);
        }
    }

    @Step("Search in {city}, on {dateStart} for {duration} days and filter by score {score}")
    public void filterAndCheckByScore(String url, String city, String dateStart, int duration, int score){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnReviewCheckbox(score);
        ElementsCollection scores = searchResultsPage.getReviewScores();

        SoftAssert softAssert = new SoftAssert();
        for (SelenideElement elem: scores) {
            softAssert.assertTrue(Double.parseDouble(elem.getText().split("Scored ")[0]) >= 8);
        }
    }

    @Step("Search in {city}, on {dateStart} for {duration} days and sort by ascending price")
    public void sortByAscendingPrice(String url, String city, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnSortByLowestPriceButton();

        List<Integer> prices = searchResultsPage.getPrices();
        List<Integer> sortedPrices = searchResultsPage.getSortedPricesAsc();

        Assert.assertEquals(prices,sortedPrices);
    }

    @Step("Search in {city}, on {dateStart} for {duration} days and sort by descending price")
    public void sortByDescendingPrice(String url, String city, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnSortByHighestPriceButton();

        List<Integer> prices = searchResultsPage.getPrices();
        List<Integer> sortedPrices = searchResultsPage.getSortedPricesDesc();

        Assert.assertEquals(prices,sortedPrices);
    }

    @Step("Search in {city}, on {dateStart} for {duration} days and sort by ascending rate")
    public void sortByAscendingRate(String url, String city, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnSortByLowestRateButton();

        List<Double> rates = searchResultsPage.getRates();
        List<Double> sortedRates = searchResultsPage.getSortedRatesAsc();

        Assert.assertEquals(rates, sortedRates);
    }

    @Step("Search in {city}, on {dateStart} for {duration} days and sort by descending rate")
    public void sortByDescendingRate(String url, String city, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnSortByHighestRateButton();

        List<Double> rates = searchResultsPage.getRates();
        List<Double> sortedRates = searchResultsPage.getSortedRatesDesc();

        Assert.assertEquals(rates, sortedRates);
    }
}
