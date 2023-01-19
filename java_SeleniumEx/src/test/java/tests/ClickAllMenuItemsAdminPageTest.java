package tests;

/*   Task06   */

import org.junit.jupiter.api.Test;
import steps.AdminPageSteps;

public class ClickAllMenuItemsAdminPageTest extends BaseTest {

    @Test
    public void clickAllMenuItemsTest() {
        AdminPageSteps adminPageSteps = new AdminPageSteps(driver);

        int level1Count = adminPageSteps.openAndLogin().getMenuItemsCount();

        for (int i = 0; i < level1Count; i++) {
            adminPageSteps
                    .clickMenuItemByIndex(i+1);
            adminPageSteps.verifyThatContentHeaderFound();

            int innerICount = adminPageSteps.getSubMenuItemsCount(i+1);
            if (innerICount > 0) {
                for (int j = 0; j < innerICount; j++) {
                    adminPageSteps
                            .clickSubMenuItemByIndex(i+1, j);
                    adminPageSteps.verifyThatContentHeaderFound();
                }
            }
        }
    }
}
