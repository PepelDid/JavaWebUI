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

@DisplayName("Удаление из корзины")
public class DeleteItemsTests extends  TestData {

    @Test
    @DisplayName("Удаление всех товаров из корзины")
    @ExtendWith(CommonPart.class)
    @Severity(SeverityLevel.CRITICAL)
    void deleteAllItems(){
        products = new MainPage(webDriver).moveOnSandal()
                .getListOfItems();
        new ProductsPage(webDriver).chooseShoes(products, productNumber1)
                .clickBuyButton()
                .selectSize()
                .goToCart()
                .deleteAllItems()
                .controlDeleting();
    }
}
