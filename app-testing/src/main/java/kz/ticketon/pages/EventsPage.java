package kz.ticketon.pages;

import io.appium.java_client.android.AndroidDriver;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventsPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final Cities city;
    private final Languages language;

    public EventsPage(final AndroidDriver driver, final Languages language, final Cities city) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.language = language;
        this.city = city;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Languages getLanguage() {
        return language;
    }
}