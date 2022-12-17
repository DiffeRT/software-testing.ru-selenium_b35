import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Task11Test {
    private WebDriver driver;
    private final By CREATE_ACCOUNT = By.cssSelector("#box-account-login a[href*=create_account]");
    private final By FIRST_NAME = By.name("firstname");
    private final By LAST_NAME = By.name("lastname");
    private final By ADDRESS1 = By.name("address1");
    private final By POST_CODE = By.name("postcode");
    private final By CITY = By.name("city");
    private final By PHONE = By.name("phone");
    private final By EMAIL = By.name("email");
    private final By PWD = By.name("password");
    private final By PWD_CONF = By.name("confirmed_password");
    private final By COUNTRY_ARR = By.className("select2-selection__arrow");
    private final By ZONE_CODE = By.cssSelector("select[name=zone_code]");
    private final By CREATE_BTN = By.name("create_account");

    private final By LOGOUT = By.xpath("//*[contains(text(), 'Logout')]");
    private final By LOGIN_BTN = By.name("login");

    @BeforeEach
    public void start() {
        driver = Config.startBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void registerNewUserReqFieldsTest() {
        driver.get("http://localhost/litecart");
        driver.findElement(CREATE_ACCOUNT).click();

        String user = fillNewAccountRandomEmail(driver, "admin123");
        driver.findElement(CREATE_BTN).click();

        Assertions.assertTrue(driver.findElement(LOGOUT).isDisplayed(), "Account created");

        driver.findElement(LOGOUT).click();
        userLogin(driver, user, "admin123");
        driver.findElement(LOGOUT).click();

        Assertions.assertTrue(driver.findElement(LOGIN_BTN).isDisplayed(), "Account logout");
    }

    public String fillNewAccountRandomEmail(WebDriver driver, String password) {
        String eMail = "rustem" + (int)(Math.random() * 1000) + ".berkaliev" + (int)(Math.random() * 1000) + "@mail.com";

        driver.findElement(FIRST_NAME).sendKeys("Rustem");
        driver.findElement(LAST_NAME).sendKeys("Berkaliev");
        driver.findElement(ADDRESS1).sendKeys("101 Oak Creek Blvd");
        driver.findElement(POST_CODE).sendKeys("95066");
        driver.findElement(CITY).sendKeys("Scotts Valley");
        driver.findElement(PHONE).sendKeys("+18314327654");
        driver.findElement(EMAIL).sendKeys(eMail);
        driver.findElement(PWD).sendKeys(password);
        driver.findElement(PWD_CONF).sendKeys(password);

        driver.findElement(COUNTRY_ARR).click();
        driver.findElement(By.xpath("//li[text()='United States']")).click();

        WebElement zone = driver.findElement(ZONE_CODE);
        Select zoneSelector = new Select(zone);
        zoneSelector.selectByVisibleText("California");

        return eMail;
    }

    public void userLogin(WebDriver driver, String user, String password) {
        driver.findElement(EMAIL).sendKeys(user);
        driver.findElement(PWD).sendKeys(password);
        driver.findElement(LOGIN_BTN).click();
    }
}
