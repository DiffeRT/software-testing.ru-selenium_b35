import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.*;

public class Task08Test {
    private WebDriver driver;

    private final By LOGIN_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.name("login");

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

    @Test
    public void countriesSortingTest() {
        openAdminPageByURL("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countryList = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']/td[5]"));
        ArrayList<String> a = new ArrayList<>();
        for (WebElement country : countryList) {
            a.add(country.getText());
        }
        ArrayList<String> aSorted = new ArrayList<>(a);
        Collections.sort(aSorted);
        //System.out.println(a.size());

        boolean sortedASC = a.equals(aSorted);
        Assertions.assertTrue(sortedASC, "Country list sorted ASC");
    }

    @Test
    public void myTest() {
        openAdminPageByURL("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> zoneList = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']/td[6]"));
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < zoneList.size(); i++) {
            if (Integer.parseInt(zoneList.get(i).getText()) > 0) {
                indexes.add(i+1);
            }
        }

        for (Integer index : indexes) {
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

            driver.findElement(By.xpath("//table[@class='dataTable']//tr[@class='row'][" + index + "]/td[5]/a")).click();

            List<WebElement> zones = driver.findElements(By.xpath("//table[@id='table-zones']//tr/td[3]"));
            ArrayList<String> zonesList = new ArrayList<>();
            for (WebElement zone : zones) {
                if (!zone.getText().isEmpty()) {
                    zonesList.add(zone.getText());
                }
            }

            ArrayList<String> zonesSorted = new ArrayList<>(zonesList);
            Collections.sort(zonesSorted);
            //System.out.println(zonesSorted.size());

            boolean sortedASC = zonesList.equals(zonesSorted);
            Assertions.assertTrue(sortedASC, "Zones list sorted ASC");
        }
    }
}
