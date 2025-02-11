package kz.ticketon.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.Cities;
import kz.ticketon.Languages;

import static com.codeborne.selenide.Selenide.*;

public abstract class ChapterPage {
    protected final SelenideElement pageTitle = $x("//div[@class='page-title']");
    protected final ElementsCollection eventList = $$x("//div[@class='block-1 list-block']");
    protected String shortPageUrl;
    protected Cities city;
    protected Languages language;
    protected String pageTitleRus;
    protected String pageTitleEng;
    protected String pageTitleKz;

    public ChapterPage(Cities city, Languages language) {
        this.city = city;
        this.language = language;
    }

    @Step("Открытие страницы выбранного основного раздела")
    public void openPage() {
        open(pageUrlCityLanguage());
    }

    @Step("Получить актуальный заголовок страницы раздела")
    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void setShortPageUrl(String shortPageUrl) {
        this.shortPageUrl = shortPageUrl;
    }

    @Step("Получить ожидаемый город страницы раздела")
    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    @Step("Получить ожидаемый язык страницы раздела")
    public Languages getLanguage() {
        return language;
    }

    @Step("Получить ожидаемый заголовок страницы раздела")
    public String getPageTitleExpected() {
        String title = switch (language) {
            case KZ -> pageTitleKz;
            case ENG -> pageTitleEng;
            default -> pageTitleRus;
        };
        return title;
    }

    public String pageUrlCityLanguage() {
        MainPage mainPage = new MainPage(city, language);
        return String.format("%s/%s", mainPage.getPageUrlCityLanguage(), shortPageUrl);
    }

    public abstract EventPage clickEvent(final SelenideElement movie);

    public abstract EventPage clickFirstEvent();
}
