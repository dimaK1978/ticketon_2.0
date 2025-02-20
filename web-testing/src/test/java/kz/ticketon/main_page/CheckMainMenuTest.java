package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;
import kz.ticketon.pages.ChapterPage;
import kz.ticketon.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

@Feature("Работоспособность основных элемнтов главной страницы")
public class CheckMainMenuTest extends BaseClassWebTest {
    static Stream<Arguments> menuItemsAndCitiesAndLanguages() {

        return Arrays.stream(MainMenuButtonsMainPage.values())
                .flatMap(item -> Arrays.stream(Cities.values())
                        .flatMap(city -> Arrays.stream(Languages.values())
                                .map(language -> Arguments.of(item, city, language))));
    }

    @Story("Проверка кликов кнопок разделов главного меню и переходов на страницы выбранных разделов, с учетом выбора города и языка")
    @ParameterizedTest()
    @MethodSource("menuItemsAndCitiesAndLanguages")
    public void checkMainMenuOpenChapter(
            final MainMenuButtonsMainPage menuItem,
            final Cities city,
            final Languages language) {
        SoftAssertions softAssertions = new SoftAssertions();
        final MainPage mainPage = new MainPage(city, language);
        mainPage.openPage();
        final ChapterPage chapterPage = mainPage.clickMainMenuButton(menuItem);
        checkChapterTitle(chapterPage, softAssertions);
        softAssertions.assertAll();
    }
}
