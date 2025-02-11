package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;
import kz.ticketon.pages.MainPage;
import kz.ticketon.pages.SessionPage;
import kz.ticketon.pages.cinema.ChapterCinemaPage;
import kz.ticketon.pages.cinema.EventCinemaPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Выбор и приобретение билетов")
public class CheckBuyTicketMovieTest extends BaseClassWebTest {

    @Story(("Проверка выбора и приобретения билетов в кино на доступный фильм"))
    @ParameterizedTest()
    @EnumSource(Cities.class)
    public void checkBuyTicketMovie(Cities city) {
        SoftAssertions softAssertions = new SoftAssertions();
        final MainPage mainPage = new MainPage(city, Languages.RUS);
        mainPage.openPage();
        final ChapterCinemaPage pageCinema = (ChapterCinemaPage) mainPage.clickMainMenuButton(MainMenuButtonsMainPage.CINEMA);
        final EventCinemaPage movie = (EventCinemaPage) pageCinema.clickFirstEvent();
        checkEventPageTitle(movie, softAssertions);
        final SessionPage sessionMovie = movie.getFirstSessionEvent();
        checkCreateSessionEventsAddAndDelTickets(sessionMovie, softAssertions);
        checkMakingOrdere(sessionMovie, softAssertions);
        softAssertions.assertAll();
    }
}
