package objectpage_and_allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends BasePage {
    @FindBy(xpath = "//a[@class='btn js-btn-cart']")
    WebElement buyButton;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown')]//li[.='43']")
    WebElement selectSize43;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[.='Сандалии и шлепанцы']")
    WebElement sandalOnBreadcrumb;


    public ItemPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Кликнуть на кнопку Добавить в корзину")
    public ItemPage clickBuyButton(){
        buyButton.click();
        return new ItemPage(webDriver);
    }
    @Step("Кликнуть на Выберите размер и выбрать доступный для заказа размер")
    public ItemPage selectSize(){
        selectSize43.click();
        return new ItemPage(webDriver);
    }
    @Step("Кликнуть на кнопку Перейти в корзину")
    public CartPage goToCart(){
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")))
                .click();
        return new CartPage(webDriver);
    }
    @Step("Кликнуть на кнопку Вернуться к покупкам")
    public ItemPage backToBuying(){
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-fancybox-close]"))).click();
        return new ItemPage(webDriver);
    }
    @Step("Кликнуть на ссылку субкаталога Сандали и шлепанцы в хлебных крошках")
    public ProductsPage backToSandal(){
        sandalOnBreadcrumb.click();
        return new ProductsPage(webDriver);
    }

}
