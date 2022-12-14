import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task14Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

    private final By COUNTRIES = By.xpath("//*[@class='dataTable']//td[5]/a");
    private final By EXT_LINKS = By.cssSelector("form a[target=_blank]");


    @BeforeEach
    public void start() {
        driver = Config.startBrowser("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void openLinkInNewTabTest() {
        openAdminPageByURL("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(COUNTRIES).click();

        List<WebElement> extLinks = driver.findElements(EXT_LINKS);
        for (WebElement link : extLinks) {
            String mainWindow = driver.getWindowHandle();
            link.click();
            wait.until((WebDriver d) -> d.getWindowHandles().size() > 1);

            Set<String> windows = driver.getWindowHandles();
            String newWindow = "";
            for (String s : windows) {
                if (!s.equals(mainWindow)) {
                    newWindow = s;
                }
            }

            Assertions.assertFalse(newWindow.isEmpty(), "Opened in new window");

            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }

    }

    private void openAdminPageByURL(String URL) {
        driver.get(URL);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }
}
