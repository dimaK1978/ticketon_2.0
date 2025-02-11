package kz.ticketon.utils;

import kz.ticketon.Cities;
import kz.ticketon.Languages;
import kz.ticketon.MainMenuButtonsMainPage;

public class NamedUtils {
    public static String getCityNameByLanguage(final Cities city, final Languages language) {
        return switch (language) {
            case ENG -> city.getTitleEn();
            case KZ -> city.getTitleKz();
            default -> city.getTitleRu();
        };
    }

    public static String getMainMenuButtonNameByLanguage(
            final MainMenuButtonsMainPage MainMenuButton,
            final Languages language
    ) {
        return switch (language) {
            case ENG -> MainMenuButton.getButtonNameEn();
            case KZ -> MainMenuButton.getButtonNameKz();
            default -> MainMenuButton.getButtonNameRu();
        };
    }
}
