package tests;

/*   Task 07   */

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import steps.MainPageSteps;

import java.util.List;

public class ProductStickerTest extends BaseTest {

    @Test
    public void checkStickersAvailabilityTest() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);

        List<WebElement> productItems = mainPageSteps.open()
                .getProductItems();

        for (WebElement item : productItems) {
            mainPageSteps.verifyThatProductHasOnlyOneSticker(item);
        }
    }
}
