package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage extends BasePage{
    private static final SelenideElement SUBMIT_BUTTON = $x("//button[@type=\"submit\"]");
    public static final SelenideElement LOGIN_ERROR_MESSAGE = $(By.id("username-note"));

    public LoginPage openLoginPage(String url) {
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public LoginPage isOpened() {
        SUBMIT_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    private LoginPage fillLoginForm(String email) {
        isOpened();
        new Input("email").write(email);
        new Button().click(SUBMIT_BUTTON);
        return this;
    }

    public VerificationEmailPage login(String email) {
        fillLoginForm(email);
        return new VerificationEmailPage();
    }
}
