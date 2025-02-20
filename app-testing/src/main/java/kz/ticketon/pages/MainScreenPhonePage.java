package kz.ticketon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainScreenPhonePage {

    private final SelenideElement appIcon = $x("//android.widget.TextView");

    @Step("Запуск приложения с главного экрана смартфона")
    public ChooseLanguagePage clickApp() {
        appIcon.click();
        return new ChooseLanguagePage();
    }
}
