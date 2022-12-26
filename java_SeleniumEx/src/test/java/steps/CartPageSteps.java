package steps;

import app.ConfigConstants;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CartPage;

import java.time.Duration;
import java.util.List;

public class CartPageSteps extends BaseSteps {
    CartPage cartPage;

    public CartPageSteps(WebDriver driver) {
        super(driver);
        cartPage = new CartPage(driver);
    }

    public CartPageSteps open() {
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    public void removeItems() {
        int count = cartPage.productItems.size();
        while (count > 0) {
            int tableSizeBefore = cartPage.rowsItemTable.size();
            wait.until(ExpectedConditions.elementToBeClickable(cartPage.removeButton));
            cartPage.removeButton.click();
            count--;

            int tableSizeAfter = getAfterRemoveRowsCount(tableSizeBefore);
            Assertions.assertTrue(tableSizeBefore > tableSizeAfter);
        }
    }

    public void verifyThatCartIsEmpty() {
        Assertions.assertTrue(cartPage.itemsTableNotFound(), "The Cart should be empty");
    }

    private int getAfterRemoveRowsCount(int tableSizeBefore) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            List<WebElement> after = wait.until(ExpectedConditions.numberOfElementsToBeLessThan(cartPage.TABLE_ROW, tableSizeBefore));
            return after.size();
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }
}
