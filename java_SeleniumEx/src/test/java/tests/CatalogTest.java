package tests;

/*   Task 12   */

import app.Product;
import org.junit.jupiter.api.Test;
import steps.AdminPageSteps;
import steps.CatalogPageSteps;

public class CatalogTest extends BaseTest {
    AdminPageSteps adminPageSteps;
    CatalogPageSteps catalogPageSteps;

    @Test
    public void addNewProductItemTest() {
        adminPageSteps = new AdminPageSteps(driver);
        catalogPageSteps = new CatalogPageSteps(driver);

        Product newProduct = new Product();

        adminPageSteps.openAndLogin()
                .clickMenuItemCatalog()
                .clickAddNewProduct()
                .fillProductFieldsRandom(newProduct)
                .save();

        catalogPageSteps.verifyThatProductExist(newProduct.getName());
    }
}
