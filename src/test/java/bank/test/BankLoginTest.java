package bank.test;


import bank.data.DataHelper;
import bank.data.SQLHelper;
import bank.page.DashboardPage;
import bank.page.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;


public class BankLoginTest {


    LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage=open("http://localhost:9999", LoginPage.class);

    }

    @AfterEach
    void cleanCodes () {
        SQLHelper.cleanAuthCodes();
    }

    @AfterAll
    static void cleanDB () {
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldAuthWithValidData() {
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        var dashboard = verificationPage.inputValidCode(verificationCode);
    }

    @Test
    void shouldNotAuthWithNotRegisteredUser() {
        var authInfo = DataHelper.getInvalidUser();
        loginPage.login(authInfo);
        loginPage.verifyErrorNotification("Неверно указан логин или пароль");

    }

    @Test
    void shouldNotAuthWithInValidCode() {
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getInvalidCode();
        verificationPage.inputCode(verificationCode);
        verificationPage.verifyErrorNotification("Неверно указан код! Попробуйте ещё раз.");

    }

    @Test
    void shouldBlockUserIfInputThreeTimesInvalidCode() {
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getInvalidCode();
        verificationPage.inputCode(verificationCode);
        verificationPage.verifyErrorNotification("Неверно указан код! Попробуйте ещё раз.");
        verificationPage.inputCode(verificationCode);
        verificationPage.verifyErrorNotification("Неверно указан код! Попробуйте ещё раз.");
        verificationPage.inputCode(verificationCode);
        verificationPage.verifyErrorNotification("Система заблокирована!");

    }



}












