package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="username")
    public WebElement userName;

    @FindBy(name="password")
    public WebElement password;

    @FindBy(name="login")
    public WebElement loginButton;

    @FindBy(name="login_form")
    public WebElement loginForm;

    @FindBy(className="fa-sign-out")
    public WebElement logoutButton;

    @FindBy(className="fa-sign-out")
    public List<WebElement> logoutButtonElements;

    @FindBy(xpath="//ul[@id='box-apps-menu']/li")
    public List<WebElement> menuItems;

    public WebElement menuItemByIndex(int index) {
        return driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[" + index + "]"));
    }

    public List<WebElement> subMenuItemsByIndex(int index) {
        return menuItemByIndex(index).findElements(By.xpath(".//li"));
    }

    public WebElement subMenuItemByIndexes(int index1, int index2) {
        return subMenuItemsByIndex(index1).get(index2);
    }

    @FindBy(css="td#content h1")
    public WebElement contentHeader;

}
