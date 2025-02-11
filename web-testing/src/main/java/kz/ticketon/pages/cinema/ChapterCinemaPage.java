package kz.ticketon.pages.cinema;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import org.openqa.selenium.By;

public class ChapterCinemaPage extends ChapterPage {
    public ChapterCinemaPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "cinema";
        super.pageTitleRus = "Билеты в кино";
        super.pageTitleEng = "Movie tickets";
        super.pageTitleKz = "Киноға билеттер";
    }

    @Override
    @Step("Клик на доступное событие")
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollIntoView(true).click();
        return new EventCinemaPage(city, language, titleMovie);
    }

    @Override
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(10);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных фильмов нет");
        }
        return clickEvent(eventList.first());
    }
}
