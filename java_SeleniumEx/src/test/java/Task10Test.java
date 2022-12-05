import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task10Test {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        driver = Config.startBrowser("firefox");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void stop() {
        driver.quit();
    }

    @Test
    public void checkProductItemConsistencyTest() {
        driver.get("http://localhost/litecart");

        WebElement duck = driver.findElement(By.cssSelector("#box-campaigns li"));

        String duckName = duck.findElement(By.className("name")).getText();
        String duckPrice = duck.findElement(By.className("regular-price")).getText();
        String duckPriceCa = duck.findElement(By.className("campaign-price")).getText();

        duck.click();
        duck = driver.findElement(By.id("box-product"));

        String duckNameAct = duck.findElement(By.cssSelector("h1")).getText();
        String duckPriceAct = duck.findElement(By.className("regular-price")).getText();
        String duckPriceCaAct = duck.findElement(By.className("campaign-price")).getText();

        Assertions.assertEquals(duckName, duckNameAct, "Name matches");
        Assertions.assertEquals(duckPrice, duckPriceAct, "Price matches");
        Assertions.assertEquals(duckPriceCa, duckPriceCaAct, "Campaign Price matches");
    }

    @Test
    public void checkProductItemStyleTest() {
        driver.get("http://localhost/litecart");

        WebElement duck = driver.findElement(By.cssSelector("#box-campaigns li"));

        String duckPriceStyle = duck.findElement(By.className("regular-price")).getCssValue("text-decoration-line");
        String duckPriceColor = duck.findElement(By.className("regular-price")).getCssValue("color");
        HashMap<String, Integer> regPriceColor = getColor(duckPriceColor);

        String duckPriceCaBold = duck.findElement(By.className("campaign-price")).getCssValue("font-weight");
        int caPriceBoldValue = Integer.parseInt(duckPriceCaBold);
        String duckPriceCaColor = duck.findElement(By.className("campaign-price")).getCssValue("color");
        HashMap<String, Integer> caPriceColor = getColor(duckPriceCaColor);

        String duckPriceHeight = duck.findElement(By.className("regular-price")).getCssValue("font-size");
        String duckPriceCaHeight = duck.findElement(By.className("campaign-price")).getCssValue("font-size");
        float regPriceH = getHeight(duckPriceHeight);
        float caPriceH = getHeight(duckPriceCaHeight);

        Assertions.assertEquals("line-through", duckPriceStyle, "Regular Price is line-through");
        Assertions.assertTrue(regPriceColor.get("R").equals(regPriceColor.get("G")) && regPriceColor.get("G").equals(regPriceColor.get("B")), "Regular Price has Grey color");
        Assertions.assertTrue(caPriceBoldValue >= 700, "Campaign Price is bold");                       // bold is >= 700
        Assertions.assertTrue(caPriceColor.get("G").equals(0) && caPriceColor.get("B").equals(0), "Campaign Price has Red color");
        Assertions.assertTrue(caPriceH > regPriceH, "Campaign Price Height >= The Regular one");
    }

    @Test
    public void checkOpenedProductItemStyleTest() {
        driver.get("http://localhost/litecart");
        driver.findElement(By.cssSelector("#box-campaigns li")).click();
        WebElement duck = driver.findElement(By.id("box-product"));

        String duckPriceCaBold = duck.findElement(By.className("campaign-price")).getCssValue("font-weight");
        int caPriceBoldValue = Integer.parseInt(duckPriceCaBold);
        String duckPriceCaColor = duck.findElement(By.className("campaign-price")).getCssValue("color");
        HashMap<String, Integer> caPriceColor = getColor(duckPriceCaColor);

        String duckPriceHeight = duck.findElement(By.className("regular-price")).getCssValue("font-size");
        String duckPriceCaHeight = duck.findElement(By.className("campaign-price")).getCssValue("font-size");
        float regPriceH = getHeight(duckPriceHeight);
        float caPriceH = getHeight(duckPriceCaHeight);

        Assertions.assertTrue(caPriceBoldValue >= 700, "Campaign Price is bold");                       // bold is >= 700
        Assertions.assertTrue(caPriceColor.get("G").equals(0) && caPriceColor.get("B").equals(0), "Campaign Price has Red color");
        Assertions.assertTrue(caPriceH > regPriceH, "Campaign Price Height >= The Regular one");
    }

    // Get RGB from the string
    private HashMap<String, Integer> getColor(String color) {
        StringBuilder sb = new StringBuilder();
        List<String> cl = new ArrayList<>();
        int pos = 0;
        while (pos < color.length()) {
            char c = color.charAt(pos);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
            else if ((c == ',' || c == ')') && sb.length() > 0) {
                cl.add(sb.toString());
                sb.setLength(0);
            }
            pos++;
        }
        HashMap<String, Integer> result = new HashMap<>();
        result.put( "R", Integer.parseInt(cl.get(0)) );
        result.put( "G", Integer.parseInt(cl.get(1)) );
        result.put( "B", Integer.parseInt(cl.get(2)) );
        return result;
    }

    //Get height from the string
    private float getHeight(String height) {
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        while (pos < height.length()) {
            char c = height.charAt(pos);
            if ((c >= '0' && c <= '9') || c == '.') {
                sb.append(c);
            }
            pos++;
        }
        return Float.parseFloat(sb.toString());
    }
}
