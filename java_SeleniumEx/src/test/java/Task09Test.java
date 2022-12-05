import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task09Test {
    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

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

    private void openAdminPageByURL(String URL) {
        driver.get(URL);
        driver.findElement(LOGIN_FIELD).sendKeys("admin");
        driver.findElement(PASSWORD_FIELD).sendKeys("admin");
        driver.findElement(LOGIN_BUTTON).click();
    }

    private ArrayList<String> getElementsText(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        ArrayList<String> result = new ArrayList<>();
        for (WebElement element : elements) {
            result.add(element.getText());
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("countryListFactory")
    public void geoZonesSortingTest(String pageURL) {
        openAdminPageByURL(pageURL);
        ArrayList<String> zones = getElementsText(By.xpath("//table[@id='table-zones']//tr/td[3]/select/option[@selected='selected']"));
        ArrayList<String> zonesSorted = new ArrayList<>(zones);
        Collections.sort(zonesSorted);
        //System.out.println(zones.size());

        boolean sortedASC = zones.equals(zonesSorted);
        Assertions.assertTrue(sortedASC, "Zones list sorted ASC");
    }

    private Stream<String> countryListFactory() {
        driver = Config.startBrowser("chrome");
        try {
            openAdminPageByURL("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            List<WebElement> countryList = driver.findElements(By.xpath("//*[@name='geo_zones_form']//tr[@class='row']/td[3]//a"));
            ArrayList<String> countriesPages = new ArrayList<>();
            for (WebElement webElement : countryList) {
                countriesPages.add(webElement.getAttribute("href"));
            }
            String[] result = countriesPages.toArray(new String[0]);

            return Stream.of(result);
        }
        finally {
            driver.quit();
        }
    }
}
