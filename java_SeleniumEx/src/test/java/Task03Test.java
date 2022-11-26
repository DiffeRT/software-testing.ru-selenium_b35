import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Task03Test {
    private final String URL_LOGIN_PAGE = "http://localhost/litecart/admin";

    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");
    private final By LOGIN_FORM = By.name("login_form");
    private final By LOGOUT_BUTTON = By.className("fa-sign-out");

    private WebDriver driver;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void correctLoginTest() {
        driver.get(URL_LOGIN_PAGE);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
        boolean logoutIsVisible = driver.findElement(LOGOUT_BUTTON).isDisplayed();
        Assertions.assertTrue(logoutIsVisible, "Login with correct credentials");
    }

    @Test
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
