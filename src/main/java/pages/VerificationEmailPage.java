package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class VerificationEmailPage extends BasePage{
    private static final SelenideElement VERIFY_YOUR_EMAIL_TEXT = $x("//h1[text()=\"Verify your email address\"]");

    public VerificationEmailPage isOpened(){
        acceptCookiesIfVisible();
        VERIFY_YOUR_EMAIL_TEXT.shouldBe(Condition.visible);
        log.info("Checking if verification email page is opened");
        return this;
    }

    public String getVerifyEmailTest(){
        return VERIFY_YOUR_EMAIL_TEXT.getText();
    }
}
