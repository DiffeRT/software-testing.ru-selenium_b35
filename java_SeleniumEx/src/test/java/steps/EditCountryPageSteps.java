package steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EditCountryPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EditCountryPageSteps extends BaseSteps {
    EditCountryPage editCountryPage;
    String mainTab;

    public EditCountryPageSteps(WebDriver driver) {
        super(driver);
        editCountryPage = new EditCountryPage(driver);
    }

    public List<String> getZonesList() {
        ArrayList<String> zones = new ArrayList<>();
        for (WebElement zone : editCountryPage.countryZones) {
            if (!zone.getText().isEmpty()) {
                zones.add(zone.getText());
            }
        }
        return zones;
    }

    public List<WebElement> getExternalLinks() {
        return editCountryPage.externalLinks;
    }

    public void OpenLink(WebElement link) {
        mainTab = driver.getWindowHandle();
        link.click();
        wait.until((WebDriver d) -> d.getWindowHandles().size() > 1);
    }

    public String getOtherTab(String mainWindow) {
        Set<String> windows = driver.getWindowHandles();
        String newWindow = "";
        for (String s : windows) {
            if (!s.equals(mainWindow)) {
                newWindow = s;
            }
        }
        return newWindow;
    }

    public void closeTab() {
        driver.switchTo().window(getOtherTab(mainTab));
        driver.close();
        driver.switchTo().window(mainTab);
    }

    public void VerifyThatNewTabOpened() {
        String newWindow = getOtherTab(mainTab);
        Assertions.assertFalse(newWindow.isEmpty(), "New tab should be opened");
    }
}
