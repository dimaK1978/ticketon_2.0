package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

@Feature("Работоспособность основных элемнтов главной страницы")
public class CheckSearchEventsTest extends BaseClassWebTest {
    @Test()
    @Story("Проверка элемента поиска событий")
    public void CheckSearchEvents() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(Cities.NO_CITY, Languages.RUS);
        mainPage.openPage();
        String eventStringTitle = mainPage.getEventTitle();
        checkSearchEventsByTitle(mainPage, eventStringTitle, softAssertions);
        softAssertions.assertAll();
    }
}

