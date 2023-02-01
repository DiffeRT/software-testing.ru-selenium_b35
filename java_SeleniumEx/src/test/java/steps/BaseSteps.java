package steps;

import app.ConfigConstants;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseSteps {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Verify that: {msg}")
    public void verifyThatListASCSorted(List<String> list, String msg) {
        ArrayList<String> aSorted = new ArrayList<>(list);
        Collections.sort(aSorted);
        Assertions.assertEquals(list, aSorted, msg);
    }
}
