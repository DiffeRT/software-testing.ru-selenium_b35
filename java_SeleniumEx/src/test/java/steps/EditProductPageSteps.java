package steps;

import app.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.EditProductPage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EditProductPageSteps extends BaseSteps {
    EditProductPage editProductPage;

    public EditProductPageSteps(WebDriver driver) {
        super(driver);
        editProductPage = new EditProductPage(driver);
    }

    public EditProductPageSteps fillProductFieldsRandom(Product product) {
        editProductPage.statusEnabled.click();
        editProductPage.name.sendKeys(product.getName());

        setImageDiffPlatforms(editProductPage.image);

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

    /* Not the best solution. But anyway I need to find out the way to run the test remotely on diff VMs */
    public void setImageDiffPlatforms(WebElement image) {
        boolean done = false;

        if (driver instanceof RemoteWebDriver) {
            String platform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName().toString().toLowerCase();
            if (platform.equals("linux")) {
                image.sendKeys("/home/rustem/NewDuck.png");
            }
            else {
                image.sendKeys("c:/home/rustem/NewDuck.png");
            }
            done = true;
        }
        //Default (on Dev PC)
        if (!done) {
            Path duckPath = Paths.get("src","test", "resources", "NewDuck.png");
            String absoluteDuckPath = duckPath.toFile().getPath();
            File file = new File(absoluteDuckPath);
            if (file.exists()) {
                image.sendKeys(absoluteDuckPath);
            }
        }
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
