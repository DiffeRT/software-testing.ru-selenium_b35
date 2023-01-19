package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.CountriesPageSteps;

import java.net.MalformedURLException;
import java.util.List;

public class CountriesZonesSortingTest extends BaseTest {
    CountriesPageSteps countriesPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        countriesPageSteps = new CountriesPageSteps(driver);
    }

    @Test
    public void countriesSortingTest() {
        List<String> countriesList = countriesPageSteps.open()
                .getCountryList();
        countriesPageSteps.verifyThatListASCSorted(countriesList, "Country list should be sorted in ASC order");
    }

    @Test
    public void countryZonesSortingTest() {
        List<Integer> indexes = countriesPageSteps.open()
                .getIndexesWithZones();

        for (Integer index : indexes) {
            List<String> zones = countriesPageSteps.open()
                    .openCountryByIndex(index)
                    .getZonesList();
            countriesPageSteps.verifyThatListASCSorted(zones, "Zones list should be sorted in ASC order");
        }
    }
}
