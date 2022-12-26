package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="remove_cart_item")
    public WebElement removeButton;

    @FindBy(css=".dataTable tr")
    public List<WebElement> rowsItemTable;

    @FindBy(css=".shortcuts li")
    public List<WebElement> productItems;

    public final By TABLE_ROW = By.cssSelector(".dataTable tr");

    public boolean itemsTableNotFound() {
        return elementIsNotFound(TABLE_ROW);
    }
}
