package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CountriesPage extends BasePage {

    public CountriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr[@class='row']/td[5]")
    public List<WebElement> countries;

    @FindBy(xpath = "//tr[@class='row']/td[6]")
    public List<WebElement> countryZones;

    @FindBy(xpath = "//*[@id='table-zones']//td[3]")
    public List<WebElement> zoneNames;

    public WebElement countryByIndex(int index) {
        return driver.findElement(By.xpath("//tr[@class='row'][" + index + "]/td[5]/a"));
    }
}
