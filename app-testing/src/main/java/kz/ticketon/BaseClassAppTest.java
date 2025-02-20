package kz.ticketon;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import kz.ticketon.pages.ChooseCityPage;
import kz.ticketon.pages.ChooseLanguagePage;
import kz.ticketon.pages.EventsPage;
import kz.ticketon.pages.MainScreenAppPage;
import kz.ticketon.utils.PropertiesUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class BaseClassAppTest {
    protected static AndroidDriver driver;

    final SelenideElement textCheckRu = $x("//android.widget.TextView[@text='Выберите язык']");
    final SelenideElement textCheck = $x("//android.widget.TextView");

    @BeforeEach
    public void initialize() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", PropertiesUtil.get("platform.name"));
        capabilities.setCapability("deviceName", PropertiesUtil.get("device.name"));
        capabilities.setCapability("platformVersion", PropertiesUtil.get("platform.version"));
        capabilities.setCapability("automationName", PropertiesUtil.get("automation.name"));
        capabilities.setCapability("appPackage", PropertiesUtil.get("app.package"));
        capabilities.setCapability("appActivity", PropertiesUtil.get("app.activity"));

        driver = new AndroidDriver(new URL(PropertiesUtil.get("appium.url")), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Подключаем драйвер к Selenide
        WebDriverRunner.setWebDriver(driver);
        // Проверка доступных контекстов (NATIVE_APP, WEBVIEW и т. д.)
        printAvailableContexts();
    }

    @Step("Вывод доступных контекстов")
    private void printAvailableContexts() {
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println("Доступный контекст: " + context);
        }
    }

    @Step("Проверка открытия экрана выбора языка")
    public ChooseLanguagePage checkLanguagePage (final ChooseLanguagePage chooseLanguagePage){
        try {
            textCheckRu.shouldHave(text("Выберите язык"), Duration.ofSeconds(50));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return chooseLanguagePage;
    }

    @Step("Проверка выбора города")
    public ChooseCityPage checkChooseCityPage (final ChooseCityPage chooseCityPage){
        final String titleString = switch (chooseCityPage.getLanguage()) {
            case KZ -> "Қаланы таңдаңыз";
            case ENG -> "Choose city";
            default -> "Выберите город";
        };

        try {
            textCheck.shouldHave(text(titleString), Duration.ofSeconds(50));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return chooseCityPage;
    }

    @Step("Проверка главного экрана")
    public MainScreenAppPage checkMainPage (final MainScreenAppPage mainScreenAppPage){
        final String titleString = switch (mainScreenAppPage.getLanguage()) {
            case KZ -> "Басты";
            case ENG -> "Home";
            default -> "Главная";
        };

        try {
            textCheck.shouldHave(text(titleString), Duration.ofSeconds(50));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return mainScreenAppPage;
    }

    @Step("Проверка экрана 'События'")
    public EventsPage checkEvents (final EventsPage eventsPage){
        final String titleString = switch (eventsPage.getLanguage()) {
            case KZ -> "Оқиғалар";
            case ENG -> "Events";
            default -> "События";
        };

        try {
            textCheck.shouldHave(text(titleString), Duration.ofSeconds(50));
        } catch (Exception e) {
            throw new RuntimeException("Ожидаемый заголовок экрана не найден");
        }
        return eventsPage;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}