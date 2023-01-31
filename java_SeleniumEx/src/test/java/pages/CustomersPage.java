package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersPage extends BasePage {
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dataTable tr.row")
    public List<WebElement> customerRows;
}
