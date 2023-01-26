package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends BasePage {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td[3]/a[contains(@href, 'edit_product')]")
    public List<WebElement> items;

    @FindBy(name = "cancel")
    public WebElement cancelBTN;

    @FindBy(xpath = "//*[contains(text(), 'Add New Product')]")
    public WebElement addNewProduct;

    public List<String> getItemsHRefs() {
        ArrayList<String> result = new ArrayList<>();
        for (WebElement item : items) {
            result.add(item.getAttribute("href"));
        }
        return result;
    }

    public WebElement getItemByHRef(String href) {
        return driver.findElement(By.cssSelector("[href='" + href + "']"));
    }

    @FindBy(className = "dataTable")
    public WebElement table;

    public WebElement getItemByName(String name) {
        return table.findElement(By.xpath(".//*[contains(text(), '" + name + "')]"));
    }
}
