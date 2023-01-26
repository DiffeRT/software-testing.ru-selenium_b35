package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProductPage extends BasePage {
    public EditProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[href*='tab-information']")
    public WebElement tabInformation;

    @FindBy(css = "[href*='tab-prices']")
    public WebElement tabPrice;

    @FindBy(xpath = "//*[contains(text(), 'Enabled')]")
    public WebElement statusEnabled;

    @FindBy(name = "name[en]")
    public WebElement name;

    @FindBy(name = "quantity")
    public WebElement quantity;

    @FindBy(name = "new_images[]")
    public WebElement image;

    @FindBy(name = "date_valid_from")
    public WebElement dateFrom;

    @FindBy(name = "date_valid_to")
    public WebElement dateTo;

    @FindBy(name = "manufacturer_id")
    public WebElement manufacturer;

    @FindBy(name = "purchase_price")
    public WebElement price;

    @FindBy(name = "purchase_price_currency_code")
    public WebElement priceCurrency;

    @FindBy(name = "save")
    public WebElement saveBTN;

}
