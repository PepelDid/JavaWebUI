package objectpage_and_allure.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import objectpage_and_allure.pages.MainPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

@DisplayName("Аутентификация")
public class AuthTests extends  TestData {

    @Test
    @DisplayName("Успешная авторизация")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.NORMAL)
    void failedLogging() {
        new MainPage(webDriver).clickLoginButton()
             .login(login, wrongPassword)
             .checkError(errorText);
    }
}
