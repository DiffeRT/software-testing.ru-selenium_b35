package pages;

import app.ConfigConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected boolean elementIsNotFound(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return driver.findElements(locator).size() == 0;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }
}
