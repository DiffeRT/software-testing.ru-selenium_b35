package pages;

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
}
