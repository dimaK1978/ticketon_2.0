package kz.ticketon;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import kz.ticketon.pages.ChooseCityPage;
import kz.ticketon.pages.ChooseLanguagePage;
import kz.ticketon.pages.EventsPage;
import kz.ticketon.pages.MainScreenAppPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class BaseClassAppTest {
    protected static AndroidDriver driver;

    @BeforeEach
    public void initialize() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "sdk_gphone64_x86_64");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "kz.glatis.ticketon");
        capabilities.setCapability("appActivity", ".MainActivity2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 🔹 Проверка доступных контекстов (NATIVE_APP, WEBVIEW и т. д.)
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println("Доступный контекст: " + context);
        }
    }

    @Step("Проверка открытия экрана выбора языка, заголовок экрана по умолчанию на русском")
    public ChooseLanguagePage checkLanguagePage(final ChooseLanguagePage chooseLanguagePage) {
        try {
            chooseLanguagePage.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//android.widget.TextView[@text='Выберите язык']"
            )));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return chooseLanguagePage;
    }

    @Step("Проверка открытия экрана устаноки города")
    public ChooseCityPage checkChooseCityPage(final ChooseCityPage chooseCityPage) {
        final String xpathButtonChooseCity = "//android.widget.TextView[@text='%s']";
        final String titleString = switch (chooseCityPage.getLanguage()) {
            case KZ -> "Қаланы таңдаңыз";
            case ENG -> "Choose city";
            default -> "Выберите город";
        };

        try {
            chooseCityPage.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath(String.format(xpathButtonChooseCity, titleString))
            ));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return chooseCityPage;
    }

    @Step("Проверка открытия экрана главной страницы приложения")
    public MainScreenAppPage checkMainPage(final MainScreenAppPage mainScreenAppPage) {
        final String xpathTitle = "//android.widget.TextView[@text='%s']";
        final String titleString = switch (mainScreenAppPage.getLanguage()) {
            case KZ -> "Басты";
            case ENG -> "Home";
            default -> "Главная";
        };
        try {
            mainScreenAppPage.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath(String.format(xpathTitle, titleString))
            ));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return mainScreenAppPage;
    }

    @Step("Проверка открытия экрана 'События'")
    public EventsPage checkEvents(final EventsPage eventsPage) {
        final String xpathTitle = "//android.widget.TextView[@text='%s']";

        final String titleString = switch (eventsPage.getLanguage()) {
            case KZ -> "Оқиғалар";
            case ENG -> "Events";
            default -> "События";
        };
        try {
            eventsPage.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath(String.format(xpathTitle, titleString))
            ));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return eventsPage;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
