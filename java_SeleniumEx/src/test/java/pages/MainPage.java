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
}
