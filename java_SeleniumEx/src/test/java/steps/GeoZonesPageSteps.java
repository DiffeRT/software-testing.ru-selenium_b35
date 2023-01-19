package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GeoZonesPage;

import java.util.ArrayList;
import java.util.List;

public class GeoZonesPageSteps extends BaseSteps {
    GeoZonesPage geoZonesPage;

    public GeoZonesPageSteps(WebDriver driver) {
        super(driver);
        geoZonesPage = new GeoZonesPage(driver);
    }

    public GeoZonesPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        return this;
    }

    public int getCountriesCount() {
        return geoZonesPage.countries.size();
    }

    public GeoZonesPageSteps openCountryByIndex(int index) {
        geoZonesPage.countryByIndex(index).click();
        return this;
    }

    public List<String> getZonesList() {
        List<WebElement> zoneNames = geoZonesPage.zoneNames;
        ArrayList<String> result = new ArrayList<>();
        for (WebElement zoneName : zoneNames) {
            result.add(zoneName.getText());
        }
        return result;
    }
}
