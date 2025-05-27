package steps;

import entity.User;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.LoginPage;
import pages.VerificationEmailPage;

public class LoginSteps {
    private LoginPage loginPage;
    private VerificationEmailPage verificationEmailPage;

    public LoginSteps() {
        loginPage = new LoginPage();
        verificationEmailPage = new VerificationEmailPage();
    }

    @Step("Login by email: {email}")
    private void login(String email, String url) {
        loginPage
                .openLoginPage(url)
                .login(email)
                .isOpened();
    }

    @Step("Login by user: {user}")
    private void successfulLogin(User user, String url) {
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
    public void loginAndCheckErrorMessage(User user, String url, String errorMessage){
        login(user, url);
        Assert.assertEquals(loginPage.getLoginErrorMessage(), errorMessage);
    }

    @Step("Login by user with correct email: {user} and check message on the page")
    public void loginAndCheckMessageAfterLogin(User user, String url, String message){
        successfulLogin(user, url);
        Assert.assertEquals(verificationEmailPage.getVerifyEmailTest(), message);
    }
}
