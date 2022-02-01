package objectpage_lesson.tests;

import objectpage_lesson.pages.MainPage;
import objectpage_lesson.pages.ProductsPage;
import org.example.extensions.CommonPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.extensions.CommonPart.webDriver;

public class DeleteItemsTests extends  TestData {

    @Test
    @DisplayName("Удаление всех товаров из корзины")
    @ExtendWith(CommonPart.class)
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
