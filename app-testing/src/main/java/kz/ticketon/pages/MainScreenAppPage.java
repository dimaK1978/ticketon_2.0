package kz.ticketon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainScreenAppPage {
    private final Cities city;
    private final Languages language;
    private final String xpathChapterMenu = "//android.widget.TextView[@text='%s']";
    private final SelenideElement clickMainMenu = $(By.className("android.view.ViewGroup"));

    public MainScreenAppPage(final Languages language, final Cities city) {
        this.city = city;
        this.language = language;
    }

    public Languages getLanguage() {
        return language;
    }

    @Step("Клик для открытия главного меню")
    public MainScreenAppPage clickMenu() {
        clickMainMenu.click();
        return this;
    }

    @Step("Выбор пункта меню 'События'")
    public EventsPage clickEvents() {
        final String eventString = switch (language) {
            case KZ -> "Оқиғалар";
            case ENG -> "Events";
            default -> "События";
        };

        $x(String.format(xpathChapterMenu, eventString)).click();
        return new EventsPage(language, city);

    }
}