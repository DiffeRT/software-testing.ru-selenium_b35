package tests;

/*   Task 13   */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.CartPageSteps;
import steps.MainPageSteps;

@DisplayName("Add Product to Cart")
public class AddProductToCartTest extends BaseTest {
    private MainPageSteps mainPageSteps;
    private CartPageSteps cartPageSteps;


    @Test
    @DisplayName("Add and remove product items to the Cart")
    public void addRemoveBasketTest() {
        mainPageSteps = new MainPageSteps(driver);
        cartPageSteps = new CartPageSteps(driver);

        int count = 1;

        while (count <= 3) {
            mainPageSteps.open()
                    .openProductItemRandom()
                    .addToCart();
            mainPageSteps.verifyThatItemsCounterUpdated(count);
            count++;
        }

        mainPageSteps.openCart()
                .removeItems();
        cartPageSteps.verifyThatCartIsEmpty();
    }
}
