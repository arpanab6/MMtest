package com.mediamonks.steps;

import com.mediamonks.pages.admin.AdminLoginPage;
import com.mediamonks.pages.customer.DashBoardPage;
import com.mediamonks.pages.customer.HomePage;
import com.mediamonks.pages.customer.LoginPage;
import com.mediamonks.pages.customer.RegisterPage;
import com.mediamonks.pages.supplier.SupplierLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final LoginPage loginPage;
    private final DashBoardPage dashBoardPage;
    private final HomePage homePage;
    private final RegisterPage registerPage;
    private final SupplierLoginPage supplierLoginPage;
    private final AdminLoginPage adminLoginPage;
    private final Logger logger;

    public LoginSteps(){
        WebDriver driver = StepHooks.driver;
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(driver);
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        supplierLoginPage = new SupplierLoginPage(driver);
        adminLoginPage = new AdminLoginPage(driver);
        logger = Logger.getLogger("LoginSteps");
    }

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() throws IOException {
        logger.info("Navigating to login page and expecting page title to be 'Login'");
        homePage.navigateToHomePage();
        homePage.navigateToLoginPage();
        loginPage.assertLoginPageTitle();
    }

    @When("User fills the login form with {string} email and {string} password")
    public void user_fills_the_login_form_with_email_and_password(String email, String password) {
        logger.info("Signing in as a customer with credentials email: " + email + " , and password: " + password);
        loginPage.doLogin(email, password);
    }

    @Then("User see {string} on the dashboard")
    public void user_see_on_the_dashboard(String fullName) {
        logger.info("Validating customer's name exists on the welcome message and the account dropdown");

        assertAll(
                () -> assertTrue(dashBoardPage.getWelcomeMessage().contains(fullName)),
                () -> assertTrue(fullName.toUpperCase().contains(dashBoardPage.getNameFromDropdown()))
        );

    }

    @Given("User goes to their profile page")
    public void user_goes_to_their_profile_page() {
        logger.info("Accessing to the profile via 'My Profile' section");
        dashBoardPage.goToMyProfile();
    }

    @When("User changes password with {string} and logout")
    public void user_changes_city_with_and_logout(String newPassword) throws InterruptedException {
        logger.info("Updating profile by changing password and logout in order to test new password is saved and the profile is updated");
        dashBoardPage.setNewPassword(newPassword);
        dashBoardPage.updateProfile();
        dashBoardPage.doLogout();
    }

    @Given("User navigates to the admin page")
    public void user_navigates_to_the_admin_page() throws IOException {
        logger.info("Navigating to the Admin page");
        adminLoginPage.navigateToAdminPage();
    }

    @When("User fill the admin login form with {string} email and {string} password")
    public void user_fill_the_admin_login_form_with_email_and_password(String email, String password) {
        logger.info("Filling the login form on the admin page with credentials email: " + email + " , and password: " + password);
        adminLoginPage.doLogin(email,password);
    }

    @Then("User see {string} message on the admin page")
    public void user_see_message_on_the_admin_page(String message) {
        logger.info("Testing that the login was unsuccessful and validation message appeared");
        assertEquals(message, adminLoginPage.getValidationMessage());
    }

    @Then("User see {string} message on the supplier page")
    public void user_see_message_on_the_supplier_page(String message) {
        logger.info("Testing that the login was unsuccessful and validation message appeared");
        assertEquals(message, supplierLoginPage.getValidationMessage());
    }

    @Given("User navigates to the supplier page")
    public void user_navigates_to_the_supplier_page() throws IOException {
        logger.info("Navigating to the Supplier page");
        supplierLoginPage.navigateToSupplierPage();
    }

    @When("User fill the supplier login form with {string} email and {string} password")
    public void user_fill_the_supplier_login_form_with_email_and_password(String email, String password) {
        logger.info("Signing in on the supplier page with credentials email: " + email + " , and password: " + password);
        supplierLoginPage.doLogin(email,password);
    }

    @When("User is registered with {string} email, {string} password, {string} phone, {string} full name")
    public void user_is_registered_with_email_password_phone_full_name(String email, String password, String phone, String fullName) throws IOException {
        logger.info("Opening a new customer account with valid credentials");
        homePage.navigateToHomePage();
        homePage.navigateToRegisterPage();
        registerPage.doRegister(fullName,phone,email,password);
    }
}
