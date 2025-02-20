package kz.ticketon.pages;

import kz.ticketon.Cities;
import kz.ticketon.Languages;

public class EventsPage {

    private final Cities city;
    private final Languages language;

    public EventsPage(final Languages language, final Cities city) {
        this.language = language;
        this.city = city;
    }

    public Languages getLanguage() {
        return language;
    }
}