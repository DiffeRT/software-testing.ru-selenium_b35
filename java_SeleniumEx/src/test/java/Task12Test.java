import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class Task12Test {
    private WebDriver driver;
    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

    private final By CATALOG = By.xpath("//*[contains(text(), 'Catalog')]");
    private final By EDIT_PRODUCT = By.xpath("//*[contains(text(), 'Add New Product')]");

    private final By STATUS_ENABLED = By.xpath("//*[contains(text(), 'Enabled')]");
    private final By NAME = By.name("name[en]");
    private final By IMAGE = By.name("new_images[]");
    private final By QUANTITY = By.name("quantity");
    private final By DATE_FROM = By.name("date_valid_from");
    private final By DATE_TO = By.name("date_valid_to");

    private final By INF_TAB = By.cssSelector("[href*='tab-information']");
    private final By MANUFACTURER = By.name("manufacturer_id");

    private final By PRICE_TAB = By.cssSelector("[href*='tab-prices']");
    private final By PRICE = By.name("purchase_price");
    private final By PRICE_CURRENCY = By.name("purchase_price_currency_code");

    private final By SAVE_BUTTON = By.name("save");

    public WebDriver startRemoteStandaloneDriver() throws MalformedURLException {
        WebDriver driver;
        driver = new RemoteWebDriver(new URL("http://192.168.248.131:4444/wd/hub"), new ChromeOptions());
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1440,900));
        return driver;
    }

    @BeforeEach
    public void start() throws MalformedURLException {
        driver = startRemoteStandaloneDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void addNewProductItemTest() {
        openAdminPageByURL("http://localhost/litecart/admin");
        driver.findElement(CATALOG).click();

        driver.findElement(EDIT_PRODUCT).click();
        String productItemName = fillProductItemRnd(driver);
        driver.findElement(SAVE_BUTTON).click();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        WebElement table = driver.findElement(By.className("dataTable"));
        WebElement item = table.findElement(By.xpath(".//*[contains(text(), '" + productItemName + "')]"));

        Assertions.assertTrue(item.isDisplayed(), "Product Item Added");
    }

    private void openAdminPageByURL(String URL) {
        driver.get(URL);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }

    private String fillProductItemRnd(WebDriver driver) {
        String itemName = "My_Item" + (int)(Math.random() * 1000);
        driver.findElement(NAME).sendKeys(itemName);
        driver.findElement(STATUS_ENABLED).click();
        Path duckPath = Paths.get("src","test", "resources", "NewDuck.png");
        String absoluteDuckPath = duckPath.toFile().getAbsolutePath();
        driver.findElement(IMAGE).sendKeys(absoluteDuckPath);
        driver.findElement(QUANTITY).sendKeys("100");
        driver.findElement(DATE_FROM).sendKeys("11/01/2022");
        driver.findElement(DATE_TO).sendKeys("11/30/2030");

        driver.findElement(INF_TAB).click();
        WebElement manufacturer = driver.findElement(MANUFACTURER);
        Select manSelector = new Select(manufacturer);
        manSelector.selectByIndex(1);

        driver.findElement(PRICE_TAB).click();
        WebElement price = driver.findElement(PRICE);
        price.clear();
        price.sendKeys("13.5");
        WebElement priceCurr = driver.findElement(PRICE_CURRENCY);
        Select priceSelector = new Select(priceCurr);
        priceSelector.selectByIndex(1);

        return itemName;
    }
}
