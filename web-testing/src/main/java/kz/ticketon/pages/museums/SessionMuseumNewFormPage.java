package kz.ticketon.pages.museums;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.ticketon.pages.MakingOrderNewFormPage;
import kz.ticketon.pages.MakingOrderPage;
import kz.ticketon.pages.SessionPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SessionMuseumNewFormPage extends SessionPage {
    private final ElementsCollection ticketsWithoutPlace = $$x("//tr[@class='row--item']");
    private final SelenideElement addTicketButton = $x("//button[@class='tickets--add-btn btn']");
    protected SelenideElement museumActual = $x("//div[@class='s-i-in']");
    protected SelenideElement dateActual = $x("//div[@id='s-show']");

    public SessionMuseumNewFormPage(String titleExpect, String time, String day, String month, String movieTheatre) {
        super(titleExpect, time, day, month, movieTheatre);
        titleActual = $x("//div[@id='s-event']");
        makingOrderButtom = $x("//a[@id='next']");
    }

    @Step("Получение количества выбранных билетов")
    @Override
    public int getTicketQantiti() {
        return ticketsWithoutPlace.size();
    }

    @Override
    public void deleteTicket() {
        if (qantitiOfSelectedPlaces == 0) {
            throw new RuntimeException("Билетов с списке нет");
        }
        ticketsWithoutPlace.get(0).$("td[class='row--remove']").click();
        qantitiOfSelectedPlaces--;
    }

    @Step("Получение текста данных о сеансе с временем, датой и местом проведения из открывшейся формы")
    @Override
    public String getFullDataSessionActual() {
        return String.format(
                "%s, %s", dateActual.getText(),
                museumActual.getOwnText());
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

    @Override
    @Step("переход к оформлению заказа")
    public MakingOrderPage makingOrder() {
        makingOrderButtom.click();
        return new MakingOrderNewFormPage();
    }
}
