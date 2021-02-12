package com.mediamonks.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.PropKey;
import com.mediamonks.base.PropertyReader;
import com.mediamonks.base.TestBase;
import java.io.IOException;

public class AdminLoginPage extends TestBase {

	@FindBy(css = "input[name='email']")
    private  WebElement emailInputField;

    @FindBy(css = "input[name='password']")
    private WebElement passwrdInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "div.alert.alert-danger.loading.wow.fadeIn.animated.animated")
    private WebElement validationMessage;
    
    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAdminPage() throws IOException {
        driver.get(PropertyReader.getInstance().getProperty(PropKey.ADMIN_URL.getPropVal()));
    }

    public void doLogin(String email, String password) {
        waitUntilClickable(emailInputField).sendKeys(email);
        passwrdInputField.sendKeys(password);
        loginButton.click();
    }

    public String getValidationMessage() {
        return waitUntilVisible(validationMessage).getText();
    }
}