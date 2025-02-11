package kz.ticketon.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainScreenAppPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final Cities city;
    private final Languages language;
    private final String xpathChapterMenu = "//android.widget.TextView[@text='%s']";

    public MainScreenAppPage(final AndroidDriver driver, final Languages language, final Cities city) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.city = city;
        this.language = language;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Languages getLanguage() {
        return language;
    }

    public MainScreenAppPage checkMainPage() {
        WebElement checkMain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//android.widget.TextView[@text=\"Главная\"]"
        )));
        checkMain.isDisplayed();
        return this;
    }

    @Step("Клик для открытия пунктов главного меню")
    public MainScreenAppPage clickMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className(
                "android.view.ViewGroup"
        ))).click();
        return this;
    }

    @Step("Выбор пункта меню 'События'")
    public EventsPage clickEventsEvents() {

        final String eventString = switch (language) {
            case KZ -> "Оқиғалар";
            case ENG -> "Events";
            default -> "События";
        };
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(xpathChapterMenu, eventString))
        )).click();
        return new EventsPage(driver, language, city);
    }
}