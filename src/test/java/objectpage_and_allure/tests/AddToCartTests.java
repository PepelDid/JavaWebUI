package objectpage_and_allure.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import objectpage_and_allure.pages.MainPage;
import objectpage_and_allure.pages.ProductsPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

@DisplayName("Добавление в корзину")
public class AddToCartTests extends  TestData{

    @Test
    @DisplayName("Добавление в корзину 1 товарa")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.BLOCKER)
    void addToCartOneItem(){
        products = new MainPage(webDriver).moveOnSandal()
                .getListOfItems();
        new ProductsPage(webDriver).chooseShoes(products, productNumber1)
                .clickBuyButton()
                .selectSize()
                .goToCart()
                .controlOneItemInCart();
    }

    @Test
    @DisplayName("Добавление в корзину 2 товаров")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.BLOCKER)
    void addToCartTwoItems(){
        products = new MainPage(webDriver).moveOnSandal()
                .getListOfItems();
        urlProduct1 = new ProductsPage(webDriver).getURLProduct(products, productNumber1);
        new ProductsPage(webDriver).chooseShoes(products, productNumber1)
                .clickBuyButton()
                .selectSize()
                .backToBuying()
                .backToSandal();

        products = new ProductsPage(webDriver).getListOfItems();
        urlProduct2 = new ProductsPage(webDriver).getURLProduct(products, productNumber2);
        new ProductsPage(webDriver).chooseShoes(products, productNumber2)
                .clickBuyButton()
                .selectSize()
                .goToCart()
                .controlTwoItemsInCart(urlProduct1, urlProduct2);
    }
}
