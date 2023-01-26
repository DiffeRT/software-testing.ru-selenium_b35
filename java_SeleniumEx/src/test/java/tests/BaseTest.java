package tests;

import app.ConfigConstants;
import app.ConfigDriver;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class BaseTest {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    @BeforeEach
    public void start() throws MalformedURLException {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            return;
        }

        //driver = ConfigDriver.startBrowserLocal("chrome");
        //driver = ConfigDriver.startBrowserRemoteStandalone(ConfigConstants.REMOTE_STANDALONE_SERVER);
        driver = ConfigDriver.startBrowserRemoteHub(ConfigConstants.REMOTE_HUB_SERVER, "linux");
        ConfigDriver.setBrowserSize(driver, 1440, 900);

        tlDriver.set(driver);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { driver.quit(); driver = null; }));
    }

}
