package tests;

/*   Task 10   */

import app.Duck;
import app.DuckStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MainPageSteps;
import steps.ProductItemPageSteps;

import java.net.MalformedURLException;

@DisplayName("Product Item Opening")
public class ProductOpeningTest extends BaseTest {
    protected MainPageSteps mainPageSteps;
    protected ProductItemPageSteps productItemPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        mainPageSteps = new MainPageSteps(driver);
        productItemPageSteps = new ProductItemPageSteps(driver);
    }

    @Test
    @DisplayName("Opened product item consistency check")
    public void checkProductItemConsistencyTest() {
        Duck selectedDuck = new Duck();

        Duck openedDuck = mainPageSteps.open()
                .openCampProductItem(selectedDuck)
                .getProductItemAttr();

        mainPageSteps.verifyThatOpenedAttrMatches(selectedDuck, openedDuck);
    }

    @Test
    @DisplayName("Product item styles check")
    public void checkProductItemStyleTest() {
        DuckStyle duckStyle = mainPageSteps.open()
                .getCampProductItemPriceStyle();

        mainPageSteps.verifyThatProductStyleCorrect(duckStyle);
    }

    @Test
    @DisplayName("Opened product item styles check")
    public void checkOpenedProductItemStyleTest() {
        Duck fakeDuck = new Duck();

        DuckStyle duckStyle = mainPageSteps.open()
                .openCampProductItem(fakeDuck)
                .getProductItemStyle();

        productItemPageSteps.verifyThatProductStyleCorrect(duckStyle);
    }
}
