package kz.ticketon.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainScreenPhonePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public MainScreenPhonePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step("Запуск приложения с главного экрана смартфона")
    public ChooseLanguagePage clickApp() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.TextView")))
                .click();
        return new ChooseLanguagePage(driver);
    }
}
