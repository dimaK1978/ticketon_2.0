package kz.ticketon;

public enum Languages {
    RUS("", "Рус"),
    ENG("en", "Eng"),
    KZ("kz", "Қаз");

    private final String urlString;
    private final String displyName;

    Languages(final String urlString, final String displyName) {
        this.urlString = urlString;
        this.displyName = displyName;
    }

    public String getUrlString() {

        return urlString;
    }

    public String getDisplyName() {

        return displyName;
    }
}
