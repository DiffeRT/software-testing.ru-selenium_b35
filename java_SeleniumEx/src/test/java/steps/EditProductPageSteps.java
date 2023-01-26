package steps;

import app.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.EditProductPage;

public class EditProductPageSteps extends BaseSteps {
    EditProductPage editProductPage;

    public EditProductPageSteps(WebDriver driver) {
        super(driver);
        editProductPage = new EditProductPage(driver);
    }

    public EditProductPageSteps fillProductFieldsRandom(Product product) {
        editProductPage.statusEnabled.click();
        editProductPage.name.sendKeys(product.getName());
        //Path duckPath = Paths.get("src","test", "resources", "NewDuck.png");
        //String absoluteDuckPath = duckPath.toFile().getPath();
        editProductPage.image.sendKeys("/home/rustem/NewDuck.png");
        editProductPage.quantity.sendKeys("100");
        editProductPage.dateFrom.sendKeys("11/01/2022");
        editProductPage.dateTo.sendKeys("11/30/2030");

        editProductPage.tabInformation.click();
        selectManufacturerByIndex(1);

        editProductPage.tabPrice.click();
        editProductPage.price.clear();
        editProductPage.price.sendKeys("200");
        selectPriceCurrencyByIndex(1);
        return this;
    }

    public void selectManufacturerByIndex(int index) {
        Select manSelector = new Select(editProductPage.manufacturer);
        manSelector.selectByIndex(index);
    }

    public void selectPriceCurrencyByIndex(int index) {
        Select currSelector = new Select(editProductPage.priceCurrency);
        currSelector.selectByIndex(index);
    }

    public void save() {
        editProductPage.saveBTN.click();
    }
}
