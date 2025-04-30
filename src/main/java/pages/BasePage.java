package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class BasePage {

    public void acceptCookiesIfVisible() {
        log.info("Accept cookies if visible");
        SelenideElement banner = $(By.id("onetrust-banner-sdk"));
        if (banner.is(Condition.visible)) {
            banner.$(By.id("onetrust-accept-btn-handler")).click();
        }
    }
}
