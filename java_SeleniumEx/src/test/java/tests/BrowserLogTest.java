package tests;

import org.junit.jupiter.api.Test;
import pages.CatalogPage;
import steps.CatalogPageSteps;

public class BrowserLogTest extends BaseTest {

    @Test
    public void browserLogTest() {
        CatalogPageSteps catalogPageSteps = new CatalogPageSteps(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        catalogPageSteps.open();
        for (String href : catalogPage.getItemsHRefs()) {
            catalogPageSteps.open()
                    .clickAndCloseElementByLink(href);

            catalogPageSteps
                    .verifyThatBrowserLogIsEmpty();
        }
    }
}
