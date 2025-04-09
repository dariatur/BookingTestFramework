package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationEmailPage {
    public static SelenideElement VERIFY_YOUR_EMAIL_TEXT = $x("//h1[text()=\"Verify your email address\"]");

    public VerificationEmailPage isOpened(){
        VERIFY_YOUR_EMAIL_TEXT.shouldBe(Condition.visible);
        return this;
    }
}
