package kz.ticketon;

public enum Cities {
    NO_CITY("", "Выбор города", "Қала тандау", "Select city"),
    AKSAI("aksai", "Аксай", "Ақсай", "Aksay"),
    AKTAU("aktau", "Актау", "Ақтау", "Aktau"),
    AKTOBE("aktobe", "Актобе", "Ақтөбе", "Aktobe"),
    ALMATY("almaty", "Алматы", "Алматы", "Almaty"),
    ASTANA("astana", "Астана", "Астана", "Astana"),
    ATBASAR("atbasar", "Атбасар", "Атбасар", "Atbasar");
    private final String urlString;
    private final String titleRu;
    private final String titleKz;
    private final String titleEn;

    Cities(
            final String urlString,
            final String titleRu,
            final String titleKz,
            final String titleEn
    ) {
        this.urlString = urlString;
        this.titleRu = titleRu;
        this.titleKz = titleKz;
        this.titleEn = titleEn;
    }

    public String getUrlString() {

        return urlString;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public String getTitleKz() {
        return titleKz;
    }

    public String getTitleEn() {
        return titleEn;
    }
}