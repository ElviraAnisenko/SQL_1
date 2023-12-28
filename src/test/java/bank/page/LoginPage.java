package bank.page;

import bank.data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField=$("[data-test-id='login'] input");
    private SelenideElement passField=$("[data-test-id='password'] input");
    private SelenideElement button= $(".button");
    private SelenideElement error= $("[data-test-id='error-notification'] .notification__content");


   public void verifyErrorNotification(String expectedText){
       error.shouldHave(text(expectedText)).shouldBe(visible);
   }

    public void login (DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passField.setValue(authInfo.getPassword());
        button.click();

    }

    public VerificationPage validLogin (DataHelper.AuthInfo authInfo) {
    login(authInfo);
        return new VerificationPage();
    }


}
