package steps;

import app.ConfigConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSteps {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
