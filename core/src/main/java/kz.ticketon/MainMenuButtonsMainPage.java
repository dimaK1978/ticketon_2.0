package kz.ticketon;

public enum MainMenuButtonsMainPage {

    CINEMA("Кино", "Movie", "Кино"),
    THEATRES("Театры", "Theaters", "Театрлар"),
    CONCERTS("Концерты", "Concert", "Концерт"),
    SPORT("Спорт", "Sport", "Спорт"),
    CHILDREN("Детям","Children","Балаларға"),
    CHRISTMAS_EVENT("Ёлки","Christmas Events","Жана-Жыл"),
    TOURS("Туры","Tours","Турлар"),

    MASTER_CLASSES("Мастер-классы","Master classes","Мастер-класстар"),
    MUSEUMS("Музеи","Museums","Мұражайлар"),
    ENTERTAIMENT("Развлеченияa","Entertainment","Ойын-сауықтар");

    private String buttonNameRu;
    private String buttonNameEn;
    private String buttonNameKz;

    MainMenuButtonsMainPage(String buttonNameRu, String buttonNameEn, String buttonNameKz) {
        this.buttonNameRu = buttonNameRu;
        this.buttonNameEn = buttonNameEn;
        this.buttonNameKz = buttonNameKz;
    }

    public String getButtonNameRu() {
        return buttonNameRu;
    }

    public String getButtonNameEn() {
        return buttonNameEn;
    }

    public String getButtonNameKz() {
        return buttonNameKz;
    }
}
