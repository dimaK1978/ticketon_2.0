package kz.ticketon.pages;

import com.codeborne.selenide.SelenideElement;

public class MakingOrderPage {
    protected final String titleExpected = "Оформление заказа";
    protected SelenideElement actualTitle;

    public String getTitleExpected() {
        return titleExpected;
    }

    public String getTitleActual() {
        return actualTitle.getText();
    }
}
