import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;

import java.io.File;
import java.time.Duration;

public class Task04Test {
    private final String URL_LOGIN_PAGE = "http://localhost/litecart/admin";

    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");
    private final By LOGIN_FORM = By.name("login_form");
    private final By LOGOUT_BUTTON = By.className("fa-sign-out");

    private WebDriver driver;

    private String getBrowser() {
        return "yandex";
    }

    @BeforeEach
    public void start() {
        switch (getBrowser()) {
            case "yandex":
                driver = new ChromeDriver(
                        new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("c:\\AQA_Tools\\yandexdriver.exe")).build());
                break;
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe")));
                driver = new FirefoxDriver(options);
                break;
            case "firefox_nightly":
                FirefoxOptions nightlyOptions = new FirefoxOptions();
                nightlyOptions.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
                driver = new FirefoxDriver(nightlyOptions);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if ("firefox_nightly".equals(getBrowser())) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }

    @Test
    @DisplayName("Login test with correct credentials")
    public void correctLoginTest() {
        driver.get(URL_LOGIN_PAGE);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
        boolean logoutIsVisible = driver.findElement(LOGOUT_BUTTON).isDisplayed();
        Assertions.assertTrue(logoutIsVisible, "Login with correct credentials");
    }

    @Test
    @DisplayName("Login test with incorrect credentials")
    public void incorrectLoginTest() {
        driver.get(URL_LOGIN_PAGE);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin1");
        driver.findElement(LOGIN_BUTTON).click();
        boolean notLoggedOn = driver.findElement(LOGIN_FORM).isDisplayed();
        Assertions.assertTrue(notLoggedOn, "Login with incorrect credentials");
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }
}
