package elements;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Input {
    private String label;
    private String inputLocator = "//input[@type='%s']";
    private static SelenideElement CITY_INPUT = $(By.name("ss"));

    public Input(){};

    public Input(String label){
        this.label = label;
    }

    public Input write(String text) {
        $x(String.format(inputLocator, label)).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input clear() {
        SelenideElement element = $x(inputLocator);
        element.click();
        element.clear();
        return this;
    }

    public Input writeCity(String city) {
        CITY_INPUT.shouldBe(Condition.visible).setValue(city);
        return this;
    }
}
