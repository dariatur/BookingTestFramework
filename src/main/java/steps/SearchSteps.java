package steps;

import com.sun.source.tree.AssertTree;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.SearchPage;
import pages.SearchResultsPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchSteps {
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    public SearchSteps() {
        searchPage = new SearchPage();
        searchResultsPage = new SearchResultsPage();
    }

    @Step("Search by {city} and from {dateStart} to {dateEnd}")
    private void doSearch(String city, String url, String dateStart, String dateEnd){
        searchPage
                .openSearchPage(url)
                .writeCity(city)
                .selectDateStart(dateStart)
                .selectDateEnd(dateEnd)
                .clickOnFindButton()
                .isOpened()
                .closeModalPage();
    }

    @Step("Search by {city} and from {dateStart} to {dateEnd}")
    private void doSearchWithoutCity(String url){
        searchPage
                .openSearchPage(url)
                .writeCity("")
                .clickOnFindButton();
    }

    @Step()
    public void checkSearhResultsPage(String city, String url, String dateStart, String dateEnd){
        doSearch(city, url, dateStart, dateEnd);
        Pattern pattern = Pattern.compile(String.format("%s: \\d+ properties found", city));
        Matcher matcher = pattern.matcher(searchResultsPage.getTextFromResultsPage());
        Assert.assertTrue(matcher.find());
    }

}
