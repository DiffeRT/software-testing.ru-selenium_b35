package steps;

import app.ConfigConstants;
import io.qameta.allure.Step;
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

    @Step("Open Admin page")
    public AdminPageSteps open() {
        driver.get("http://localhost/litecart/admin");
        return this;
    }

    @Step("Check if the user is logged in")
    public boolean isUserLoggedOn() {
        try {
            open();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return adminPage.logoutButtonElements.size() == 1;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }

    @Step("Login with: {user}, {password}")
    public void loginAs(String user, String password) {
        adminPage.userName.sendKeys(user);
        adminPage.password.sendKeys(password);
        adminPage.loginButton.click();
    }

    @Step("Login on Admin page")
    public AdminPageSteps openAndLogin() {
        open().loginAs("admin", "admin");
        return this;
    }

    @Step("Get menu items Count")
    public int getMenuItemsCount() {
        return adminPage.menuItems.size();
    }

    @Step("Get Sub Items count for Menu Item {index}")
    public int getSubMenuItemsCount(int index) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return adminPage.subMenuItemsByIndex(index).size();
        }
        finally {
            driver.manage().timeouts().implicitlyWait(ConfigConstants.IMPLICITLY_WAIT);
        }
    }

    @Step("Click Menu Item by index {index}")
    public void clickMenuItemByIndex(int index) {
        adminPage.menuItemByIndex(index).click();
    }

    @Step("Click Sub Menu Item ({index1}, {index2})")
    public void clickSubMenuItemByIndex(int index1, int index2) {
        adminPage.subMenuItemByIndexes(index1, index2).click();
    }

    @Step("Click on menu item Catalog")
    public CatalogPageSteps clickMenuItemCatalog() {
        adminPage.catalogMenuItem.click();
        return new CatalogPageSteps(driver);
    }

    @Step("Verify that the user is logged in")
    public void verifyThatUserLoggedIn() {
        Assertions.assertTrue(adminPage.logoutButton.isDisplayed(), "Logout button should be visible");
    }

    @Step("Verify that the user is logged off")
    public void verifyThatUserLoggedOff() {
        Assertions.assertTrue(adminPage.loginForm.isDisplayed(), "Login form should be visible");
    }

    @Step("Verify that the header is found on the opened page")
    public void verifyThatContentHeaderFound() {
        boolean isHeaderFound = wait.until(
                (WebDriver d) -> adminPage.contentHeader.isDisplayed()
        );
        Assertions.assertTrue(isHeaderFound, "Content header should be visible");
    }
}
