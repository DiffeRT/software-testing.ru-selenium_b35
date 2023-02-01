package tests;

/*   Task 17   */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CatalogPage;
import steps.CatalogPageSteps;

@DisplayName("Browser Log")
public class BrowserLogTest extends BaseTest {

    @Test
    @DisplayName("Browser log test")
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
