package kz.ticketon.pages.museums;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SessionMuseumOldFormPage extends SessionPage {
    private final SelenideElement fullDataSession = $x("//div[@class='timeAndPlace']");
    private final ElementsCollection ticketsWithoutPlace = $$x("//div[@class='ticketWrapper entryTicketComponent']");
    private final SelenideElement addTicketButton = $x("//button[@class='button secondary addTicketButton']");

    public SessionMuseumOldFormPage(String titleExpect, String time, String day, String month, String movieTheatre) {
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
        return ticketsWithoutPlace.size();
    }

    @Step("Удаление выбранного билета")
    @Override
    public void deleteTicket() {
        if (qantitiOfSelectedPlaces == 0) {
            throw new RuntimeException("Билетов с списке нет");
        }
        ticketsWithoutPlace.get(0).$("img[alt='close']").click();
        qantitiOfSelectedPlaces--;
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
            throw new RuntimeException("билетов нет");
        }
    }
}
