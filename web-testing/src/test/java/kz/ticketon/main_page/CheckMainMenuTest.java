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
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Feature("Работоспособность основных элемнтов главной страницы")
public class CheckMainMenuTest extends BaseClassWebTest {
    static Stream<Object[]> menuItemsAndCitiesAndLanguages() {
        List<Object[]> list = new ArrayList<>();
        Arrays.asList(MainMenuButtonsMainPage.values()).forEach(
                item -> Arrays.asList(Cities.values()).forEach(
                        city -> Arrays.asList(Languages.values()).forEach(
                                language -> list.add(new Object[]{item, city, language})
                        )
                )
        );
        return list.stream();
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
