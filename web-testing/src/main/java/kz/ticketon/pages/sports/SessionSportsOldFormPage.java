package kz.ticketon.pages.sports;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.SleepUtils;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.*;

public class SessionSportsOldFormPage extends SessionPage {
    private final SelenideElement freeSeatButton = $x("//div[@style='color: rgb(255, 255, 255);']");
    private final SelenideElement fullDataSession = $x("//div[@class='timeAndPlace']");
    private final ElementsCollection tickets = $$x("//div[@class='ticketWrapper desktopTicket']");
    private final ElementsCollection ticketsWithoutPlace = $$x("//div[@class='ticketWrapper entryTicketComponent']");
    private final SelenideElement sectorOfHall = $("path[stroke='#00bbff']");
    private final SelenideElement addTicketButton = $x("//button[@class='button secondary addTicketButton']");

    public SessionSportsOldFormPage(String titleExpect, String time, String day, String month, String movieTheatre) {
        super(titleExpect, time, day, month, movieTheatre);
        titleActual = $x("//div[@class='title']");
        makingOrderButtom = $x("//button[@class='button primary']");
    }

    @Step("Получение текста данных о сеансе с временем, датой и местом проведения из открывшейся формы")
    @Override
    public String getFullDataSessionActual() {
        return fullDataSession.getText();
    }

    @Step("Получение количества выбранных билетов")
    @Override
    public int getTicketQantiti() {
        return tickets.size();
    }

    @Step("Удаление выбранного билета")
    @Override
    public void deleteTicket() {
        if (qantitiOfSelectedPlaces == 0) {
            throw new RuntimeException("Билетов с списке нет");
        }
        if (tickeForm == TickeForm.WITH_PLACE) {
            tickets.get(0).$("img[alt='закрыть']").click();
            qantitiOfSelectedPlaces--;
        } else {
            ticketsWithoutPlace.get(0).$("img[alt='close']").click();
            qantitiOfSelectedPlaces--;
        }
    }

    @Step("Клик на свободное место в зале - добавление билета")
    @Override
    public void clickSeatAddTicket() {
        if (addTicketButton.exists()) {
            tickeForm = TickeForm.WITHOUT_PLACE;
            if (ticketsWithoutPlace.size() == qantitiOfSelectedPlaces) {
                addTicketButton.click();
            }
            qantitiOfSelectedPlaces++;
        } else {
            if (sectorOfHall.exists()) {
                sectorOfHall.click();
            }
            SleepUtils.sleepSeconds(5);
            if (addTicketButton.exists()) {
                tickeForm = TickeForm.WITHOUT_PLACE;
                if (ticketsWithoutPlace.size() == qantitiOfSelectedPlaces) {
                    addTicketButton.click();
                }
                qantitiOfSelectedPlaces++;
            } else {
                if (!freeSeatButton.exists()) {
                    throw new RuntimeException("Свободных мест нет");
                }
                freeSeatButton.click();
                qantitiOfSelectedPlaces++;
            }
        }
    }
}
