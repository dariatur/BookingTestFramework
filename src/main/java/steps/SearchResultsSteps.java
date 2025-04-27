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

    @Step
    public void filter(String url, String city, String dateStart, int duration, String minPrice, String maxPrice){
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
    }

    @Step
    public void filterAndCheckByScore(String url, String city, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);

        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage()
                .clickOnCheckbox();
        ElementsCollection scores = searchResultsPage.getReviewScores();

        SoftAssert softAssert = new SoftAssert();
        for (SelenideElement elem:
             scores) {
            softAssert.assertTrue(Double.parseDouble(elem.getText().split("Scored ")[0]) >= 8);
        }
    }

    @Step
    public void filterByAscendingPrice(String url, String city, String dateStart, int duration){
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

    @Step
    public void filterByDescendingPrice(String url, String city, String dateStart, int duration){
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

    @Step
    public void filterByAscendingRate(String url, String city, String dateStart, int duration){
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

    @Step
    public void filterByDescendingRate(String url, String city, String dateStart, int duration){
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
