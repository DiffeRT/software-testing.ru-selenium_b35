import app.ConfigConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Task15Test {
    private WebDriver driver;

    public final By YA_SEARCH_FIELD = By.name("text");
    public final By YA_FIND_BUTTON = By.cssSelector("button.search3__button");

    public final By G_SEARCH_FIELD = By.name("q");
    public final By G_FIND_BUTTON = By.name("btnK");

    @BeforeEach
    public void start() throws MalformedURLException {
//        driver = startSimpleDriver();
//        driver = startRemoteStandaloneDriver();
//        driver = startRemoteHubDriver("linux");
        driver = startRemoteCloudDriver("WINDOWS");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void actionsBasketTest() throws InterruptedException {
        driver.get("https://ya.ru");
        driver.findElement(YA_SEARCH_FIELD).sendKeys("java thread sleep");
        driver.findElement(YA_FIND_BUTTON).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".main__center a")).click();
        Thread.sleep(3000);
        switchToNewTabAndClose();

        driver.get("https://google.ru");
        List<WebElement> co = driver.findElements(By.xpath("//button/div[contains(text(), 'Alles accepteren')]"));
        if (co.size() > 0) {
            co.get(0).click();
        }
        driver.findElement(G_SEARCH_FIELD).sendKeys("java thread sleep");
        driver.findElement(G_FIND_BUTTON).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#center_col a")).click();
        Thread.sleep(3000);
        switchToNewTabAndClose();
    }

    private void switchToNewTabAndClose() {
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        String newWindow = "";
        for (String s : windows) {
            if (!s.equals(mainWindow)) {
                newWindow = s;
            }
        }
        driver.switchTo().window(newWindow);
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    private WebDriver startSimpleDriver() {
        return new ChromeDriver();
    }

    private WebDriver startRemoteStandaloneDriver() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.0.167:4444/wd/hub"), new ChromeOptions());
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1440,900));
        return driver;
    }

    private WebDriver startRemoteHubDriver(String platform) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platformName", platform);
        driver = new RemoteWebDriver(new URL("http://192.168.0.167:4444/wd/hub"), chromeOptions);
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1440,900));
        return driver;
    }

    private WebDriver startRemoteCloudDriver(String platform) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platformName", platform);
        driver = new RemoteWebDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", ConfigConstants.BRO_STACK_USER, ConfigConstants.BRO_STACK_KEY)), chromeOptions);
        return driver;
    }
}
