package kz.ticketon.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public abstract class EventPage extends BaseTemlatePage {

    protected final String title;
    protected ElementsCollection availableSessions;
    protected String stringForFindLocation;
    protected final SelenideElement titleEvent = $x("//h1");

    public EventPage(final Cities city, final Languages language, final String title) {
        super(city, language);
        this.title = title;
    }

    public SessionPage getFirstSessionEvent() {
        return getSessionMovie(availableSessions.first());
    }

    @Step("Выбор доступного сеанса, переход к форме выбора мест и приобретения билетов")
    public SessionPage getSessionMovie(final SelenideElement selenideElement) {
        if (availableSessions.isEmpty()) {
            throw new RuntimeException("Нет доступных сеансов");
        }
        final SelenideElement buttonSession = selenideElement.find("span[class='TicketButton_text__HwCFn']");
        if (!buttonSession.scrollIntoView(true).isDisplayed()) {
            throw new RuntimeException("Не удалось выбрать сеанс");
        }
        final String time = buttonSession.getOwnText();
        final String day = selenideElement.find("div[class='Date_day__GAPLg']").getText();
        final String month = selenideElement.find("div[class='Date_dateText__h6416']").$(new By.ByTagName("div")).getText();
        final String eventLocation = selenideElement.find(stringForFindLocation).getText();
        buttonSession.scrollIntoView(true).click();
        SleepUtils.sleepSeconds(15);
        return createSesionPage(time, day, month, eventLocation);
    }

    protected abstract SessionPage createSesionPage(
            final String time,
            final String day,
            final String month,
            final String eventLocation
    );

    @Step("Получение атуального названия события")
    public String getTitleActual() {
        return titleEvent.getText();
    }

    @Step("Получение ожидаемого названия события")
    public String getTitleExpect() {
        return title;
    }
}
