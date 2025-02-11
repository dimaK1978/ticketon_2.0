package kz.ticketon.pages.cinema;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.EventPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.*;

public class EventCinemaPage extends EventPage {
    private final SelenideElement frameNewFormChoosePlase = $("[id='frame_ticketonWidgetContainer']");
    private final SelenideElement frameOldFormChoosePlase = $("[id='widgetFrame']");

    public EventCinemaPage(final Cities city, final Languages language, final String title) {
        super(city, language, title);
        availableSessions = $$("div[class='ScheduleRow_scheduleRow__o3xf2']");
        stringForFindLocation = "div[class='CinemaInfo_cinemaName___Escv']";
    }
    @Step("Открытие модального окна выбора билетов в зависимости от формы")
    @Override
    protected SessionPage createSesionPage(
            final String time,
            final String day,
            final String month,
            final String eventLocation
    ) {
         /*
        На сайте одноврменно используются две разные формы выбора мест и преобретения билетов. Какая из форм
        будет использована в каждом конкретном случае заранее по этой причине приходится проверять по факту
        */
        if (frameNewFormChoosePlase.exists()) {
            switchTo().frame(frameNewFormChoosePlase);
            return new SessionMovieNewFormPage(title, time, day, month, eventLocation);
        } else if (frameOldFormChoosePlase.exists()) {
            switchTo().frame(frameOldFormChoosePlase);
            return new SessionMovieOldFormPage(title, time, day, month, eventLocation);
        } else {
            throw new RuntimeException("Форма для выбора билетов не загрузилась");
        }
    }
}
