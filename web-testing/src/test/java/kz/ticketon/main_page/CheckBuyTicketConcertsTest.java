package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;
import kz.ticketon.pages.MainPage;
import kz.ticketon.pages.SessionPage;
import kz.ticketon.pages.concerts.ChapterConcertsPage;
import kz.ticketon.pages.concerts.EventConcertsPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Выбор и приобремение билетов")
public class CheckBuyTicketConcertsTest extends BaseClassWebTest {

    @Story("Проверка выбора и приобретения билетов на доступный концерт")
    @ParameterizedTest()
    @EnumSource(Cities.class)
    public void checkBuyTicketConcerts(Cities city) {
        SoftAssertions softAssertions = new SoftAssertions();
        final MainPage mainPage = new MainPage(city, Languages.RUS);
        mainPage.openPage();
        final ChapterConcertsPage concertsPage = (ChapterConcertsPage) mainPage.clickMainMenuButton(MainMenuButtonsMainPage.CONCERTS);
        final EventConcertsPage concert = (EventConcertsPage) concertsPage.clickFirstEvent();
        checkEventPageTitle(concert, softAssertions);
        final SessionPage sessionConcert = concert.getFirstSessionEvent();
        checkCreateSessionEventsAddAndDelTickets(sessionConcert, softAssertions);
        checkMakingOrdere(sessionConcert, softAssertions);
        softAssertions.assertAll();
    }
}


