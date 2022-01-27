package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CityChange {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://henderson.ru/");
        webDriver.manage().window().setSize(new Dimension(1300, 720));


        webDriver.findElement(By.className("fancybox-close-small")).click();

        webDriver.findElement(By.xpath("//div[@class=' h_city']//span[@class='user-selected-city']")).click();
        webDriver.findElement(By.xpath("//div[@class='rezult_form_filter_city']//a[contains(text(),'Липецк')]")).click();

        //заготовка под ассершн
        webDriver.findElement(By.xpath("//span[.='Липецк']"));


        webDriver.quit();

    }
}
