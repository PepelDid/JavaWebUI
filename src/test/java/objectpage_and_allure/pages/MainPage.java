package objectpage_and_allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{
    @FindBy(xpath = "//div[@class='acc_in']/a[@href='/hlogin/']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class=' h_city']//span[@class='user-selected-city']")
    WebElement city;

    @FindBy(xpath = "//div[@class='search_form']/input[@placeholder='Поиск города']")
    WebElement citySearch;

    @FindBy(xpath = "//div[@data-index=0]")
    WebElement selectFirstCity;

    @FindBy(xpath = "//div[@class='nav']//a[@href='/catalog/footwear/']")
    WebElement selectFootwear;

    @FindBy(xpath = "//li/a[.='Сандалии и шлёпанцы']")
    WebElement selectSandal;

    @FindBy(xpath = "//div[@class='nav']//a[@href='/catalog/sumki/']")
    WebElement selectBags;

    @FindBy(xpath = "//li/a[.='Чемоданы']")
    WebElement selectSuit;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Кликнуть на кнопку Войти")
    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage(webDriver);
    }

    @Step("Проверить наличие на странице кнопки Войти")
    public MainPage checkOutProfile(){
        loginButton.isEnabled();
        return this;
    }
    @Step("Изменить город на {0}")
    public MainPage changeCity (String newCity){
        city.click();
        webDriver.findElement(By.xpath(String.format("//div[@class='rezult_form_filter_city']//a[contains(text(),'%s')]", newCity))).click();
        //new WebDriverWait(webDriver, 4).until(ExpectedConditions.textToBePresentInElement(city, "Липецк"));
        return new MainPage(webDriver);
    }

    @Step("Проверить ,что город изменен на выбранный")
    public MainPage controlNewCity (){
        new WebDriverWait(webDriver, 4).until(ExpectedConditions.textToBePresentInElement(city, "Липецк"));
        return this;
    }
    @Step("Выбрать населенный пункт сельского типа")
    public MainPage chooseVillage(String  village){
        city.click();
        citySearch.sendKeys(village);
        selectFirstCity.click();
        return new MainPage(webDriver);
    }
    @Step("Запомнить выбранный населенный пункт сельского типа")
    public String findVillageLocator(){
        String location = city.getText();
        return location;
    }
    @Step("Навести курсор на субкаталог Обувь и выбрать в выпадающем подменю Сандали")
    public ProductsPage moveOnSandal(){
        new Actions(webDriver)
                .moveToElement(selectFootwear)
                .build()
                .perform();
        selectSandal.click();
        return new ProductsPage(webDriver);
    }
    @Step("Кликнуть на субкаталог Чемоданы")
    public ProductsPage moveOnSuitcase(){
        new Actions(webDriver)
                .moveToElement(selectBags)
                .build()
                .perform();
        selectSuit.click();
        return new ProductsPage(webDriver);
    }


}
