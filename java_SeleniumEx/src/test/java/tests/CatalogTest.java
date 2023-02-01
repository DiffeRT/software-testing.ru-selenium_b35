package tests;

/*   Task 12   */

import app.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AdminPageSteps;
import steps.CatalogPageSteps;

@DisplayName("Product Catalog")
public class CatalogTest extends BaseTest {
    AdminPageSteps adminPageSteps;
    CatalogPageSteps catalogPageSteps;

    @Test
    @DisplayName("Add new product item to the Catalog")
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
