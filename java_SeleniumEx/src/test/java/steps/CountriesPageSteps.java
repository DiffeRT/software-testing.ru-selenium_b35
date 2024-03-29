package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CountriesPage;
import java.util.ArrayList;
import java.util.List;

public class CountriesPageSteps extends BaseSteps {
    CountriesPage countriesPage;

    public CountriesPageSteps(WebDriver driver) {
        super(driver);
        countriesPage = new CountriesPage(driver);
    }

    @Step("Open Countries page")
    public CountriesPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        return this;
    }

    @Step("Get the list of the countries")
    public List<String> getCountryList() {
        ArrayList<String> result = new ArrayList<>();
        for (WebElement country : countriesPage.countries) {
            result.add(country.getText());
        }
        return result;
    }

    @Step("Open country by index {index}")
    public EditCountryPageSteps openCountryByIndex(int index) {
        countriesPage.countryByIndex(index).click();
        return new EditCountryPageSteps(driver);
    }

    @Step("Get the countries which has country zones")
    public List<Integer> getIndexesWithZones() {
        List<WebElement> zoneList = countriesPage.countryZones;
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < zoneList.size(); i++) {
            //Looks like getText() works slow when using remote connection
            if (Integer.parseInt(zoneList.get(i).getText()) > 0) {
                indexes.add(i+1);
            }
        }
        return indexes;
    }

}
