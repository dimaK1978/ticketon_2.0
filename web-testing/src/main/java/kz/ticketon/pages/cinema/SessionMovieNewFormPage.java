package kz.ticketon.pages.cinema;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.MakingOrderNewFormPage;
import kz.ticketon.pages.MakingOrderPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.$x;

public class SessionMovieNewFormPage extends SessionPage {
    private final SelenideElement tickets = $x("//div[@id='s-seats']");
    private final SelenideElement cancelSeatButtons = $x("//a[@class='seat seat-s1 seat-t0 seat-s1-t0 active']//i");
    private final SelenideElement freeSeatButtons = $x("//a[@class='seat seat-s1 seat-t0 seat-s1-t0']//i");
    protected SelenideElement movieTheatreActual = $x("//div[@class='s-i-in']");
    protected SelenideElement dateActual = $x("//div[@id='s-show']");


    public SessionMovieNewFormPage(String titleExpect, String time, String day, String month, String movieTheatre) {
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
        if (cancelSeatButtons.exists()) {
            cancelSeatButtons.scrollIntoView(true).click();
            qantitiOfSelectedPlaces--;
        }
    }

    @Step("Получение текста данных о сеансе с временем, датой и местом проведения из открывшейся формы")
    @Override
    public String getFullDataSessionActual() {
        return String.format(
                "%s, %s", dateActual.getText(),
                movieTheatreActual.getOwnText());
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
        if (freeSeatButtons.exists()) {
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
