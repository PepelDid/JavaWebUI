package objectpage_and_allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage{

   @FindBy(xpath = "//form[@class='mi_cabinet_window_inputs']")
   WebElement authForm;

   @FindBy(className = "mi_cart_step_button")
   WebElement authFormButton;

   @FindBy(tagName = "h1")
   WebElement profile;

/* @FindBy (xpath = "//div[@class='acc_in']/a[@href='/logout/']")
   WebElement logoutButton;
   в Waiter  метода  public MainPage logout()совсем не хочет работать конструкция
   ExpectedConditions.presenceOfElementLocated((By) logoutButton)
   а чем ее заменить в данной ситуации,я не знаю,поэтому не использовала этот FindBy*/


    public LoginPage(WebDriver webDriver) {
        super(webDriver);}

    @Step("Ввести логин {0} и пароль {1} в соответствующие поля формы и нажать на кнопку Войти")
    public LoginPage login(String login, String password){
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@class='mi_cabinet_window_inputs']")));

        authForm.findElement(By.xpath("//input[@name='email']")).sendKeys(login);
        authForm.findElement(By.name("password")).sendKeys(password);
        authFormButton.click();
        return new LoginPage(webDriver);
    }
    @Step("Проверить,что переход в личный кабинет произошел")
    public LoginPage checkBeInProfile(){
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.visibilityOf(profile));
        return this;
    }
    @Step("Проверить наличие сообщение о неправильном логине или пароле")
    public LoginPage checkError(String errorText){
        assertThat(webDriver.findElement(By.xpath("//*[contains(text(),'" + errorText + "')]")).isEnabled());
        return this;
    }
    @Step("Проверить наличие кнопки Выйти")
    public MainPage logout(){
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='acc_in']/a[@href='/logout/']")))
                .click();
        return new MainPage(webDriver);
    }

}
