package steps;

import app.ConfigConstants;
import app.Duck;
import app.DuckStyle;
import io.qameta.allure.Step;
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

    @Step("Open Main Page")
    public MainPageSteps open() {
        driver.get("http://localhost/litecart");
        return this;
    }

    @Step("Open Product Item by number {number}")
    public ProductItemPageSteps openProductItemByNumber(int number) {
        if (number >= mainPage.productItems.size()) {
            Assertions.fail("Item index " + number + " Out Of Bounds");
        }
        mainPage.productItems.get(number).click();
        return new ProductItemPageSteps(driver);
    }

    @Step("Select and open random Product Item")
    public ProductItemPageSteps openProductItemRandom() {
        int index = (int)(Math.random() * (mainPage.productItems.size()-1));
        mainPage.productItems.get(index).click();
        return new ProductItemPageSteps(driver);
    }

    @Step("Get the list of Product Items")
    public List<WebElement> getProductItems() {
        return mainPage.productItems;
    }

    @Step("Open Cart page")
    public CartPageSteps openCart() {
        mainPage.checkoutButton.click();
        return new CartPageSteps(driver);
    }

    @Step("Open Product Item from campaigns category")
    public ProductItemPageSteps openCampProductItem(Duck selectedDuck) {
        WebElement campItem = mainPage.campaignsItem;
        selectedDuck.name = mainPage.nameByItem(campItem).getText();
        selectedDuck.price = mainPage.priceByItem(campItem).getText();
        selectedDuck.priceCamp = mainPage.priceCampByItem(campItem).getText();
        campItem.click();
        return new ProductItemPageSteps(driver);
    }

    @Step("Get Price Styles for selected product item")
    public DuckStyle getCampProductItemPriceStyle() {
        DuckStyle duckStyle = new DuckStyle();
        WebElement openedItem = mainPage.campaignsItem;
        duckStyle.setPriceStyle(mainPage.getPriceStyleByItem(openedItem));
        duckStyle.setPriceColor(mainPage.getPriceColorByItem(openedItem));
        duckStyle.setPriceCampColor(mainPage.getPriceCampColorByItem(openedItem));
        duckStyle.setPriceHeight(mainPage.getPriceSizeByItem(openedItem));
        duckStyle.setPriceCampHeight(mainPage.getPriceCampSizeByItem(openedItem));
        duckStyle.setPriceCampFontWeight(mainPage.getPriceCampFWByItem(openedItem));
        return duckStyle;
    }

    @Step("Click New Customer link")
    public CreateAccountSteps clickNewCustomerLink() {
        mainPage.newCustomerLink.click();
        return new CreateAccountSteps(driver);
    }

    @Step("Login with {email}, {password}")
    public MainPageSteps loginAs(String email, String password) {
        mainPage.email.sendKeys(email);
        mainPage.password.sendKeys(password);
        mainPage.loginBTN.click();
        return this;
    }

    @Step("Click logout button")
    public MainPageSteps logOut() {
        mainPage.logoutBTN.click();
        return this;
    }

    @Step("Verify that Cart counter updated")
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

    @Step("Verify that Product Item has only one sticker")
    public void verifyThatProductHasOnlyOneSticker(WebElement product) {
        Assertions.assertEquals(1, mainPage.stickers(product).size(), "Only one sticker should be found");
    }

    @Step("Verify that opened Product Item attributes matches to the selected one")
    public void verifyThatOpenedAttrMatches(Duck selectedDuck, Duck openedDuck) {
        Assertions.assertEquals(selectedDuck.name, openedDuck.name, "Name should match");
        Assertions.assertEquals(selectedDuck.price, openedDuck.price, "Price should match");
        Assertions.assertEquals(selectedDuck.priceCamp, openedDuck.priceCamp, "Campaign Price should match");
    }

    @Step("Verify that Product Item styles attributes matches to the spec")
    public void verifyThatProductStyleCorrect(DuckStyle actualDuckStyle) {
        Assertions.assertEquals("line-through", actualDuckStyle.getPriceStyle(), "Regular Price should be line-through");
        Assertions.assertTrue(actualDuckStyle.getPriceColor().get("R").equals(actualDuckStyle.getPriceColor().get("G")) && actualDuckStyle.getPriceColor().get("G").equals(actualDuckStyle.getPriceColor().get("B")), "Regular Price should be Grey (R == G == B)");
        // bold is >= 700
        Assertions.assertTrue(actualDuckStyle.getPriceCampFontWeight() >= 700, "Campaign Price should be bold");
        Assertions.assertTrue(actualDuckStyle.getPriceCampColor().get("G").equals(0) && actualDuckStyle.getPriceCampColor().get("B").equals(0), "Campaign Price should be Red (G == B == 0)");
        Assertions.assertTrue(actualDuckStyle.getPriceCampHeight() > actualDuckStyle.getPriceHeight(), "Campaign Price Height >= The Regular one");
    }

    @Step("Verify that the customer is logged in")
    public void verifyThatCustomerLoggedIn() {
        Assertions.assertTrue(mainPage.logoutBTN.isDisplayed(), "Logout button should be visible");
    }

    @Step("Verify that the customer is logged out")
    public void verifyThatCustomerLoggedOut() {
        Assertions.assertTrue(mainPage.loginBTN.isDisplayed(), "Login button should be visible");
    }
}

