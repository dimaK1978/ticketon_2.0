package kz.ticketon.pages;

import static com.codeborne.selenide.Selenide.$;

public class MakingOrderNewFormPage extends MakingOrderPage {
    public MakingOrderNewFormPage() {
        actualTitle = $("div[class='steps step-order']").$("b");
    }
}
