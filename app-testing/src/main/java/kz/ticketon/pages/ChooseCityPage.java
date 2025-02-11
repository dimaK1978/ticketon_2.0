package kz.ticketon.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.NamedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChooseCityPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final Languages language;
    private final String xpahtAccordeonCity = "//android.widget.TextView[@resource-id='android:id/text1' and @text='%s']";
    private final String xpahtChooseCity = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']";
    private final String xpathButtonNext = "//android.widget.TextView[@text='%s']";

    public ChooseCityPage(AndroidDriver driver, Languages language) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.language = language;
    }

    public WebDriverWait getWait() {
        return wait;
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

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(xpahtAccordeonCity, startCityString))
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(xpahtChooseCity, NamedUtils.getCityNameByLanguage(city, language)))
        )).click();

        final String nameButtonString = switch (language) {
            case KZ -> "БАСТАУ";
            case ENG -> "START";
            default -> "НАЧАТЬ";
        };

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                String.format(xpathButtonNext, nameButtonString))
        )).click();
        return new MainScreenAppPage(driver, language, city);
    }
}
