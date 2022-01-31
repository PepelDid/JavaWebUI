package org.example;

import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.extensions.CommonPart.webDriver;

public class CityChangeTests {
    @Test
    @DisplayName("Изменение города")
    @ExtendWith(CommonPart.class)
    void cityChangeTest(){
        WebElement city = webDriver.findElement(By.xpath("//div[@class=' h_city']//span[@class='user-selected-city']"));
        city.click();
        webDriver.findElement(By.xpath("//div[@class='rezult_form_filter_city']//a[contains(text(),'Липецк')]")).click();

        assertThat(city.getText().contains("Липецк"));
        /*как вариант ассерта new WebDriverWait(webDriver, 2)
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Липецк']")));*/

    }

    @Test
    @DisplayName("Выбор города,в который не осуществляется доставка")
    @ExtendWith(CommonPart.class)
    void cityWithoutDeliveringTest(){
        webDriver.findElement(By.xpath("//div[@class=' h_city']//span[@class='user-selected-city']")).click();
        webDriver.findElement(By.xpath("//div[@class='search_form']/input[@placeholder='Поиск города']")).sendKeys("Липов");
        webDriver.findElement(By.xpath("//div[@data-index=0]")).click();

        String location = webDriver.findElement(By.xpath("//div[@class=' h_city']//span[@class='user-selected-city']")).getText();
      // 'Липовая Грива (Кумохинский с/с)'

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/sumki/']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//li/a[.='Чемоданы']")).click();
        webDriver.findElement(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')][1]//div[@class='card-product__wrap-img']")).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")).click();
        webDriver.findElement(By.xpath("//a[@class='mi_button_check']")).click();

        assertThat(webDriver.findElement(By.xpath("//input[@placeholder='Город']")).getAttribute("value")).isEqualTo(location);
        assertThat(webDriver.findElement(By.xpath("//div[@id='Delivery_City_error']")).getText().contains("К сожалению, доставка в выбранный вами населённый пункт не осуществляется. Пожалуйста, выберите другой ближайший к вам город."));




    }
}

