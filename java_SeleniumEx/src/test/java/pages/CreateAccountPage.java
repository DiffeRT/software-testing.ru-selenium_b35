package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage {
    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="firstname")
    public WebElement firstName;

    @FindBy(name="lastname")
    public WebElement lastName;

    @FindBy(name="address1")
    public WebElement address1;

    @FindBy(name="postcode")
    public WebElement postCode;

    @FindBy(name="city")
    public WebElement city;

    @FindBy(name="phone")
    public WebElement phone;

    @FindBy(name="email")
    public WebElement email;

    @FindBy(name="password")
    public WebElement password;

    @FindBy(name="confirmed_password")
    public WebElement passwordConfirm;

    @FindBy(name="create_account")
    public WebElement createBTN;

    @FindBy(className="select2-selection__arrow")
    public WebElement countryList;

    public WebElement getCountryOptionByName(String name) {
        return driver.findElement(By.xpath(String.format("//li[text()='%s']", name)));
    }

    @FindBy(css="select[name=zone_code]")
    public WebElement stateList;
}
