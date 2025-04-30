package tests;

import org.testng.annotations.Test;
import utils.ITestConstants;

public class LoginTest extends BaseTest implements ITestConstants {

    @Test(description = "login with correct email")
    public void loginAndCheckMessageAfterLogin(){
        loginSteps.loginAndCheckMessageAfterLogin(userSuccess, LOGIN_URL, SUCCESSFUL_LOGIN_TEXT);
    }

    @Test(description = "login with incorrect email")
    public void loginAndCheckErrorMessage(){
        loginSteps.loginAndCheckErrorMessage(userIncorrectFields, LOGIN_URL, INCORRECT_EMAIL_ERROR);
    }

    @Test(description = "login with empty email field")
    public void loginWithEmptyEmailAndCheckErrorMessage(){
        loginSteps.loginAndCheckErrorMessage(userWithEmptyEmail, LOGIN_URL, EMPTY_EMAIL_ERROR);
    }
}
