import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class Task07Test {
    private WebDriver driver;

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
    public void checkStickersAvailabilityTest() {
        driver.get("http://localhost/litecart");

        List<WebElement> prodItems = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
        for (WebElement prodItem : prodItems) {
            List<WebElement> stickers = prodItem.findElements(By.className("sticker"));
            Assertions.assertEquals(1, stickers.size(), "Only one sticker available");
            System.out.println(stickers.get(0).getText());
        }
    }
}
