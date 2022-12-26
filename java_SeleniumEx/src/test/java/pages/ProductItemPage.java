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
}
