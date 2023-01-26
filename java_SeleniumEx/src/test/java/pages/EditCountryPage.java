package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditCountryPage extends BasePage {
    public EditCountryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr[@class='row']/td[6]")
    public List<WebElement> countryZones;

    @FindBy(css = "form a[target=_blank]")
    public List<WebElement> externalLinks;
}
