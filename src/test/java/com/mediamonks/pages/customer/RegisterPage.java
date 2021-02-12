package com.mediamonks.pages.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class RegisterPage extends TestBase {

	@FindBy(css = "input[name='firstname']")
    private WebElement firstNameInputField;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameInputField;

    @FindBy(css = "input[name='phone']")
    private WebElement phoneInputField;

    @FindBy(css = "input[name='confirmpassword']")
    private WebElement confirmPasswordInputField;
    
    @FindBy(css = "input[name='email']")
    private WebElement emailInputField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;
    
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void doRegister(String fullName, String phoneNumber, String email, String password) {
        String[] splitName = fullName.split(" ");
        waitUntilVisible(firstNameInputField).sendKeys(splitName[0]);
        lastNameInputField.sendKeys(splitName[1]);
        phoneInputField.sendKeys(phoneNumber);
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        confirmPasswordInputField.sendKeys(password);
        submitBtn.click();
    }
}
