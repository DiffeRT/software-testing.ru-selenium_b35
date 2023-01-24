package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductItemPage extends BasePage{

    private final By SIZE = By.name("options[Size]");

    public ProductItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="add_cart_product")
    public WebElement addToCartButton;

    @FindBy(xpath="//a[contains(text(), 'Checkout')]")
    public WebElement checkOutButton;

    public WebElement getSizeSelect() {
        if (elementIsNotFound(SIZE)) {
            return null;
        }
        else {
            return driver.findElement(SIZE);
        }
    }

    @FindBy(id = "box-product")
    public WebElement productItem;

    public WebElement nameByItem(WebElement product) {
        return product.findElement(By.cssSelector("h1"));
    }

    public WebElement priceByItem(WebElement product) {
        return product.findElement(By.className("regular-price"));
    }

    public WebElement priceCampByItem(WebElement product) {
        return product.findElement(By.className("campaign-price"));
    }

    public String getPriceCampFWByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("font-weight");
    }

    public String getPriceCampColorByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("color");
    }

    public String getPriceSizeByItem(WebElement product) {
        return priceByItem(product).getCssValue("font-size");
    }

    public String getPriceCampSizeByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("font-size");
    }
}
