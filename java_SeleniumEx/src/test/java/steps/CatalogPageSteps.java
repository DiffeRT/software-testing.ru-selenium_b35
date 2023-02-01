package steps;

import io.qameta.allure.Step;
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

    @Step("Open Catalog page")
    public CatalogPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        return this;
    }

    @Step("Click item {href} and close")
    public void clickAndCloseElementByLink(String href) {
        catalogPage.getItemByHRef(href).click();
        catalogPage.cancelBTN.click();
    }

    @Step("Click Add New Product button")
    public EditProductPageSteps clickAddNewProduct() {
        catalogPage.addNewProduct.click();
        return new EditProductPageSteps(driver);
    }

    @Step("Verify that browser log is empty")
    public void verifyThatBrowserLogIsEmpty() {
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        Assertions.assertEquals(0, logs.size(), "Log should be empty");
    }

    @Step("Verify that Product Item has been added")
    public void verifyThatProductExist(String name) {
        Assertions.assertTrue(catalogPage.getItemByName(name).isDisplayed(), "Product Item should exist in table");
    }
}
