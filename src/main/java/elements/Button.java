package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@Log4j2
public class Button {
    public Button() {
    }

    public void click(SelenideElement element) {
        element.shouldBe(interactable, Duration.ofSeconds(10))
                .scrollIntoView("{behavior: 'auto', block: 'center'}");

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Click intercepted, trying JS click...");
            executeJavaScript("arguments[0].click();", element);
        }
    }
}
