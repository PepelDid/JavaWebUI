package org.example.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class CommonPart implements BeforeEachCallback, AfterEachCallback {
    public static WebDriver webDriver;
    public static JavascriptExecutor js;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false", "disable-infobars",
                "disable-translate", "disable-notifications",
                "disable-popup-blocking", "ignore-certificate-errors");

        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(1300, 920));
        js = (JavascriptExecutor) webDriver;
        webDriver.get("https://henderson.ru/");

        WebElement subscribe = webDriver.findElement(By.id("subscribe-modal"));
        js.executeScript("arguments[0].remove()", subscribe);
        webDriver.findElement(By.className("i-close")).click();
        Thread.sleep(2000);


    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        webDriver.quit();
    }

}
