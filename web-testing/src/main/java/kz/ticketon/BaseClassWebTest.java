package kz.ticketon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import kz.ticketon.pages.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.webdriver;

public class BaseClassWebTest {
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 40000;
        //  Configuration.headless = true;
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
    }

    @Step("Проверка заполнения формы события, выбора, добавление 2 билетов, удаления 1 билета, провека результата")
    public void checkCreateSessionEventsAddAndDelTickets(final SessionPage sessionPage, final SoftAssertions softAssertions) {
        checkTitleSession(sessionPage, softAssertions);
        checkFullDataSession(sessionPage, softAssertions);
        sessionPage.clickSeatAddTicket();
        checkTicketsQantiti(sessionPage, 1, softAssertions);
        sessionPage.clickSeatAddTicket();
        checkTicketsQantiti(sessionPage, 2, softAssertions);
        sessionPage.deleteTicket();
        checkTicketsQantiti(sessionPage, 1, softAssertions);
    }

    @Step("Проверка заголовка страницы события, его соотвеьсвие выбранному")
    public void checkEventPageTitle(final EventPage eventPage, final SoftAssertions softAssertions) {
        softAssertions
                .assertThat(eventPage.getTitleActual())
                .isEqualTo(eventPage.getTitleExpect());
    }

    @Step("Проверка перехода к оформлению заказа")
    public void checkMakingOrdere(final SessionPage sessionPage, final SoftAssertions softAssertions) {
        MakingOrderPage makingOrderPage = sessionPage.makingOrder();
        softAssertions
                .assertThat(makingOrderPage.getTitleActual().trim())
                .contains(makingOrderPage.getTitleExpected().trim());
    }

    @Step("Проверка нахождения страницы по заголовку в результатах поиска")
    public void checkSearchEventsByTitle(
            final MainPage mainPage,
            final String eventTitle,
            final SoftAssertions softAssertions
    ) {
        SearchResultPage searchResultPage = mainPage.searchEvent(eventTitle);
        softAssertions.assertThat(searchResultPage.eventIsExists(eventTitle)).isTrue();
    }

    @Step("Проверка соответсвия заголовка открывшейся страницы выбранного раздела ожиданиям")
    public void checkChapterTitle(
            final ChapterPage chapterPage,
            final SoftAssertions softAssertions
    ) {
        softAssertions
                .assertThat(chapterPage.getPageTitle().trim())
                .isEqualTo(chapterPage.getPageTitleExpected().trim());
    }

    @Step("Проверка URL главной страницы для выбранного города и языка")
    public void checkUrlPageCityLanguageMaim(
            final MainPage mainPage,
            final SoftAssertions softAssertions
    ) {
        SleepUtils.sleepSeconds(5);
        softAssertions
                .assertThat(webdriver().driver().url())
                .contains(mainPage.getPageUrlCityLanguage());
    }

    @Step("Проверка языка отображаемого на странице")
    public void checkViewLanguageMaim(
            final MainPage mainPage,
            final Languages language,
            final SoftAssertions softAssertions
    ) {
        softAssertions
                .assertThat(mainPage.getActualLanguageText())
                .isEqualTo(language.getDisplyName());
    }

    @Step("Проверка имени города отображаемого на странице")
    public void checkViewCityMaim(
            final MainPage mainPage,
            final Cities city,
            final SoftAssertions softAssertions
    ) {
        softAssertions
                .assertThat(mainPage.getActualShowCity())
                .isEqualTo(mainPage.getCityName(city));
    }

    @Step("Проверка отображения имени города в заголовке страницы")
    public void checkViewCityTitleMaim(
            final MainPage mainPage,
            final Cities city,
            final SoftAssertions softAssertions
    ) {
        softAssertions
                .assertThat(mainPage.getHeaderEventSchedule())
                .contains(mainPage.getCityName(city));
    }

    @Step("Проверка количества выбранных билетов")
    public void checkTicketsQantiti(
            final SessionPage sessionPage,
            final int expectedQantiti,
            final SoftAssertions softAssertions
    ) {
        softAssertions
                .assertThat(sessionPage.getTicketQantiti())
                .isEqualTo(expectedQantiti);
    }

    @Step("Проверка правильности заполнения времени, даты и места проведения сейнса выбранного события")
    public void checkFullDataSession(final SessionPage sessionPage, final SoftAssertions softAssertions) {
        softAssertions
                .assertThat(sessionPage.getFullDataSessionActual())
                .contains(sessionPage.getFullDataSessionExpect());
    }

    @Step("Проверка правильности заполнения заголовка события в форме выборе билетов")
    public void checkTitleSession(final SessionPage sessionPage, final SoftAssertions softAssertions) {
        softAssertions
                .assertThat(sessionPage.getTitleExpect())
                .contains(sessionPage.getTitleActual());
    }

    @AfterEach
    public void tearDownBrowser() {
        Selenide.closeWebDriver();
    }
}