package steps;

import app.Customer;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CustomersPage;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomersPageSteps extends BaseSteps {
    CustomersPage customersPage;

    public CustomersPageSteps(WebDriver driver) {
        super(driver);
        customersPage = new CustomersPage(driver);
    }

    public CustomersPageSteps open() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);
        if (!adminPageSteps.isUserLoggedOn()) {
            adminPageSteps.openAndLogin();
        }
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        return this;
    }

    public Set<String> getCustomerIds() {
        open();
        return customersPage.customerRows
                .stream()
                .map( e -> e.findElements(By.tagName("td")).get(2).getText() )
                .collect(Collectors.toSet());
    }

    public void verifyThatCustomerHasBeenAdded(Customer customer, Set<String> oldIds) {
        Set<String> newIds = getCustomerIds();

        Assertions.assertTrue(newIds.containsAll(oldIds), "Prev rows shouldn't be affected");
        Assertions.assertEquals(oldIds.size() + 1, newIds.size(), "Only one row should be added");
        Assertions.assertTrue(customerFoundByName(customer.getUserFullName()), "New name should be found in the table");
    }

    public boolean customerFoundByName(String customerName) {
        for (int i = 0; i < customersPage.customerRows.size(); i++) {
            String name = customersPage.customerRows.get(i).findElements(By.tagName("td")).get(4).getText();
            if (name.equals(customerName)) {
                return true;
            }
        }
        return false;
    }
}
