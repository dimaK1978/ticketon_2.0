package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;
import kz.ticketon.pages.MainPage;
import kz.ticketon.pages.SessionPage;
import kz.ticketon.pages.children.ChapterChildrenPage;
import kz.ticketon.pages.children.EventChildrenPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Выбор и приобремение билетов")
public class CheckBuyTicketChildrenTest extends BaseClassWebTest {

    @Story("Проверка выбора и приобретения билетов на доступное мероприятие для детей")
    @ParameterizedTest()
    @EnumSource(Cities.class)
    public void checkBuyTicketChildren(Cities city) {
        SoftAssertions softAssertions = new SoftAssertions();
        final MainPage mainPage = new MainPage(city, Languages.RUS);
        mainPage.openPage();
        final ChapterChildrenPage theatresPage = (ChapterChildrenPage) mainPage.clickMainMenuButton(MainMenuButtonsMainPage.CHILDREN);
        final EventChildrenPage theatrePlay = (EventChildrenPage) theatresPage.clickFirstEvent();
        checkEventPageTitle(theatrePlay, softAssertions);
        final SessionPage sessionChildrenActivity = theatrePlay.getFirstSessionEvent();
        checkCreateSessionEventsAddAndDelTickets(sessionChildrenActivity, softAssertions);
        checkMakingOrdere(sessionChildrenActivity, softAssertions);
        softAssertions.assertAll();
    }
}
