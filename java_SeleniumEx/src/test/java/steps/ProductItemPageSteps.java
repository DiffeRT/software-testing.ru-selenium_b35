package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.ProductItemPage;

public class ProductItemPageSteps extends BaseSteps {
    ProductItemPage productItemPage;

    public ProductItemPageSteps(WebDriver driver) {
        super(driver);
        productItemPage = new ProductItemPage(driver);
    }

    public void addToCart() {
        if (productItemPage.getSizeSelect() != null) {
            Select sizeSelector = new Select(productItemPage.getSizeSelect());
            sizeSelector.selectByIndex(1);
        }
        productItemPage.addToCartButton.click();
    }
}
