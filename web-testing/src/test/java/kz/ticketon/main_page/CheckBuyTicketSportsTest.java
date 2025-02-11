package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;
import kz.ticketon.pages.MainPage;
import kz.ticketon.pages.SessionPage;
import kz.ticketon.pages.sports.ChapterSportsPage;
import kz.ticketon.pages.sports.EventSportsPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Выбор и приобремение билетов")
public class CheckBuyTicketSportsTest extends BaseClassWebTest {

    @Story("Проверка перехода к форме покупки билета на концерт на доступный спектакль")
    @ParameterizedTest()
    @EnumSource(Cities.class)
    public void checkBuyTicketSports(Cities city) {
        SoftAssertions softAssertions = new SoftAssertions();
        final MainPage mainPage = new MainPage(city, Languages.RUS);
        mainPage.openPage();
        final ChapterSportsPage sportsPage = (ChapterSportsPage) mainPage.clickMainMenuButton(MainMenuButtonsMainPage.SPORT);
        final EventSportsPage sportGame = (EventSportsPage) sportsPage.clickFirstEvent();
        checkEventPageTitle(sportGame, softAssertions);
        final SessionPage sessionSportGame = sportGame.getFirstSessionEvent();
        checkCreateSessionEventsAddAndDelTickets(sessionSportGame, softAssertions);
        checkMakingOrdere(sessionSportGame, softAssertions);
        softAssertions.assertAll();
    }
}
