package tests;

/*   Task 07   */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import steps.MainPageSteps;

import java.util.List;

@DisplayName("Product Items Stickers")
public class ProductStickerTest extends BaseTest {

    @Test
    @DisplayName("Product items Stickers check")
    public void checkStickersAvailabilityTest() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);

        List<WebElement> productItems = mainPageSteps.open()
                .getProductItems();

        for (WebElement item : productItems) {
            mainPageSteps.verifyThatProductHasOnlyOneSticker(item);
        }
    }
}
