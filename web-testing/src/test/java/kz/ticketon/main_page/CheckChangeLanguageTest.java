package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

@Feature("Работоспособность основных элемнтов главной страницы")
public class CheckChangeLanguageTest extends BaseClassWebTest {
    static Stream<Arguments> languages() {

        return Arrays.stream(Languages.values())
                .flatMap(startLanguage -> Arrays.stream(Languages.values())
                        .filter(newLanguage -> startLanguage != newLanguage)
                        .map(newLanguage -> Arguments.of(startLanguage, newLanguage)));

    }

    @Story("Проверка переключения языка главной страницы")
    @ParameterizedTest()
    @MethodSource("languages")
    public void checkChangeLanguage(
            final Languages startPageLanguage,
            final Languages newLanguage
    ) {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(Cities.NO_CITY, startPageLanguage);
        mainPage.openPage();
        mainPage.changeLanguage(newLanguage);
        checkUrlPageCityLanguageMaim(mainPage, softAssertions);
        checkViewLanguageMaim(mainPage, newLanguage, softAssertions);
        softAssertions.assertAll();
    }
}
