package tests;

import org.testng.annotations.Test;

import static utils.ITestConstants.SEARCH_URL;

public class SearchResultsTest extends BaseTest{

    @Test(description = "search for accommodation and then filter by min and max price")
    public void filterPrice(){
        searchResultsSteps.filterByPriceAndCheckResults(SEARCH_URL, "Paris", "16.07", 8, "200", "600");
    }

    @Test(description = "search for accommodation and then filter by score")
    public void filterAndCheckByScore(){
        searchResultsSteps.filterAndCheckByScore(SEARCH_URL, "Paris", "16.07", 8, 9);
    }

    @Test(description = "search for accommodation and then sort by ascending price")
    public void sortByLowestPrice(){
        searchResultsSteps.sortByAscendingPrice(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test(description = "search for accommodation and then sort by descending price")
    public void sortByHighestPrice(){
        searchResultsSteps.sortByDescendingPrice(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test(description = "search for accommodation and then sort by ascending rate")
    public void sortByLowestRate(){
        searchResultsSteps.sortByAscendingRate(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test(description = "search for accommodation and then sort by descending rate")
    public void sortByHighestRate(){
        searchResultsSteps.sortByDescendingRate(SEARCH_URL, "Paris", "16.07", 8);
    }
}
