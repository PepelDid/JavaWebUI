package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AddToCart {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://henderson.ru/");
        webDriver.manage().window().setSize(new Dimension(1300, 720));

        // приходится закрывать много всплывающих окон
        webDriver.findElement(By.className("fancybox-close-small")).click();
        webDriver.findElement(By.className("i-close")).click();
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/footwear/']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//li/a[.='Кроссовки']")).click();

        webDriver.findElement(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')][5]//div[@class='card-product__wrap-img']")).click();
        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();
        webDriver.findElement(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")).click();

        //это заготовки под ассершны
        webDriver.findElement(By.className("mi_cart_product_info"));
        webDriver.findElement(By.className("mi_cart_logo")).click();
        webDriver.findElement(By.xpath("//a[@class='h_basket']/span[@class='qtyCartItems']"));


        webDriver.quit();
    }
}
