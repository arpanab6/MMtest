package com.mediamonks.pages.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.PropKey;
import com.mediamonks.base.PropertyReader;
import com.mediamonks.base.TestBase;
import java.io.IOException;

public class SupplierLoginPage extends TestBase {

	@FindBy(css = "input[name='email']")
    private  WebElement emailInputField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;
    
    @FindBy(css = "div.alert.alert-danger.loading.wow.fadeIn.animated.animated")
    private WebElement validationMessage;
	
    public SupplierLoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSupplierPage() throws IOException {
        driver.get(PropertyReader.getInstance().getProperty(PropKey.SUPPLIER_URL.getPropVal()));
    }

    public void doLogin(String email, String password) {
        waitUntilClickable(emailInputField).sendKeys(email);
        passwordInputField.sendKeys(password);
        loginBtn.click();
    }

    public String getValidationMessage() {
        return waitUntilVisible(validationMessage).getText();
    }
}
