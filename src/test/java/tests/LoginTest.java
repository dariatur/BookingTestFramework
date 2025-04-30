package tests;

import org.testng.annotations.Test;
import utils.ITestConstants;

public class LoginTest extends BaseTest implements ITestConstants {

//    @Test(description = "login with correct email")
//    public void loginAndCheckMessageAfterLogin(){
//        loginSteps.checkMessageAfterLogin(userSuccess, LOGIN_URL, SUCCESSFUL_LOGIN_TEXT);
//    }

    @Test(description = "login with incorrect email")
    public void loginAndCheckErrorMessage(){
        loginSteps.checkErrorMessage(userIncorrectFields, LOGIN_URL, INCORRECT_EMAIL_ERROR);
    }

    @Test(description = "login with empty email field")
    public void loginWithEmptyEmailAndCheckErrorMessage(){
        loginSteps.checkErrorMessage(userWithEmptyEmail, LOGIN_URL, EMPTY_EMAIL_ERROR);
    }
}
