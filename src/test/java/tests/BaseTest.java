package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.SearchResultsPage;
import steps.LoginSteps;
import steps.SearchResultsSteps;
import steps.SearchSteps;
import utils.Preconditions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

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
        Configuration.headless = true;
        initPages();
    }

    @AfterMethod
    public void endTest() {
        getWebDriver().quit();
    }
}
