package steps;

import app.ConfigConstants;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.AdminPage;

import java.time.Duration;

public class AdminPageSteps extends BaseSteps {
    AdminPage adminPage;

    public AdminPageSteps(WebDriver driver) {
        super(driver);
        adminPage = new AdminPage(driver);
    }

    public AdminPageSteps open() {
        driver.get("http://localhost/litecart/admin");
        return this;
    }

    public boolean isUserLoggedOn() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return adminPage.logoutButtonElements.size() == 1;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }

    public void loginAs(String user, String password) {
        adminPage.userName.sendKeys(user);
        adminPage.password.sendKeys(password);
        adminPage.loginButton.click();
    }

    public AdminPageSteps openAndLogin() {
        open().loginAs("admin", "admin");
        return this;
    }

    public int getMenuItemsCount() {
        return adminPage.menuItems.size();
    }

    public int getSubMenuItemsCount(int index) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return adminPage.subMenuItemsByIndex(index).size();
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }

    public void clickMenuItemByIndex(int index) {
        adminPage.menuItemByIndex(index).click();
    }

    public void clickSubMenuItemByIndex(int index1, int index2) {
        adminPage.subMenuItemByIndexes(index1, index2).click();
    }

    public CatalogPageSteps clickMenuItemCatalog() {
        adminPage.catalogMenuItem.click();
        return new CatalogPageSteps(driver);
    }

    public void verifyThatUserLoggedIn() {
        Assertions.assertTrue(adminPage.logoutButton.isDisplayed(), "Logout button should be visible");
    }

    public void verifyThatUserLoggedOff() {
        Assertions.assertTrue(adminPage.loginForm.isDisplayed(), "Login form should be visible");
    }

    public void verifyThatContentHeaderFound() {
        boolean isHeaderFound = wait.until(
                (WebDriver d) -> adminPage.contentHeader.isDisplayed()
        );
        Assertions.assertTrue(isHeaderFound, "Content header should be visible");
    }
}
