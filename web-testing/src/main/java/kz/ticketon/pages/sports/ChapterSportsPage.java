package kz.ticketon.pages.sports;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import org.openqa.selenium.By;

public class ChapterSportsPage extends ChapterPage {
    public ChapterSportsPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "sports";
        super.pageTitleRus = "Билеты на спорт";
        super.pageTitleEng = "Sports tickets";
        super.pageTitleKz = "Спорт билеттері";
    }

    @Override
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollTo().click();
        return new EventSportsPage(city, language, titleMovie);
    }

    @Override
    @Step("Клик на доступное событие")
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(10);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных спортивных мероприятий нет");
        }
        return clickEvent(eventList.first());
    }
}
