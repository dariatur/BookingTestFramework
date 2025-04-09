package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage {
    private static SelenideElement MODAL_TEXT = $x("//div[text()='Sign in, save money']");
    private SelenideElement resultText = $x("//h1");

    public SearchResultsPage isOpened() {
        MODAL_TEXT.shouldBe(Condition.visible);
        return this;
    }

    public SearchResultsPage closeModalPage(){
        if(MODAL_TEXT.is(Condition.visible)){
            $x("//button[@aria-label=\"Dismiss sign in information.\"]").click();
        }
        return this;
    }

    public String getTextFromResultsPage(){
        return resultText.getText();
    }

}
