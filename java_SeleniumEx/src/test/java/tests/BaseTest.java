package tests;

import app.ConfigDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void start() {
        driver = ConfigDriver.startBrowserLocal("chrome");
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }
}
