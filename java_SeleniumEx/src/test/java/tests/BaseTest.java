package tests;

import app.ConfigConstants;
import app.ConfigDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void start() throws MalformedURLException {
        //driver = ConfigDriver.startBrowserLocal("chrome");
        driver = ConfigDriver.startBrowserRemoteStandalone(ConfigConstants.REMOTE_STANDALONE_SERVER);
        //driver = ConfigDriver.startBrowserRemoteHub(ConfigConstants.REMOTE_HUB_SERVER, "linux");
        ConfigDriver.setBrowserSize(driver, 1440, 900);
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }
}
