package objectpage_and_allure.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//div[@class='list-product']//div[contains(@class,'list-product__item')]//div[@class='card-product__wrap-img']")
    List<WebElement> productsOnPage;

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }


    public List<WebElement> getListOfItems(){
        List<WebElement> products = productsOnPage;
        return products;
    }
    @Step("Кликнуть на продукт")
    public ItemPage chooseShoes(List<WebElement> products, int number){
        products.get(number).click();
        return new ItemPage(webDriver);
    }
    @Step("Зафиксировать идентификатор продукта")
    public String getURLProduct(List<WebElement> products, int number){
        String urlProduct = products.get(number).findElement(By.xpath(".//a[@href]")).getAttribute("href");
        return urlProduct;
    }

    @Step("Кликнуть на чемодан")
    public ItemPage chooseSuitcase(int number){
        webDriver.findElement(By.xpath("//div[@class='list-product']//div[contains(@class,'list-product__item')][" + number + "]//div[@class='card-product__wrap-img']")).click();
        return new ItemPage(webDriver);
    }
}
