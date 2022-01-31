package org.example;

import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.extensions.CommonPart.webDriver;

public class AuthTests {
    @Test
    @DisplayName("Успешная авторизация")
    @ExtendWith(CommonPart.class)
    void successfulLogging() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/hlogin/']")).click();
        By authFormLocator = By.xpath("//form[@class='mi_cabinet_window_inputs']");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));

        WebElement authForm = webDriver.findElement(authFormLocator);
        authForm.findElement(By.xpath("//input[@name='email']")).sendKeys("npapublic@gmail.com");
        authForm.findElement(By.name("password")).sendKeys("60740520");
        authForm.findElement(By.className("mi_cart_step_button")).click();

        By cabinet = By.tagName("h1");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(cabinet));

        assertThat(webDriver.findElement(cabinet).getText().contains("Личный кабинет"));

        //в AfterEach тестов по аутентификации прописать
        // webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/logout/']")).click();

    }

    @Test
    @DisplayName("Неуспешная авторизация без пароля")
    @ExtendWith(CommonPart.class)
    void failedLogging() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/hlogin/']")).click();
        By authFormLocator = By.xpath("//form[@class='mi_cabinet_window_inputs']");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));

        WebElement authForm = webDriver.findElement(authFormLocator);
        authForm.findElement(By.xpath("//input[@name='email']")).sendKeys("npapublic@gmail.com");
        authForm.findElement(By.name("password")).sendKeys("123456");
        authForm.findElement(By.className("mi_cart_step_button")).click();

        assertThat(webDriver.findElement(By.xpath("//*[contains(text(),'Вход не выполнен')]")).isEnabled());


    }
}
