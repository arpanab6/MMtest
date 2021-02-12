package com.mediamonks.pages.customer;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(css = "input[name='username']")
    private WebElement userInputField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    @FindBy(tagName = "h3")
    private WebElement pageTitle;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void doLogin(String email, String password){
        waitUntilClickable(userInputField).sendKeys(email);
        passwordInputField.sendKeys(password);
        loginBtn.click();
    }

    public void assertLoginPageTitle() {
        assertEquals("Login",pageTitle.getText());
    }
}