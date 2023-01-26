package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="li.product a.link")
    public List<WebElement> productItems;

    @FindBy(className="quantity")
    public WebElement counterLabel;

    @FindBy(xpath="//a[contains(text(), 'Checkout')]")
    public WebElement checkoutButton;

    public List<WebElement> stickers(WebElement productItem) {
        return productItem.findElements(By.className("sticker"));
    }

    @FindBy(css = "#box-campaigns li")
    public WebElement campaignsItem;

    public WebElement nameByItem(WebElement product) {
        return product.findElement(By.className("name"));
    }

    public WebElement priceByItem(WebElement product) {
        return product.findElement(By.className("regular-price"));
    }

    public WebElement priceCampByItem(WebElement product) {
        return product.findElement(By.className("campaign-price"));
    }

    public String getPriceStyleByItem(WebElement product) {
        return priceByItem(product).getCssValue("text-decoration-line");
    }

    public String getPriceColorByItem(WebElement product) {
        return priceByItem(product).getCssValue("color");
    }

    public String getPriceSizeByItem(WebElement product) {
        return priceByItem(product).getCssValue("font-size");
    }

    public String getPriceCampFWByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("font-weight");
    }

    public String getPriceCampColorByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("color");
    }

    public String getPriceCampSizeByItem(WebElement product) {
        return priceCampByItem(product).getCssValue("font-size");
    }

    @FindBy(css = "#box-account-login a[href*=create_account]")
    public WebElement newCustomerLink;

    @FindBy(xpath = "//*[contains(text(), 'Logout')]")
    public WebElement logoutBTN;

    @FindBy(name = "login")
    public WebElement loginBTN;

    @FindBy(name="email")
    public WebElement email;

    @FindBy(name="password")
    public WebElement password;
}
