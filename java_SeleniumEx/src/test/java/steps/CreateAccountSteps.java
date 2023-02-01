package steps;

import app.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.CreateAccountPage;

public class CreateAccountSteps extends BaseSteps {
    CreateAccountPage createAccountPage;

    public CreateAccountSteps(WebDriver driver) {
        super(driver);
        createAccountPage = new CreateAccountPage(driver);
    }

    @Step("Open New Account page")
    public CreateAccountSteps open() {
        driver.get("http://localhost/litecart/en/create_account");
        return this;
    }

    @Step("Fill required fields")
    public CreateAccountSteps fillRequiredFields(Customer customer) {
        createAccountPage.firstName.sendKeys(customer.firstName);
        createAccountPage.lastName.sendKeys(customer.lastName);
        createAccountPage.address1.sendKeys(customer.address1);
        createAccountPage.postCode.sendKeys(customer.postCode);
        createAccountPage.city.sendKeys(customer.city);
        createAccountPage.phone.sendKeys(customer.phone);
        createAccountPage.email.sendKeys(customer.email);
        createAccountPage.password.sendKeys(customer.password);
        createAccountPage.passwordConfirm.sendKeys(customer.password);
        selectCountry(customer.country);
        selectState(customer.state);
        return this;
    }

    @Step("Save new customer")
    public void save() {
        createAccountPage.createBTN.click();
    }

    @Step("Select country {name}")
    public void selectCountry(String name) {
        createAccountPage.countryList.click();
        createAccountPage.getCountryOptionByName(name).click();
    }

    @Step("Select state {name}")
    public void selectState(String name) {
        Select stateSelector = new Select(createAccountPage.stateList);
        stateSelector.selectByVisibleText(name);
    }
}
