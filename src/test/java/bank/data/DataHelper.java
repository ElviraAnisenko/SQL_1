package bank.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class DataHelper {
    private DataHelper() {
    }
    static Faker faker=new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        String login;
        String password;


    }
    public static AuthInfo getUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static  AuthInfo getInvalidUser () {
        return new AuthInfo (getInvalidLogin(),getInvalidPass());

    }

    public static  String getInvalidLogin () {
        String invalidLogin=faker.name().username();
        return invalidLogin;
    }

    public static  String getInvalidPass () {
        String invalidPass=faker.internet().password();
        return invalidPass;
    }


    @Value
    public static class VerificationCode {
        String code;
    }

    public static  VerificationCode getInvalidCode () {
        return new VerificationCode(faker.number().digits(6));
        }

}
