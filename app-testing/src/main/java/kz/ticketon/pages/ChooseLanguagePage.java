package kz.ticketon.pages;

import io.qameta.allure.Step;
import kz.ticketon.Languages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ChooseLanguagePage {

    private final String xpathChooseLanguage = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']";
    private final String xpathButtonNext = "//android.widget.TextView[@text='%s']";

    @Step("Выбор языка при открытии")
    public ChooseCityPage selectLanguage(final Languages language) {
        $x("//android.widget.TextView[@resource-id='android:id/text1']").shouldBe(visible).click();

        final String languageString = switch (language) {
            case KZ -> "Қазақ";
            case ENG -> "English";
            default -> "Русский";
        };
        $x(String.format(xpathChooseLanguage, languageString)).click();

        final String nameButtonString = switch (language) {
            case KZ -> "ӘРІ ҚАРАЙ";
            case ENG -> "NEXT";
            default -> "ДАЛЕЕ";
        };
        $x(String.format(xpathButtonNext, nameButtonString)).click();

        return new ChooseCityPage(language);
    }
}