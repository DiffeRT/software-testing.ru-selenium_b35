package tests;

/*   Task 11   */

import app.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.MainPageSteps;
import java.net.MalformedURLException;

public class CustomerAccountTest extends BaseTest {
    private MainPageSteps mainPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        mainPageSteps = new MainPageSteps(driver);
    }

    @Test
    public void registerNewUserReqFieldsTest() {
        Customer user = new Customer().setRandomCustomer();

        mainPageSteps.open()
                .clickNewCustomerLink()
                .fillRequiredFields(user)
                .save();
        mainPageSteps.verifyThatCustomerLoggedIn();

        mainPageSteps.logOut()
                .loginAs(user.email, user.password)
                .logOut();
        mainPageSteps.verifyThatCustomerLoggedOut();
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
