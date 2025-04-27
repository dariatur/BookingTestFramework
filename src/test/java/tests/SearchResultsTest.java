package tests;

import org.testng.annotations.Test;

import static utils.ITestConstants.SEARCH_URL;

public class SearchResultsTest extends BaseTest{

    @Test
    public void filterPrice(){
        searchResultsSteps.filter(SEARCH_URL, "Paris", "16.07", 8, "100", "300");
    }

    @Test
    public void filterAndCheckByScore(){
        searchResultsSteps.filterAndCheckByScore(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test
    public void sortByLowestPrice(){
        searchResultsSteps.filterByAscendingPrice(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test
    public void sortByHighestPrice(){
        searchResultsSteps.filterByDescendingPrice(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test
    public void sortByLowestRate(){
        searchResultsSteps.filterByAscendingRate(SEARCH_URL, "Paris", "16.07", 8);
    }

    @Test
    public void sortByHighestRate(){
        searchResultsSteps.filterByDescendingRate(SEARCH_URL, "Paris", "16.07", 8);
    }
}
