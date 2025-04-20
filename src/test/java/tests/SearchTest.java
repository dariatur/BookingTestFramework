package tests;

import org.testng.annotations.Test;

import static utils.ITestConstants.EMPTY_CITY_FIELD_ALERT_TEXT;
import static utils.ITestConstants.SEARCH_URL;

public class SearchTest extends BaseTest{

    @Test(description = "Search with correct data")
    public void searchAndCheckFoundResultsPage(){
        searchSteps.checkFoundResultsPage("Amsterdam", SEARCH_URL, "17.06", 6);
        //DateTime(2,5,2025)
    }

    @Test(description = "Search without entering city and dates")
    public void searchAndCheckWithoutCityAndDate(){
        searchSteps.checkWithoutCityAndDate(SEARCH_URL, EMPTY_CITY_FIELD_ALERT_TEXT);
    }

    @Test(description = "Search without entering dates")
    public void searchAndCheckWithoutDate(){
        searchSteps.checkSearchWithoutDates(SEARCH_URL, "Amsterdam");
    }

    @Test(description = "Search without entering city")
    public void searchAndCheckWithoutCity(){
        searchSteps.checkWithoutCity(SEARCH_URL, "17.06", 6, EMPTY_CITY_FIELD_ALERT_TEXT);
    }

}
