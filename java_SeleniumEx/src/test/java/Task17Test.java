import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class Task17Test {
    private WebDriver driver;

    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");
    private final By CANCEL_BUTTON = By.name("cancel");


    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        driver = new ChromeDriver(options);

        //driver = Config.startBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void browserLogTest() {
        openAdminPageByURL("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> itemsList = driver.findElements(By.xpath("//td[3]/a[contains(@href, 'edit_product')]"));
        ArrayList<String> itemsPages = new ArrayList<>();
        for (WebElement webElement : itemsList) {
            itemsPages.add(webElement.getAttribute("href"));
        }

//        List<LogEntry> logs = new ArrayList<>();
        for (String href : itemsPages) {
            driver.findElement(By.cssSelector("[href='" + href + "']")).click();
            driver.findElement(CANCEL_BUTTON).click();
//            logs = driver.manage().logs().get("performance").getAll();
//            System.out.println(logs);
        }
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        System.out.println(logs);
        Assertions.assertEquals(0, logs.size(), "Log should be empty");
    }

    private void openAdminPageByURL(String URL) {
        driver.get(URL);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }
}
