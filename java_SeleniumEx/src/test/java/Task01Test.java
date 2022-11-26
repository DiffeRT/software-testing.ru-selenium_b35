import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task01Test {
    public final By SEARCH_FIELD = By.name("text");
    public final By FIND_BUTTON = By.xpath(".//*[@class='search3__inner']/button[last()]");

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void searchPageTest() {
        driver.get("https://www.ya.ru");
        driver.findElement(SEARCH_FIELD).sendKeys("software testing selenium");
        driver.findElement(FIND_BUTTON).click();
        wait.until(titleContains("software testing selenium"));
        Assertions.assertTrue(true, "We are good!");
    }

    @AfterAll
    public void stop() {
        driver.quit();
    }
}
