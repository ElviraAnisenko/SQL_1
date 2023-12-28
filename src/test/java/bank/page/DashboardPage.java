package bank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



public class DashboardPage {
    private SelenideElement dashboard=$("[data-test-id=dashboard]");


    public DashboardPage() {
        dashboard.shouldHave(text("Личный кабинет")).shouldBe(visible);
    }


}


