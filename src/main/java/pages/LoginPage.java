package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage{
    private static final SelenideElement SUBMIT_BUTTON = $x("//button[@type=\"submit\"]");
    private static final SelenideElement LOGIN_ERROR_MESSAGE = $(By.id("username-note"));

    public LoginPage openLoginPage(String url) {
        log.info("Open url {}", url);
        open(url);
        acceptCookiesIfVisible();
        return this;
    }

    public LoginPage isOpened() {
        log.info("Check if page was opened");
        SUBMIT_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    private LoginPage fillLoginForm(String email) {
        isOpened();
        log.info("Fill email input with {}", email);
        new Input("email").write(email);
        log.info("Click on submit button");
        new Button().click(SUBMIT_BUTTON);
        return this;
    }

    public VerificationEmailPage login(String email) {
        fillLoginForm(email);
        return new VerificationEmailPage();
    }

    public String getLoginErrorMessage(){
        try {
            log.info("Get login error message {}", LOGIN_ERROR_MESSAGE.getText());
            return LOGIN_ERROR_MESSAGE.getText();
        } catch (Exception e) {
            log.info("Failed to get login error message");
            return "";
        }
    }
}
