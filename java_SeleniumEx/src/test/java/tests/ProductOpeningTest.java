package tests;

/*   Task 10   */

import app.Duck;
import app.DuckStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.MainPageSteps;
import steps.ProductItemPageSteps;

import java.net.MalformedURLException;

public class ProductOpeningTest extends BaseTest {
    private MainPageSteps mainPageSteps;
    private ProductItemPageSteps productItemPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        mainPageSteps = new MainPageSteps(driver);
        productItemPageSteps = new ProductItemPageSteps(driver);
    }

    @Test
    public void checkProductItemConsistencyTest() {
        Duck selectedDuck = new Duck();

        Duck openedDuck = mainPageSteps.open()
                .openCampProductItem(selectedDuck)
                .getProductItemAttr();

        mainPageSteps.verifyThatOpenedAttrMatches(selectedDuck, openedDuck);
    }

    @Test
    public void checkProductItemStyleTest() {
        DuckStyle duckStyle = new DuckStyle();

        duckStyle = mainPageSteps.open()
                .getCampProductItemPriceStyle();

        mainPageSteps.verifyThatProductStyleCorrect(duckStyle);
    }

    @Test
    public void checkOpenedProductItemStyleTest() {
        DuckStyle duckStyle = new DuckStyle();
        Duck fakeDuck = new Duck();

        duckStyle = mainPageSteps.open()
                .openCampProductItem(fakeDuck)
                .getProductItemStyle();

        productItemPageSteps.verifyThatProductStyleCorrect(duckStyle);
    }
}
