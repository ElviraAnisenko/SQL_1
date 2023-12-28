package bank.page;

import bank.data.DataHelper;
import bank.data.SQLHelper;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private SelenideElement codeField=$("[data-test-id='code'] input");
    private SelenideElement error= $("[data-test-id='error-notification'] .notification__content");
    private SelenideElement button=$(".button");

    public VerificationPage () {

        codeField.shouldBe(visible);
    }

    public void inputCode (DataHelper.VerificationCode verificationCode) {
       codeField.setValue(verificationCode.getCode());
       button.click();
    }

    public void verifyErrorNotification(String expectedText){
        error.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public DashboardPage inputValidCode (DataHelper.VerificationCode verificationCode) {
        inputCode(verificationCode);
        return new DashboardPage();

    }


}
