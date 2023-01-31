package tests;

/*   Task 11   */

import app.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.CustomersPageSteps;
import steps.MainPageSteps;
import java.net.MalformedURLException;
import java.util.Set;

public class CustomerAccountTest extends BaseTest {
    private MainPageSteps mainPageSteps;
    private CustomersPageSteps customersPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        mainPageSteps = new MainPageSteps(driver);
        customersPageSteps = new CustomersPageSteps(driver);
    }

    @Test
    public void registerNewUserReqFieldsTest() {
        Customer user = new Customer().setRandomCustomer();

        Set<String> oldIds = customersPageSteps.getCustomerIds();
        System.out.println(oldIds);

        mainPageSteps.open()
                .clickNewCustomerLink()
                .fillRequiredFields(user)
                .save();
        mainPageSteps.verifyThatCustomerLoggedIn();

        mainPageSteps.logOut()
                .loginAs(user.email, user.password)
                .logOut();
        mainPageSteps.verifyThatCustomerLoggedOut();

        customersPageSteps.verifyThatCustomerHasBeenAdded(user, oldIds);
    }

    @Test
    public void userLoginTest() {
        mainPageSteps.open()
                .loginAs("user@email.com", "user123");
        mainPageSteps.verifyThatCustomerLoggedIn();

        mainPageSteps.logOut();
        mainPageSteps.verifyThatCustomerLoggedOut();
    }
}
