package kz.ticketon.pages.entertainment;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import kz.ticketon.pages.concerts.EventConcertsPage;
import org.openqa.selenium.By;

public class ChapterEntertainmentPage extends ChapterPage {
    public ChapterEntertainmentPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "entertainment";
        super.pageTitleRus = "Развлечения";
        super.pageTitleEng = "Entertainment";
        super.pageTitleKz = "Ойын-сауықтар";
    }

    @Override
    @Step("Клик на доступное событие")
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollTo().click();
        return new EventConcertsPage(city, language, titleMovie);
    }

    //заглушка, для данного раздела пока полной реализации нет
    @Override
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(5);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных событий нет");
        }
        return clickEvent(eventList.first());
    }
}
