package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public void acceptCookiesIfVisible() {
        SelenideElement banner = $(By.id("onetrust-banner-sdk"));
        if (banner.is(Condition.visible)) {
            banner.$(By.id("onetrust-accept-btn-handler")).click();
        }
    }
}
