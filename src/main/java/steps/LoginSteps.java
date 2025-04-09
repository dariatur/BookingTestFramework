package steps;

import entity.User;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import static pages.VerificationEmailPage.VERIFY_YOUR_EMAIL_TEXT;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Step("Login by email: {email}")
    public void login(String email, String url) {
        loginPage
                .openLoginPage(url)
                .login(email)
                .isOpened();
    }

    @Step("Login by user: {user}")
    public void successfulLogin(User user, String url) {
        loginPage
                .openLoginPage(url)
                .login(user.getEmail())
                .isOpened();
    }

    @Step("Login by user: {user}")
    public void login(User user, String url) {
        loginPage
                .openLoginPage(url)
                .login(user.getEmail());
    }

    @Step("Login by user with incorrect email: {user} and check error message")
    public void checkErrorMessage(User user, String url, String errorMessage){
        login(user, url);
        Assert.assertEquals((LoginPage.LOGIN_ERROR_MESSAGE).getText(), errorMessage);
    }

    @Step("Login by user with correct email: {user} and check message on the page")
    public void checkMessageAfterLogin(User user, String url, String message){
        successfulLogin(user, url);
        Assert.assertEquals(VERIFY_YOUR_EMAIL_TEXT.getText(), message);
    }
}
