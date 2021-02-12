package com.mediamonks.pages.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class DashBoardPage extends TestBase {

	@FindBy(css = ".text-align-left")
    private WebElement welcomeMessage;

    @FindBy(css ="div[class='dropdown dropdown-login dropdown-tab']")
    private WebElement accountDropdown;

    @FindBy(css = "div.dropdown-menu.dropdown-menu-right.show > div > a:nth-child(2)")
    private WebElement logoutOption;

    @FindBy(css = "ul.menu-vertical-01 > li:nth-child(2)")
    private WebElement myProfileLink;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(css = "input[name='confirmpassword']")
    private WebElement confirmPasswordField;

    @FindBy(css = "input[name='city']")
    private WebElement cityInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = "div.animated.flash.d-none.d-md-block")
    private WebElement header;

    @FindBy(css = "h5.mt0.float-none")
    private WebElement reservationStatus;
	
    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return waitUntilVisible(welcomeMessage).getText();
    }

    public String getNameFromDropdown() throws InterruptedException {
        Thread.sleep(1000);
        return waitUntilVisible(accountDropdown).getText();
    }

    public void goToMyProfile() {
        waitUntilClickable(myProfileLink).click();
    }

    public void setNewPassword(String password) {
        waitUntilClickable(passwordField).sendKeys(password);
        scrollToElement(confirmPasswordField).sendKeys(password);
    }

    public void updateProfile() throws InterruptedException {
        scrollToElement(submitBtn).click();
        Thread.sleep(2000);
    }

    public void doLogout() {
        scrollToElement(header);
        waitUntilClickable(accountDropdown).click();
        waitUntilVisible(logoutOption).click();
    }

    public String getReservationStatus() {
        return waitUntilVisible(reservationStatus).getText();
    }
}