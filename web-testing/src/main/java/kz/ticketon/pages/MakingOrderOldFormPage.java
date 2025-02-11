package kz.ticketon.pages;

import static com.codeborne.selenide.Selenide.$x;

public class MakingOrderOldFormPage extends MakingOrderPage{
    public MakingOrderOldFormPage() {
        actualTitle = $x("//p[@data-v-d20a5f7b='']");
    }
}
