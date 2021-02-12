package com.mediamonks.pages.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.mediamonks.base.TestBase;

public class AddHotelPage extends TestBase {
	@FindBy(css = "input.form-control")
	private WebElement hotelNameInputField;

	@FindBy(css = "iframe.cke_wysiwyg_frame.cke_reset")
	private WebElement dscrptionInputField;

	@FindBy(css = "body.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders")
	private WebElement textInput;

	@FindBy(css = "select[name='hoteltype']")
	private WebElement hotelTypeDropdown;

	@FindBy(id = "s2id_searching")
	private WebElement locationDropDown;

	@FindBy(xpath = "//*[@id='select2-drop']/div/input")
	private WebElement locationInputField;

	@FindBy(className = "select2-result-label")
	private WebElement locationResult;

	@FindBy(css = "a[href='#FACILITIES']")
	private WebElement facilitiesSection;

	@FindBy(css = "a[href='#TRANSLATE']")
	private WebElement translateSection;

	@FindBy(className = "icheckbox_square-grey")
	private WebElement selectAllCheckBox;

	@FindBy(xpath = "/html/body/div[3]/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[8]/label")
	private WebElement laundryServiceCheckBox;

	@FindBy(css = "input[name='translated[es][title]']")
	private WebElement spanishHotelNameInputField;

	@FindBy(css = "Rich Text Editor, translated[vi][desc]")
	private WebElement spanishDescriptionInputField;

	@FindBy(css = "iframe[title='Rich Text Editor, translated[tr][desc]'] > body.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders")
	private WebElement turkishDescriptionTextInput;

	@FindBy(id = "add")
	private WebElement addBtn;

	public AddHotelPage(WebDriver driver) {
		super(driver);
	}

	public void setHotelDetails(String hotelName, String type, String description, String location) {
		setHotelName(hotelName);
		setHotelType(type);
		setLocation(location);
		setHotelDescription(description);
	}

	public void goToFacilitiesSection() {
		facilitiesSection.click();
	}

	public void setAllFacilitiesButLaundry() {
		goToFacilitiesSection();
		waitUntilClickable(selectAllCheckBox).click();
		mouseHoverAndClick(laundryServiceCheckBox);
	}

	public void addSpanishTranslation(String name) {
		goToTranslateSection();
		scrollToElement(waitUntilClickable(spanishHotelNameInputField)).sendKeys(name);
	}

	public void goToTranslateSection() {
		translateSection.click();
	}

	public void addHotel() {
		scrollToElement(addBtn).click();
	}

	public void setHotelName(String hotelName) {
		waitUntilClickable(hotelNameInputField).sendKeys(hotelName);
	}

	public void setHotelDescription(String description) {
		driver.switchTo().frame(waitUntilVisible(dscrptionInputField));
		waitUntilClickable(textInput).click();
		textInput.sendKeys(description);
		driver.switchTo().defaultContent();
	}

	public void setHotelType(String type) {
		Select hotelType = new Select(hotelTypeDropdown);
		hotelType.selectByVisibleText(type);
	}

	public void setLocation(String location) {
		waitUntilClickable(locationDropDown).click();
		waitUntilVisible(locationInputField).sendKeys(location);
		waitUntilVisible(locationResult).click();
	}
}
