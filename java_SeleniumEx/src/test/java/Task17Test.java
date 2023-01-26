import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.assertj.core.api.SoftAssertions;


import java.net.MalformedURLException;
import java.net.URL;
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
    public void start() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        driver = startRemoteStandaloneDriver(options);
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void browserLogTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        openAdminPageByURL("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> itemsList = driver.findElements(By.xpath("//td[3]/a[contains(@href, 'edit_product')]"));
        ArrayList<String> itemsPages = new ArrayList<>();
        for (WebElement webElement : itemsList) {
            itemsPages.add(webElement.getAttribute("href"));
        }

        List<LogEntry> logs = new ArrayList<>();
        for (String href : itemsPages) {
            driver.findElement(By.cssSelector("[href='" + href + "']")).click();
            driver.findElement(CANCEL_BUTTON).click();
            logs = driver.manage().logs().get("performance").getAll();
            System.out.println(logs);

            softAssertions.assertThat(logs.size() == 0);
        }
        softAssertions.assertAll();
    }

    private void openAdminPageByURL(String URL) {
        driver.get(URL);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }

    public WebDriver startRemoteStandaloneDriver(ChromeOptions options) throws MalformedURLException {
        WebDriver driver;
        driver = new RemoteWebDriver(new URL("http://192.168.248.131:4444/wd/hub"), options);
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1440,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
