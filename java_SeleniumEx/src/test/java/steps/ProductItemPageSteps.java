package steps;

import app.Duck;
import app.DuckStyle;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.ProductItemPage;

public class ProductItemPageSteps extends BaseSteps {
    ProductItemPage productItemPage;

    public ProductItemPageSteps(WebDriver driver) {
        super(driver);
        productItemPage = new ProductItemPage(driver);
    }

    @Step("Add opened Product Item to Cart")
    public void addToCart() {
        if (productItemPage.getSizeSelect() != null) {
            Select sizeSelector = new Select(productItemPage.getSizeSelect());
            sizeSelector.selectByIndex(1);
        }
        productItemPage.addToCartButton.click();
    }

    @Step("Get Product Item attributes")
    public Duck getProductItemAttr() {
        Duck duck = new Duck();
        WebElement openedItem = productItemPage.productItem;
        duck.name = productItemPage.nameByItem(openedItem).getText();
        duck.price = productItemPage.priceByItem(openedItem).getText();
        duck.priceCamp = productItemPage.priceCampByItem(openedItem).getText();
        return duck;
    }

    @Step("Get Product Item style attributes")
    public DuckStyle getProductItemStyle() {
        DuckStyle duckStyle = new DuckStyle();
        WebElement openedItem = productItemPage.productItem;
        duckStyle.setPriceCampFontWeight(productItemPage.getPriceCampFWByItem(openedItem));
        duckStyle.setPriceCampColor(productItemPage.getPriceCampColorByItem(openedItem));
        duckStyle.setPriceHeight(productItemPage.getPriceSizeByItem(openedItem));
        duckStyle.setPriceCampHeight(productItemPage.getPriceCampSizeByItem(openedItem));
        return duckStyle;
    }

    @Step("Verify that the style matches spec")
    public void verifyThatProductStyleCorrect(DuckStyle actualDuckStyle) {
        // bold is >= 700
        Assertions.assertTrue(actualDuckStyle.getPriceCampFontWeight() >= 700, "Campaign Price should be bold");
        Assertions.assertTrue(actualDuckStyle.getPriceCampColor().get("G").equals(0) && actualDuckStyle.getPriceCampColor().get("B").equals(0), "Campaign Price should be Red (G == B == 0)");
        Assertions.assertTrue(actualDuckStyle.getPriceCampHeight() > actualDuckStyle.getPriceHeight(), "Campaign Price Height >= The Regular one");
    }
}
