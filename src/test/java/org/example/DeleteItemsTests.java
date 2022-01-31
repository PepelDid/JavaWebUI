package org.example;

import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.example.extensions.CommonPart.webDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteItemsTests {
    @Test
    @DisplayName("Удаление всех товаров из корзины")
    @ExtendWith(CommonPart.class)
    void deleteAllItems(){
        webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/footwear/']")).click();
        webDriver.findElement(By.xpath("//div[@class='left_nav as_filter']//a[.='Сандалии и шлепанцы']")).click();
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']"));
        products.get(1).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();

        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")))
                .click();

        webDriver.findElement(By.xpath(" //div[@class='cart-header__right']//button[contains(@class,'btn-delete-products')]")).click();

        assertThat(webDriver.findElement(By.xpath("//div[contains(@class,'card-empty')]"))).isNotNull();
    }
}
