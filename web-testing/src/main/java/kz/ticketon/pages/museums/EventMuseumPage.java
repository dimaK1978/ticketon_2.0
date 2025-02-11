package kz.ticketon.pages.museums;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.EventPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.*;

public class EventMuseumPage extends EventPage {
    private final SelenideElement frameNewFormChooseTicket = $("[id='frame_ticketonWidgetContainer']");
    private final SelenideElement frameOldFormChooseTicket = $("[id='widgetFrame']");

    public EventMuseumPage(final Cities city, final Languages language, final String title) {
        super(city, language, title);
        availableSessions = $$x("//div[@class='EventScheduleRow_eventScheduleRow__gQsT9']");
        stringForFindLocation = "div[class='Place_placeWrapper__XP_Ng']";
    }

    @Step("Открытие модального окна выбора билетов в зависимости от формы")
    @Override
    protected SessionPage createSesionPage(
            final String time,
            final String day,
            final String month,
            final String eventLocation
    ) {
        if (frameOldFormChooseTicket.exists()) {
            switchTo().frame(frameOldFormChooseTicket);
            return new SessionMuseumOldFormPage(title, time, day, month, eventLocation);
        } else if (frameNewFormChooseTicket.exists()) {
            switchTo().frame(frameNewFormChooseTicket);
            return new SessionMuseumNewFormPage(title, time, day, month, eventLocation);
        } else {
            throw new RuntimeException("Форма для выбора билетов не прогрузилась");
        }
    }
}
