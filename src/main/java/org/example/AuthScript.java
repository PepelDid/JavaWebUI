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

public class AuthScript
{
    public static void main( String[] args ) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://henderson.ru/");
        webDriver.manage().window().setSize(new Dimension(1300, 720));

        webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/hlogin/']")).click();
        By authFormLocator = By.xpath("//form[@class='mi_cabinet_window_inputs']");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(authFormLocator));

        WebElement authForm = webDriver.findElement(authFormLocator);
        authForm.findElement(By.xpath("//input[@name='email']")).sendKeys("npapublic@gmail.com");
        //authForm.findElement(By.name("email")).sendKeys("npapublic@gmail.com"); как вариант
        authForm.findElement(By.name("password")).sendKeys("60740520");
        //authForm.findElement(By.xpath("//input[@name='password']")).sendKeys("60740520");  как вариант
        authForm.findElement(By.className("mi_cart_step_button")).click();
        //authForm.findElement(By.cssSelector("button.mi_cart_step_button")).click(); как вариант

        By cabinet = By.tagName("h1");
        //By cabinet = By.xpath("//h1[@class='ttl']"); как вариант
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(cabinet));
        webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/logout/']")).click();

        // заготовка под ассершн
        webDriver.findElement(By.xpath("//div[@class='acc_in']/a[@href='/hlogin/']"));
        webDriver.quit();
    }
}
