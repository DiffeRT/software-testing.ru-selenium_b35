import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task13Test {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By PRODUCT_ITEM = By.cssSelector("li.product a.link");
    private final By ADD_TO_CART = By.name("add_cart_product");
    private final By SIZE = By.name("options[Size]");
    private final By CHECKOUT = By.xpath("//a[contains(text(), 'Checkout')]");
    private final By REMOVE_ITEM = By.name("remove_cart_item");
    private final By TABLE_ROW = By.cssSelector(".dataTable tr");


    @BeforeEach
    public void start() {
        driver = Config.startBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void actionsBasketTest() {
        driver.get("http://localhost/litecart");
        addRandomItemToTheBasketStep();
        checkItemsCounterUpdated("1");

        driver.get("http://localhost/litecart");
        addRandomItemToTheBasketStep();
        checkItemsCounterUpdated("2");

        driver.get("http://localhost/litecart");
        addRandomItemToTheBasketStep();
        checkItemsCounterUpdated("3");

        driver.findElement(CHECKOUT).click();

        checkRemoveItemsButton(3);
    }

    private void checkItemsCounterUpdated(String count) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            WebElement counter = driver.findElement(By.className("quantity"));
            wait.until(ExpectedConditions.textToBePresentInElement(counter, count));
        }
        catch (TimeoutException e) {
            Assertions.fail("Items counter was not updated");
        }
        finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    private void checkRemoveItemsButton(int count) {
        while (count > 0) {
            //The same element could be added twice. That means one delete action deletes 2 instances
            if (elementIsNotFound(TABLE_ROW) || elementIsNotFound(REMOVE_ITEM)) {
                return;
            }
            int tableSizeBefore = driver.findElements(TABLE_ROW).size();
            wait.until(ExpectedConditions.elementToBeClickable(REMOVE_ITEM));
            driver.findElement(REMOVE_ITEM).click();
            count--;

            int tableSizeAfter = getAfterRemoveRowsCount(tableSizeBefore);
            Assertions.assertTrue(tableSizeBefore > tableSizeAfter);
        }
    }

    private void addRandomItemToTheBasketStep() {
        List<WebElement> items = driver.findElements(PRODUCT_ITEM);
        int index = (int)(Math.random() * (items.size()-1));
        items.get(index).click();

        if (!elementIsNotFound(SIZE)) {
            WebElement size = driver.findElement(SIZE);
            Select sizeSelector = new Select(size);
            sizeSelector.selectByIndex(1);
        }
        driver.findElement(ADD_TO_CART).click();
    }

    private boolean elementIsNotFound(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return driver.findElements(locator).size() == 0;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    private int getAfterRemoveRowsCount(int tableSizeBefore) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            List<WebElement> after = wait.until(ExpectedConditions.numberOfElementsToBeLessThan(TABLE_ROW, tableSizeBefore));
            return after.size();
        }
        finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }
}
