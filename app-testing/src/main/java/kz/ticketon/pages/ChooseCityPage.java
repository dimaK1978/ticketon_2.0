package kz.ticketon.pages;

import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.utils.NamedUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ChooseCityPage {
    private final Languages language;
    private final String AccordeonCity = "//android.widget.TextView[@resource-id='android:id/text1' and @text='%s']";
    private final String ChooseCity = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']";
    private final String ButtonNext = "//android.widget.TextView[@text='%s']";

    public ChooseCityPage(Languages language) {
        this.language = language;
    }

    public Languages getLanguage() {
        return language;
    }

    @Step("Выбор города при запуске приложения ")
    public MainScreenAppPage selectCity(final Cities city) {
        final String startCityString = switch (language) {
            case KZ -> "Алматы";
            case ENG -> "Almaty";
            default -> "Алматы";
        };

        $x(String.format(AccordeonCity, startCityString)).shouldBe(visible).click();
        $x(String.format(ChooseCity, NamedUtils.getCityNameByLanguage(city, language))).shouldBe(visible).click();

        final String nameButtonString = switch (language) {
            case KZ -> "БАСТАУ";
            case ENG -> "START";
            default -> "НАЧАТЬ";
        };
        $x(String.format(ButtonNext, nameButtonString)).shouldBe(visible).click();

        return new MainScreenAppPage(language, city);
    }
}
