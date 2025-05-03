package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Listeners;
import steps.LoginSteps;
import steps.SearchResultsSteps;
import steps.SearchSteps;
import utils.Preconditions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners({AllureTestNg.class})
public class BaseTest extends Preconditions {
    protected LoginSteps loginSteps;
    protected SearchSteps searchSteps;
    protected SearchResultsSteps searchResultsSteps;

    public void initPages() {
        loginSteps = new LoginSteps();
        searchSteps = new SearchSteps();
        searchResultsSteps = new SearchResultsSteps();
    }

    @BeforeMethod
    public void initTest(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--lang=en");
        prefs.put("intl.accept_languages", "en");
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        setWebDriver(driver);

        Configuration.timeout = 15000;
        Configuration.headless = false;
        initPages();
    }

    @AfterMethod
    public void endTest() {
        getWebDriver().quit();
    }
}
