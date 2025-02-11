package kz.ticketon.pages.concerts;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import org.openqa.selenium.By;

public class ChapterConcertsPage extends ChapterPage {
    public ChapterConcertsPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "concerts";
        super.pageTitleRus = "Билеты на концерт";
        super.pageTitleEng = "Concert tickets";
        super.pageTitleKz = "Концертке билеттер";
    }

    @Override
    @Step("Клик на доступное событие")
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollTo().click();
        return new EventConcertsPage(city, language, titleMovie);
    }

    @Override
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(10);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных концертов нет");
        }
        return clickEvent(eventList.first());
    }
}
