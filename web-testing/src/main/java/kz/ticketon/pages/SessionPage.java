package kz.ticketon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public abstract class SessionPage {
    protected final String titleExpect;
    protected final String time;
    protected final String day;
    protected final String month;
    protected final String eventLocation;
    protected SelenideElement titleActual;
    protected SelenideElement makingOrderButtom;
    protected TickeForm tickeForm = TickeForm.WITH_PLACE;
    protected int qantitiOfSelectedPlaces = 0;


    public SessionPage(String titleExpect, String time, String day, String month, String eventLocation) {
        this.titleExpect = titleExpect;
        this.time = time;
        this.day = day;
        this.month = month;
        this.eventLocation = eventLocation;
    }

    public abstract void clickSeatAddTicket();

    public abstract void deleteTicket();

    @Step("Получение текста актуального заголовка открывшейся формы с названием события")
    public String getTitleActual() {
        return titleActual.getText();
    }

    @Step("Получение ожидаемого текста заголовка с названием события")
    public String getTitleExpect() {
        return titleExpect;
    }

    @Step("Получение ожидаемого текста данных о сеансе с временем, датой и местом проведения")
    public String getFullDataSessionExpect() {
        return String.format("%s %s в %s, %s", day, month, time, eventLocation);
    }

    public abstract String getFullDataSessionActual();

    public abstract int getTicketQantiti();

    @Step("переход к оформлению заказа")
    public MakingOrderPage makingOrder() {
        makingOrderButtom.click();
        return new MakingOrderOldFormPage();
    }

    protected enum TickeForm {
        WITH_PLACE,
        WITHOUT_PLACE
    }
}
