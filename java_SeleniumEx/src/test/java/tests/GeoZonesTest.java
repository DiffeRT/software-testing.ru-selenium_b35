package tests;

/*   Task 09   */

import org.junit.jupiter.api.Test;
import steps.GeoZonesPageSteps;

import java.util.List;

public class GeoZonesTest extends BaseTest {

    @Test
    public void geoZonesSortingTest() {
        GeoZonesPageSteps geoZonesPageSteps = new GeoZonesPageSteps(driver);

        int countryCount = geoZonesPageSteps.open()
                .getCountriesCount();

        for (int i = 0; i < countryCount; i++) {
            List<String> zones = geoZonesPageSteps.open()
                    .openCountryByIndex(i)
                    .getZonesList();

            geoZonesPageSteps.verifyThatListASCSorted(zones, "Zones list should be sorted in ASC order");
        }
    }
}
