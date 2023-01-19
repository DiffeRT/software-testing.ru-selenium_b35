package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GeoZonesPage extends BasePage {
    public GeoZonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr[@class='row']/td[3]//a")
    public List<WebElement> countries;

    @FindBy(xpath = "//td[3]/select/option[@selected='selected']")
    public List<WebElement> zoneNames;

    public WebElement countryByIndex(int index) {
        return countries.get(index);
    }
}
