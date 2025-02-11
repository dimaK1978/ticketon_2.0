package kz.ticketon.pages.children;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.EventPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.*;

public class EventChildrenPage extends EventPage {
    private final SelenideElement frameNewFormChoosePlase = $("[id='frame_ticketonWidgetContainer']");
    private final SelenideElement frameFormChoosePlase = $("[id='widgetFrame']");

    public EventChildrenPage(final Cities city, final Languages language, final String title) {
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
        if (frameFormChoosePlase.exists()) {
            switchTo().frame(frameFormChoosePlase);
            return new SessionChildrenOldFormPage(title, time, day, month, eventLocation);
        } else if (frameNewFormChoosePlase.exists()) {
            switchTo().frame(frameNewFormChoosePlase);
            return new SessionChildrenNewFormPage(title, time, day, month, eventLocation);
        } else {
            throw new RuntimeException("Форма для выбора билетов");
        }
    }
}
