package kz.ticketon;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.pages.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Мобильное приложение")
public class StartTest extends BaseClassAppTest {

    @Story("Проверка запуска и стартовая нстройка языка и города приложения, преход на главную страницу, главное меню выбор пункта 'Cобытия'")
    @ParameterizedTest()
    @EnumSource(Languages.class)
    public void startTicketon(Languages language) {
        MainScreenPhonePage mainScreenPhonePage = new MainScreenPhonePage();
        ChooseLanguagePage chooseLanguagePage = mainScreenPhonePage.clickApp();
        checkLanguagePage(chooseLanguagePage);
        ChooseCityPage chooseCityPage = chooseLanguagePage.selectLanguage(language);
        checkChooseCityPage(chooseCityPage);
        MainScreenAppPage mainScreenAppPage = chooseCityPage.selectCity(Cities.ASTANA);
        checkMainPage(mainScreenAppPage);
        mainScreenAppPage.clickMenu();
        EventsPage eventsPage = mainScreenAppPage.clickEvents();
        checkEvents(eventsPage);
    }
}
