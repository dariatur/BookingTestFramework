package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Button {
    public Button() {
    }

    public void click(SelenideElement selenideElement) {
        selenideElement.shouldBe(Condition.visible).click();
    }
}
