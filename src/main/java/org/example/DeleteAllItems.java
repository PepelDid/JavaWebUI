package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeleteAllItems {
    public static void main(String[] args) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

            WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            webDriver.get("https://henderson.ru/");
            webDriver.manage().window().setSize(new Dimension(1300, 720));

        webDriver.findElement(By.className("fancybox-close-small")).click();
        webDriver.findElement(By.className("i-close")).click();
        //здесь последовательный переход  по категориям
        webDriver.findElement(By.xpath("//div[@class='nav']//a[@href='/catalog/footwear/']")).click();
        webDriver.findElement(By.xpath("//div[@class='left_nav as_filter']//a[.='Ботинки']")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']"));
        products.get(4).click();

        webDriver.findElement(By.xpath("//a[@class='btn js-btn-cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'select2-dropdown')]//li[.='43']")).click();

        //добавление в корзину медленное
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")))
                .click();

       webDriver.findElement(By.xpath(" //div[@class='cart-header__right']//button[contains(@class,'btn-delete-products')]")).click();

       // заготовка под ассершн
       webDriver.findElement(By.xpath("//div[contains(@class,'card-empty')]"));

       webDriver.quit();
    }
}
