package steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.DateParser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pages.SearchPage.ENTER_CITY_FIELD_ALERT;

public class SearchSteps {
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    public SearchSteps() {
        searchPage = new SearchPage();
        searchResultsPage = new SearchResultsPage();
    }

    @Step("Search by {city} and from {dateStart} to {dateEnd}")
    public void doSearch(String city, String url, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);
        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton()
                .isOpened()
                .closeModalPage();
    }

    @Step("Search by {city} and from {dateStart} to {dateEnd}")
    private void doSearchWithoutCityAndDate(String url){
        searchPage
                .openSearchPage(url)
                .writeCity("")
                .clickOnFindButton();
    }

    @Step("Search by {city} without dates")
    private void doSearchWithoutDates(String url, String city){
        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .clickOnFindButton()
                .isOpened()
                .closeModalPage();
    }

    @Step("Search without city but from {dateStart} to {dateEnd}")
    private void doSearchWithoutCity(String url, String dateStart, int duration){
        LocalDate date = DateParser.parseDate(dateStart);
        searchPage
                .openSearchPage(url)
                .selectDateStart(date)
                .selectDateEnd(date.plusDays(duration))
                .clickOnFindButton();
    }

    @Step("Check search results page after search with correct data")
    public void checkFoundResultsPage(String city, String url, String dateStart, int duration){
        doSearch(city, url, dateStart, duration);
        Pattern pattern = Pattern.compile(String.format("%s: \\d{1,3}(,\\d{3})* properties found", city));
        Matcher matcher = pattern.matcher(searchResultsPage.getTextFromResultsPage());
        Assert.assertTrue(matcher.find());
    }

    @Step("Check page after search without entering city and date")
    public void checkWithoutCityAndDate(String url, String message){
        doSearchWithoutCityAndDate(url);
        Assert.assertEquals(ENTER_CITY_FIELD_ALERT.getText(), message);
    }

    @Step("Check page after search without entering date")
    public void checkSearchWithoutDates(String url, String city){
        doSearchWithoutDates(url,city);
        Pattern pattern = Pattern.compile(String.format("%s: \\d{1,3}(,\\d{3})* properties found", city));
        Matcher matcher = pattern.matcher(searchResultsPage.getTextFromResultsPage());
        System.out.println(searchResultsPage.getTextFromResultsPage());
        Assert.assertTrue(matcher.find());
    }

    @Step("Check page after search without entering city")
    public void checkWithoutCity(String url, String date, int duration, String message){
        doSearchWithoutCity(url, date, duration);
        Assert.assertEquals(ENTER_CITY_FIELD_ALERT.getText(), message);
    }

    @Step("Check maximum number of adults")
    public void checkMaxLimitOfAdults(String url){
        searchPage.openSearchPage(url)
                .clickOnChooseAmountOfGuestsButton()
                .increaseAmountOfAdultsUntilLimit();
        int amount = searchPage.getAmountOfAdults();

        Assert.assertEquals(amount, 30);
    }

    @Step("Check minimum number of adults")
    public void checkMinLimitOfAdults(String url){
        searchPage.openSearchPage(url)
                .clickOnChooseAmountOfGuestsButton()
                .decreaseAmountOfAdultsUntilLimit();
        int amount = searchPage.getAmountOfAdults();

        Assert.assertEquals(amount, 1);
    }

    @Step("Check maximum number of children")
    public void checkMaxLimitOfChildren(String url){
        searchPage.openSearchPage(url)
                .clickOnChooseAmountOfGuestsButton()
                .increaseAmountOfChildrenUntilLimit();
        int amount = searchPage.getAmountOfChildren();
        Assert.assertEquals(amount, 10);
    }

    @Step("Check minimum number of children")
    public void checkMinLimitOfChildren(String url){
        searchPage.openSearchPage(url)
                .clickOnChooseAmountOfGuestsButton()
                .decreaseAmountOfChildrenUntilLimit();
        int amount = searchPage.getAmountOfChildren();

        Assert.assertEquals(amount, 0);
    }

    @Step("Check amount of 'select children age' input")
    public void checkAmountOfSelectChildrenAgeInputs(String url){
        searchPage.openSearchPage(url)
                .clickOnChooseAmountOfGuestsButton()
                .increaseAmountOfChildrenUntilLimit()
                .clickOnChooseAmountOfGuestsButton();
        int amount = searchPage.getAmountOfChildren();

        Assert.assertEquals(searchPage.getAmountOfSelectChildrenAgeInputs(), amount);
    }
}
