package kz.ticketon.main_page;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import kz.ticketon.BaseClassWebTest;
import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Feature("Работоспособность основных элемнтов главной страницы")
public class CheckChangeCityTest extends BaseClassWebTest {

    static Stream<Object[]> cities() {
        List<Object[]> list = new ArrayList<>();
        Arrays.asList(Cities.values()).forEach(
                city -> Arrays.asList(Languages.values()).forEach(
                        language -> {
                            {
                                if (city != Cities.NO_CITY) {
                                    list.add(new Object[]{city, language});
                                }
                            }
                        }
                )
        );
        return list.stream();
    }

    @Story("Проверка переключения города главной страницы")
    @ParameterizedTest()
    @MethodSource(value = "cities()")
    public void checkChangeCityMaim(
            final Cities newCity,
            final Languages language
    ) {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(Cities.NO_CITY, language);
        mainPage.openPage();
        mainPage.changeSity(newCity);
        checkUrlPageCityLanguageMaim(mainPage, softAssertions);
        checkViewCityTitleMaim(mainPage, newCity, softAssertions);
        checkViewCityMaim(mainPage, newCity, softAssertions);
        softAssertions.assertAll();
    }
}
