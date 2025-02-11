package kz.ticketon.pages.concerts;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.MakingOrderNewFormPage;
import kz.ticketon.pages.MakingOrderPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.*;

public class SessionConsertNewFormPage extends SessionPage {
    private final SelenideElement tickets = $x("//div[@id='s-seats']");
    private final ElementsCollection ticketsWithoutPlace = $$x("//tr[@class='row--item']");
    private final SelenideElement addTicketButton = $x("//button[@class='tickets--add-btn btn']");
    private final SelenideElement freeSeatButtons = $x("//a[@class='seat seat-s1 seat-t0 seat-s1-t0']//i");
    private final SelenideElement cancelSeatButtons = $x("//a[@class='seat seat-s1 seat-t0 seat-s1-t0 active']//i");
    protected SelenideElement dateActual = $x("//div[@id='s-show']");
    protected SelenideElement movieHallActual = $x("//div[@class='s-i-in']");

    public SessionConsertNewFormPage(String titleExpect, String time, String day, String month, String movieTheatre) {
        super(titleExpect, time, day, month, movieTheatre);
        titleActual = $x("//div[@id='s-event']");
        makingOrderButtom = $x("//a[@class='next']");
    }

    @Step("Удаление выбранного билета")
    @Override
    public void deleteTicket() {
        if (qantitiOfSelectedPlaces == 0) {
            throw new RuntimeException("Билетов с списке нет");
        }
        SleepUtils.sleepSeconds(10);
        if (tickeForm == TickeForm.WITHOUT_PLACE) {
            ticketsWithoutPlace.get(0).$("td[class='row--remove']").click();
            qantitiOfSelectedPlaces--;
        } else if (cancelSeatButtons.exists()) {
            cancelSeatButtons.scrollIntoView(true).click();
            qantitiOfSelectedPlaces--;
        }
    }

    @Step("Получение текста данных о сеансе с временем, датой и местом проведения из открывшейся формы")
    @Override
    public String getFullDataSessionActual() {
        return String.format(
                "%s, %s", dateActual.getText(),
                movieHallActual.getOwnText());
    }

    @Step("Получение количества выбранных билетов")
    @Override
    public int getTicketQantiti() {
        if (!tickets.exists()) {
            return 0;
        }
        return Integer.parseInt(tickets.getOwnText().split(" ")[0]);
    }

    @Step("Клик на свободное место в зале - добавление билета")
    @Override
    public void clickSeatAddTicket() {
        SleepUtils.sleepSeconds(5);
        if (addTicketButton.exists()) {
            tickeForm = TickeForm.WITHOUT_PLACE;
            if (ticketsWithoutPlace.size() == qantitiOfSelectedPlaces) {
                addTicketButton.click();
            }
            qantitiOfSelectedPlaces++;
        } else if (freeSeatButtons.exists()) {
            freeSeatButtons.scrollIntoView(true).click();
            qantitiOfSelectedPlaces++;
        } else {
            throw new RuntimeException("Свободных мест нет");
        }
    }

    @Override
    @Step("переход к оформлению заказа")
    public MakingOrderPage makingOrder() {
        makingOrderButtom.click();
        return new MakingOrderNewFormPage();
    }
}
