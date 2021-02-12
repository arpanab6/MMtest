package com.mediamonks.pages.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class AddRoomPage extends TestBase {

	@FindBy(id = "s2id_autogen1")
    private WebElement roomTypeDropdown;

    @FindBy(css = "#select2-drop > div > input")
    private WebElement dropdownInput;

    @FindBy(className = "select2-result-label")
    private WebElement dropdownResult;

    @FindBy(id = "s2id_autogen3")
    private WebElement hotelDropdown;

    @FindBy(css = "input[name='basicprice']")
    private WebElement priceInput;

    @FindBy(xpath = "//*[@id='content']/div[2]/div[2]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[3]/a")
    private WebElement firstRoomName;

    @FindBy(css = "#content > div.panel.panel-default > div.panel-body > div > div > div.xcrud-ajax > div.xcrud-list-container > table > tbody > tr:nth-child(1) > td:nth-child(4)")
    private WebElement firstHotelName;
    
    @FindBy(id = "add")
	private WebElement addBtn;
    
    public AddRoomPage(WebDriver driver) {
        super(driver);
    }

    public void addRoom(String roomType, String hotelName, String price) {
        setPrice(price);
        selectRoom(roomType);
        selectHotel(hotelName);
        waitUntilClickable(addBtn).click();
    }

    public String getHotelName() {
        return waitUntilVisible(firstHotelName).getText();
    }

    public String getRoomNameListed() {
        return waitUntilVisible(firstRoomName).getText();
    }

    public void selectRoom(String roomType) {
        waitUntilClickable(roomTypeDropdown).click();
        waitUntilVisible(dropdownInput).sendKeys(roomType);
        waitUntilVisible(dropdownResult).click();
    }

    public void selectHotel(String hotelName) {
        waitUntilClickable(hotelDropdown).click();
        waitUntilVisible(dropdownInput).sendKeys(hotelName);
        waitUntilVisible(dropdownResult).click();
    }

    public void setPrice(String price) {
        waitUntilClickable(priceInput).sendKeys(price);
    }
}
