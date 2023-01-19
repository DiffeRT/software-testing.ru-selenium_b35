package steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CountriesPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesPageSteps extends BaseSteps {
    CountriesPage countriesPage;

    public CountriesPageSteps(WebDriver driver) {
        super(driver);
        countriesPage = new CountriesPage(driver);
    }

    public CountriesPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        return this;
    }

    public List<String> getCountryList() {
        ArrayList<String> result = new ArrayList<>();
        for (WebElement country : countriesPage.countries) {
            result.add(country.getText());
        }
        return result;
    }

    public List<Integer> getIndexesWithZones() {
        List<WebElement> zoneList = countriesPage.countryZones;
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < zoneList.size(); i++) {
            if (Integer.parseInt(zoneList.get(i).getText()) > 0) {
                indexes.add(i+1);
            }
        }
        return indexes;
    }

    public CountriesPageSteps openCountryByIndex(int index) {
        countriesPage.countryByIndex(index).click();
        return this;
    }

    public List<String> getZonesList() {
        ArrayList<String> zones = new ArrayList<>();
        for (WebElement zone : countriesPage.countryZones) {
            if (!zone.getText().isEmpty()) {
                zones.add(zone.getText());
            }
        }
        return zones;
    }

    public void verifyThatListASCSorted(List<String> list, String msg) {
        ArrayList<String> aSorted = new ArrayList<>(list);
        Collections.sort(aSorted);
        Assertions.assertEquals(list, aSorted, msg);
    }
}
