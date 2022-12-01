import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task06Test {
    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

    private WebDriver driver;

    @BeforeAll
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterAll
    public void stop() {
        driver.quit();
    }

    private void openAdminPage() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private List<String> getItemsLevelCache(By locator) {
        List<String> itemsLevel1Cache = new ArrayList<>();
        int size = driver.findElements(locator).size();
        for (int i = 0; i < size; i++) {
            List<WebElement> menuItems = driver.findElements(locator);
            itemsLevel1Cache.add(menuItems.get(i).getAttribute("href"));
        }
        return itemsLevel1Cache;
    }

    @Test
    public void clickAllMenuItemsTest() {
        openAdminPage();
        List<String> itemsLevel1HRefs = getItemsLevelCache(By.cssSelector("#box-apps-menu a"));

        for (int i = 0; i < itemsLevel1HRefs.size(); i++) {
            driver.findElement(By.cssSelector("a[href='" + itemsLevel1HRefs.get(i) + "']")).click();
            boolean isHeaderFound = isElementPresent(By.cssSelector("td#content h1"));
            Assertions.assertTrue(isHeaderFound, "Menu Item Clicked");

            List<WebElement> itemsLevel2 = driver.findElement(By.cssSelector("a[href='" + itemsLevel1HRefs.get(i) + "']")).findElements(By.xpath("..//a"));
            if (itemsLevel2.size() > 0) {
                List<String> itemsLevel2Cache = new ArrayList<>();
                for (int j = 0; j < itemsLevel2.size(); j++) {
                    List<WebElement> menuItemsInner = driver.findElement(By.cssSelector("a[href='" + itemsLevel1HRefs.get(i) + "']")).findElements(By.xpath("..//a"));
                    itemsLevel2Cache.add(menuItemsInner.get(j).getAttribute("href"));
                }

                for (int j = 0; j < itemsLevel2Cache.size(); j++) {
                    driver.findElement(By.cssSelector("a[href='" + itemsLevel2Cache.get(j) + "']")).click();
                    boolean isInnerHeaderFound = isElementPresent(By.cssSelector("td#content h1"));
                    Assertions.assertTrue(isInnerHeaderFound, "Menu Item Clicked");
                }
            }

        }
    }

}
