package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ITestConstants;

import static pages.VerificationEmailPage.VERIFY_YOUR_EMAIL_TEXT;

public class LoginTest extends BaseTest implements ITestConstants {

    @Test(description = "login with correct email")
    public void loginWithCorrectEmail(){
        loginSteps.checkMessageAfterLogin(userSuccess, LOGIN_URL, SUCCESSFUL_LOGIN_TEXT);
    }

    @Test(description = "login with incorrect email")
    public void loginWithIncorrectEmail(){
        loginSteps.checkErrorMessage(userIncorrectFields, LOGIN_URL, INCORRECT_EMAIL_ERROR);
    }

    @Test(description = "login with empty email field")
    public void loginWithEmptyEmail(){
        loginSteps.checkErrorMessage(userWithEmptyEmail, LOGIN_URL, EMPTY_EMAIL_ERROR);
    }
}
