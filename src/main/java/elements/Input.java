package elements;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Input {
    private String label;
    private String inputLocator = "//input[@type='%s']";

    public Input(String label){
        this.label = label;
        inputLocator = String.format(inputLocator, label);
    }

    public Input write(String text) {
        $x(inputLocator).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input clear() {
        SelenideElement element = $x(inputLocator);
        element.click();
        element.clear();
        return this;
    }
}
