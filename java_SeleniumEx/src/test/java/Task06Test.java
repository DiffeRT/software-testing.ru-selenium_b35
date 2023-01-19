import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task06Test {
    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void start() throws MalformedURLException {
        //driver = Config.startBrowser("chrome");
        driver = Config.startRemoteStandaloneDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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

    @Test
    public void clickAllMenuItemsTest() {
        openAdminPage();
        int level1Count = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();

        for (int i = 0; i < level1Count; i++) {
            driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[" + (i+1) + "]")).click();
            boolean isHeaderFound = wait.until(
                    (WebDriver d) -> d.findElement(By.cssSelector("td#content h1")).isDisplayed()
            );
            Assertions.assertTrue(isHeaderFound, "Menu Item Clicked");
            System.out.println(i+1);

            int innerICount = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[" + (i+1) + "]//li")).size();
            if (innerICount > 0) {
                for (int j = 0; j < innerICount; j++) {
                    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[" + (i+1) + "]//li[" + (j+1) + "]")).click();
                    boolean isInnerHeaderFound = wait.until(
                            (WebDriver d) -> d.findElement(By.cssSelector("td#content h1")).isDisplayed()
                    );
                    Assertions.assertTrue(isInnerHeaderFound, "Menu Item Clicked");
                    System.out.println("--" + (j+1));
                }
            }
        }
    }

}
