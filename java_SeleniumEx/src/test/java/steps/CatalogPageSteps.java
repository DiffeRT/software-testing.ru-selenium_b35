package steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import pages.CatalogPage;

import java.util.List;

public class CatalogPageSteps extends BaseSteps {
    CatalogPage catalogPage;

    public CatalogPageSteps(WebDriver driver) {
        super(driver);
        catalogPage = new CatalogPage(driver);
    }

    public CatalogPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        return this;
    }

    public void clickAndCloseElementByLink(String href) {
        catalogPage.getItemByHRef(href).click();
        catalogPage.cancelBTN.click();
    }


    public EditProductPageSteps clickAddNewProduct() {
        catalogPage.addNewProduct.click();
        return new EditProductPageSteps(driver);
    }

    public void verifyThatBrowserLogIsEmpty() {
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        Assertions.assertEquals(0, logs.size(), "Log should be empty");
    }

    public void verifyThatProductExist(String name) {
        Assertions.assertTrue(catalogPage.getItemByName(name).isDisplayed(), "Product Item should exist in table");
    }
}
