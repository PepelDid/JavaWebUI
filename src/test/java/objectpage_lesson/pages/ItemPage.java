package objectpage_lesson.pages;

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

    public ItemPage clickBuyButton(){
        buyButton.click();
        return new ItemPage(webDriver);
    }

    public ItemPage selectSize(){
        selectSize43.click();
        return new ItemPage(webDriver);
    }

    public CartPage goToCart(){
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-added__buttons']/a[@href='/cart/']")))
                .click();
        return new CartPage(webDriver);
    }

    public ItemPage backToBuying(){
        new WebDriverWait(webDriver, 8)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-fancybox-close]"))).click();
        return new ItemPage(webDriver);
    }

    public ProductsPage backToSandal(){
        sandalOnBreadcrumb.click();
        return new ProductsPage(webDriver);
    }

}
