package objectpage_lesson.tests;

import objectpage_lesson.pages.MainPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

public class AuthTests extends  TestData {

    @Test
    @DisplayName("Успешная авторизация")
    @ExtendWith(CommonPart.class)
    void successfulLogging() {
        new MainPage(webDriver).clickLoginButton()
            .login(login, password)
            .checkBeInProfile()
            .logout()
            .checkOutProfile();
    }

    @Test
    @DisplayName("Неуспешная авторизация без пароля")
    @ExtendWith(CommonPart.class)
    void failedLogging() {
        new MainPage(webDriver).clickLoginButton()
             .login(login, wrongPassword)
             .checkError(errorText);
    }
}
