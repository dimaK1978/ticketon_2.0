package kz.ticketon;

import io.qameta.allure.Story;
import kz.ticketon.pages.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class StartTest extends BaseClassAppTest {
    @Story("Проверка запуска и стартовая нстройка языка и города приложения, преход на главную страницу, главное меню выбор пункта 'Cобытия'")
    @ParameterizedTest()
    @EnumSource(Languages.class)
    public void startTicketon(Languages language) {
       final MainScreenPhonePage mainScreenPhonePage = new MainScreenPhonePage(driver);
       final ChooseLanguagePage chooseLanguagePage = mainScreenPhonePage.clickApp();
        checkLanguagePage(chooseLanguagePage);
        final ChooseCityPage chooseCityPage = chooseLanguagePage.selectLanguage(language);
        checkChooseCityPage(chooseCityPage);
        final MainScreenAppPage mainScreenAppPage = chooseCityPage.selectCity(Cities.ASTANA);
        checkMainPage(mainScreenAppPage);
        mainScreenAppPage.clickMenu();
        final EventsPage eventsPage = mainScreenAppPage.clickEventsEvents();
        checkEvents(eventsPage);
    }
}
