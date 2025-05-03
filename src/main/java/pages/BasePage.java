package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class BasePage {

    public void acceptCookiesIfVisible() {
        log.info("Accept cookies if visible");
        SelenideElement banner = $(By.id("onetrust-banner-sdk"));
        try {
            if (banner.should(appear, Duration.ofSeconds(5)).isDisplayed()) {
                SelenideElement acceptBtn = banner.$(By.id("onetrust-accept-btn-handler"));
                acceptBtn.shouldBe(visible, Duration.ofSeconds(3));
                acceptBtn.click();
                log.info("Cookie banner accepted");
            }
        } catch (Exception e) {
            log.warn("Cookie banner not found or already dismissed");
        }
    }
}
