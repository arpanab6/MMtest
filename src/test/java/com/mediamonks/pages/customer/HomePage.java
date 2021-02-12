package com.mediamonks.pages.customer;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.PropKey;
import com.mediamonks.base.PropertyReader;
import com.mediamonks.base.TestBase;
import java.io.IOException;

public class HomePage extends TestBase {

	@FindBy(css ="div[class='dropdown dropdown-login dropdown-tab']")
    private WebElement myAccountDropdown;

    @FindBy(css = "div.dropdown-menu.dropdown-menu-right.show > div > a.dropdown-item.active.tr")
    private WebElement loginOption;

    @FindBy(css = "div.dropdown-menu.dropdown-menu-right.show > div > a:nth-child(2)")
    private WebElement signUpOption;

    @FindBy(css = "a.select2-choice")
    private WebElement destinationField;

    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    private WebElement destinationInput;

    @FindBy(className = "select2-match")
    private WebElement destinationResult;

    @FindBy(id = "checkin")
    private WebElement checkInDatePicker;

    @FindBy(id = "checkout")
    private WebElement checkOutDatePicker;

    @FindBy(css = "div.form-people-thread > div > div > div > div > div > span > button:nth-child(1)")
    private WebElement increaseAdultCustomerNumberBtn;

    @FindBy(css = "div.form-people-thread > div > div > div > div > div > span > button:nth-child(2)")
    private WebElement decreaseAdultCustomerNumberBtn;

    @FindBy(css = "div.form-people-thread > div > div:nth-child(2) > div > div > div > span > button:nth-child(1)")
    private WebElement increaseChildrenCustomerNumberBtn;

    @FindBy(css = "div.form-people-thread > div > div:nth-child(2) > div > div > div > span > button:nth-child(2)")
    private WebElement decreaseChildrenCustomerNumberBtn;

    @FindBy(css = "button.btn.btn-primary.btn-block")
    private WebElement searchBtn;

    @FindBy(id = "dropdownLangauge")
    private WebElement languageDropdown;

    @FindBy(id = "es")
    private WebElement spanishLanguageOption;
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() throws IOException {
        driver.get(PropertyReader.getInstance().getProperty(PropKey.URL.getPropVal()));
    }

    public void navigateToRegisterPage() {
        waitUntilClickable(myAccountDropdown).click();
        waitUntilVisible(signUpOption).click();
    }

    public void navigateToLoginPage() {
        waitUntilClickable(myAccountDropdown).click();
        waitUntilVisible(loginOption).click();
    }

    public void makeASearch(String destination, String checkInDate, String checkOutDate, int adultNumber, int childrenNumber) {
        setDestination(destination);

        setDate(checkInDate, checkOutDate);

        setAdultCustomerNumber(adultNumber);
        setChildrenCustomerNumber(childrenNumber);

        waitUntilClickable(searchBtn).click();
    }

    public void setDate(String checkInDate, String checkOutDate) {
        waitUntilClickable(checkInDatePicker).clear();
        checkInDatePicker.sendKeys(checkInDate);
        checkInDatePicker.sendKeys(Keys.TAB);

        checkOutDatePicker.sendKeys(checkOutDate);
    }

    public void setDestination(String destination) {
        waitUntilClickable(destinationField).click();
        waitUntilVisible(destinationInput).sendKeys(destination);
        waitUntilVisible(destinationResult).click();
    }

    public void setAdultCustomerNumber(int adultNumber) {
        if(adultNumber > 2) {
            for(int i = 0; i < adultNumber-2; i++){
                waitUntilClickable(increaseAdultCustomerNumberBtn).click();
            }
        }else if(adultNumber < 2) {
            for(int j = 0; j < 2 - adultNumber; j++){
                waitUntilClickable(decreaseAdultCustomerNumberBtn).click();
            }
        }
    }

    public void setChildrenCustomerNumber(int childrenNumber) {
        if(childrenNumber > 0) {
            for(int i = 0; i < childrenNumber; i++){
                waitUntilClickable(increaseChildrenCustomerNumberBtn).click();
            }
        }
    }

    public void changeLanguageTo(String language) {
        waitUntilClickable(languageDropdown).click();
        waitUntilClickable(spanishLanguageOption).click();
    }
}