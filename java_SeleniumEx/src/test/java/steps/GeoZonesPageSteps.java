package steps;

import io.qameta.allure.Step;
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

    @Step("Open Geo Zones page")
    public GeoZonesPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        return this;
    }

    @Step("Get countries count")
    public int getCountriesCount() {
        return geoZonesPage.countries.size();
    }

    @Step("Open Country by index {index}")
    public GeoZonesPageSteps openCountryByIndex(int index) {
        geoZonesPage.countryByIndex(index).click();
        return this;
    }

    @Step("Get the list of the Zones")
    public List<String> getZonesList() {
        List<WebElement> zoneNames = geoZonesPage.zoneNames;
        ArrayList<String> result = new ArrayList<>();
        for (WebElement zoneName : zoneNames) {
            result.add(zoneName.getText());
        }
        return result;
    }
}
