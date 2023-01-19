package steps;

import app.ConfigConstants;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;

import java.time.Duration;
import java.util.List;

public class MainPageSteps extends BaseSteps {
    MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        super(driver);
        mainPage = new MainPage(driver);
    }

    public MainPageSteps open() {
        driver.get("http://localhost/litecart");
        return this;
    }

    public ProductItemPageSteps openProductItemByNumber(int number) {
        if (number >= mainPage.productItems.size()) {
            Assertions.fail("Item index " + number + " Out Of Bounds");
        }
        mainPage.productItems.get(number).click();
        return new ProductItemPageSteps(driver);
    }

    public ProductItemPageSteps openProductItemRandom() {
        int index = (int)(Math.random() * (mainPage.productItems.size()-1));
        mainPage.productItems.get(index).click();
        return new ProductItemPageSteps(driver);
    }

    public List<WebElement> getProductItems() {
        return mainPage.productItems;
    }

    public CartPageSteps openCart() {
        mainPage.checkoutButton.click();
        return new CartPageSteps(driver);
    }

    public void verifyThatItemsCounterUpdated(int count) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wait.until(ExpectedConditions.textToBePresentInElement(mainPage.counterLabel, String.valueOf(count)));
        }
        catch (TimeoutException e) {
            Assertions.fail("Items counter was not updated");
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }

    public void verifyThatProductHasOnlyOneSticker(WebElement product) {
        Assertions.assertEquals(1, mainPage.stickers(product).size(), "Only one sticker should be found");
    }
}
