package kz.ticketon.pages.museums;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.EventPage;
import org.openqa.selenium.By;

public class ChapterMuseumsPage extends ChapterPage {
    public ChapterMuseumsPage(Cities city, Languages language) {
        super(city, language);
        super.shortPageUrl = "museums";
        super.pageTitleRus = "Музеи";
        super.pageTitleEng = "Museums tickets";
        super.pageTitleKz = "Мұражайлар";
    }

    @Override
    @Step("Клик на доступное событие")
    public EventPage clickEvent(final SelenideElement movie) {
        final String titleMovie = movie.$(new By.ByTagName("a")).getAttribute("title");
        movie.scrollTo().click();
        return new EventMuseumPage(city, language, titleMovie);
    }

    @Override
    public EventPage clickFirstEvent() {
        SleepUtils.sleepSeconds(10);
        if (eventList.isEmpty()) {
            throw new RuntimeException("Доступных музейных экскурсий и выставок нет");
        }
        return clickEvent(eventList.first());
    }
}
