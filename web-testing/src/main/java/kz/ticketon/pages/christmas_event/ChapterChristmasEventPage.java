package kz.ticketon.pages.christmas_event;

import com.codeborne.selenide.SelenideElement;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import kz.ticketon.pages.cinema.EventCinemaPage;
import kz.ticketon.utils.SleepUtils;
import org.openqa.selenium.By;

public class ChapterChristmasEventPage extends ChapterPage {
    public ChapterChristmasEventPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "christmas-event";
        super.pageTitleRus = "Новогодние события";
        super.pageTitleEng = "Christmas Event";
        super.pageTitleKz = "ЖАНА-ЖЫЛ";
    }

    @Override
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollTo().click();
        return new EventCinemaPage(city, language, titleMovie);
    }

    //заглушка, для данного раздела пока полной реализации нет
    @Override
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(5);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных мероприятий нет");
        }
        return clickEvent(eventList.first());
    }
}
