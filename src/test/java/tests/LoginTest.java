package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ITestConstants;

import static pages.VerificationEmailPage.VERIFY_YOUR_EMAIL_TEXT;

public class LoginTest extends BaseTest implements ITestConstants {

    @Test(description = "login with correct email")
    public void loginWithCorrectEmail(){
        loginSteps.successfulLogin(userSuccess, LOGIN_URL);
        Assert.assertEquals(VERIFY_YOUR_EMAIL_TEXT.getText(), "Verify your email address");
    }

    @Test(description = "login with incorrect email")
    public void loginWithIncorrectEmail(){
        loginSteps.login(userIncorrectFields, LOGIN_URL);
        Assert.assertEquals((LoginPage.LOGIN_ERROR_MESSAGE).getText(), "Make sure the email address you entered is correct.");
    }

    @Test(description = "login with empty email field")
    public void loginWithEmptyEmail(){
        loginSteps.login(userWithEmptyEmail, LOGIN_URL);
        Assert.assertEquals((LoginPage.LOGIN_ERROR_MESSAGE).getText(), "Enter your email address");
    }
}
