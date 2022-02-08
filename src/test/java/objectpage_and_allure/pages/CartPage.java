package objectpage_and_allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {
    @FindBy(className = "mi_cart_product_info")
    WebElement itemInfoCard;

    @FindBy(xpath = "//input[@placeholder='Город']")
    WebElement cityForDelivering;

    @FindBy(xpath = "//div[@id='Delivery_City_error']")
    WebElement deliveringErrorText;

    @FindBy(xpath = "//div[@class='wrap_button_checks']")
    WebElement checkOutButton;

    @FindBy(xpath = "//div[@class='mi_cart_product cart-item '][2]//a[@class='mi_cart_product_title']")
    WebElement product1InCart;

    @FindBy(xpath = "//div[@class='mi_cart_product cart-item '][1]//a[@class='mi_cart_product_title']")
    WebElement product2InCart;

    @FindBy(xpath = "//div[@class='cart-header__right']//button[contains(@class,'btn-delete-products')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class,'card-empty')]")
    WebElement emptyCart;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить наличие в корзине выбранного товара")
    public CartPage controlOneItemInCart(){
        assertThat(itemInfoCard.isEnabled());
        return this;
    }
    @Step("Проверить наличие сообщения о невозможности доставки")
    public CartPage controlNonDeliveringMessage(String location){
        assertThat(cityForDelivering.getAttribute("value")).isEqualTo(location);
        new WebDriverWait(webDriver, 4).until(ExpectedConditions.visibilityOf(deliveringErrorText));
        //  или вот такой вариант: assertThat(deliveringErrorText.isEnabled());
        return this;
    }
    @Step("Кликнуть на кнопку Оформить заказ")
    public CartPage clickCheckoutButton(){
        checkOutButton.click();
        return  new CartPage(webDriver);
    }
    @Step("Проверить наличие в корзине двух выбранных товаров")
    public void controlTwoItemsInCart(String urlProduct1, String urlProduct2) {
        assertThat(product1InCart
                .getAttribute("href"))
                .isEqualTo(urlProduct1);
        assertThat(product2InCart
                .getAttribute("href"))
                .isEqualTo(urlProduct2);
    }
    @Step("Кликнуть на кнопку Удалить все")
    public CartPage deleteAllItems() {
        deleteButton.click();
        return new CartPage(webDriver);
    }
    @Step("Проверить,что корзина пуста")
    public CartPage controlDeleting() {
        new WebDriverWait(webDriver, 4).until(ExpectedConditions.visibilityOf(emptyCart));
        //как вариант: assertThat(emptyCart.isEnabled());
        return new CartPage(webDriver);
    }
}
