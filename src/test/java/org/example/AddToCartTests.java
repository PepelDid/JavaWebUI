package org.example;

import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.example.extensions.CommonPart.webDriver;

public class AddToCartTests {
    @Test
    @DisplayName("Добавление в корзину 1 товарa")
    @ExtendWith(CommonPart.class)
    void addToCartOneItem(){

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/footwear/']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//li/a[.='Сандалии и шлёпанцы']")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']"));
        products.get(1).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();


        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")))
                .click();

        assertThat(webDriver.findElement(By.className("mi_cart_product_info"))).isNotNull();
    }

    @Test
    @DisplayName("Добавление в корзину 2 товаров")
    @ExtendWith(CommonPart.class)
    void addToCartTwoItems(){
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/footwear/']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//li/a[.='Сандалии и шлёпанцы']")).click();

        List<WebElement> products1 = webDriver.findElements(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']"));
        String urlProduct1 = products1.get(1).findElement(By.xpath(".//a[@href]")).getAttribute("href");
        products1.get(1).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();

        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-fancybox-close]"))).click();

        webDriver.findElement(By.xpath("//ul[@class='breadcrumb']//a[.='Сандалии и шлепанцы']")).click();

        List<WebElement> products2 = webDriver.findElements(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']"));
        String urlProduct2 = products2.get(4).findElement(By.xpath(".//a[@href]")).getAttribute("href");
        products2.get(4).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();

        webDriver.findElement(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")).click();

        assertThat(webDriver.findElement(By.xpath("//div[@class='mi_cart_product cart-item '][2]//a[@class='mi_cart_product_title']"))
                .getAttribute("href"))
                .isEqualTo(urlProduct1);
        assertThat(webDriver.findElement(By.xpath("//div[@class='mi_cart_product cart-item '][1]//a[@class='mi_cart_product_title']"))
                .getAttribute("href"))
                .isEqualTo(urlProduct2);
    }
}
