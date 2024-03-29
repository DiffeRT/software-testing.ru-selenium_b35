package tests;

/*   Task 03   */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AdminPageSteps;

import java.net.MalformedURLException;

@DisplayName("Admin Page Opening")
public class LoginAdminPageTest extends BaseTest {
    protected AdminPageSteps adminPageSteps;

    @BeforeEach
    public void start() throws MalformedURLException {
        super.start();
        adminPageSteps = new AdminPageSteps(driver);
    }

    @Test
    @DisplayName("Login with correct credentials")
    public void correctLoginTest() {
        adminPageSteps.open()
                .loginAs("admin", "admin");
        adminPageSteps.verifyThatUserLoggedIn();
    }

    @Test
    @DisplayName("Login with incorrect credentials")
    public void incorrectLoginTest() {
        adminPageSteps.open()
                .loginAs("admin", "admin1");
        adminPageSteps.verifyThatUserLoggedOff();
    }
}
