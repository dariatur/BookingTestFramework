package steps;

import entity.User;
import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Login by user: {email}")
    public void login(String email, String url) {
        loginPage
                .openLoginPage(url)
                .login(email)
                .isOpened();
    }

    @Step("Login by user: {email}")
    public void successfulLogin(User user, String url) {
        loginPage
                .openLoginPage(url)
                .login(user.getEmail())
                .isOpened();
    }

    @Step("Login by user: {email}")
    public void login(User user, String url) {
        loginPage
                .openLoginPage(url)
                .login(user.getEmail());
    }
}
