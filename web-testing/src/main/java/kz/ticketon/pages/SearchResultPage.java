package kz.ticketon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage extends BaseTemlatePage {
    private final String stringXpathSearchEvent =
            "//div[@class='DetailedCardInfo_eventTitle__7VjTp'  and contains(text(),'%s')]";

    public SearchResultPage(Cities city, Languages language) {
        super(city, language);
    }

    @Step("Проверка на странице результатов поиска наличия события с заданным заголовком")
    public boolean eventIsExists(final String eventTitle) {
        final SelenideElement event = $x(String.format(
                stringXpathSearchEvent,
                eventTitle
        ));
        return event.exists();
    }
}

