package tests;

import org.testng.annotations.Test;

import static utils.ITestConstants.SEARCH_URL;

public class SearchTest extends BaseTest{

    @Test
    public void search(){
        searchSteps.checkSearhResultsPage("Amsterdam", SEARCH_URL, "17", "19");
        ////*[@data-date="2025-04-15"]
        //DateTime
    }
}
