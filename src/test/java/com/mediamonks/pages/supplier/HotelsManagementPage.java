package com.mediamonks.pages.supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class HotelsManagementPage extends TestBase {

	@FindBy(css = "form.add_button > button")
	private WebElement addHotelBtn;

	public HotelsManagementPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToAddHotelPage() {
		waitUntilClickable(addHotelBtn).click();
	}

	public boolean isHotelListed(String hotelName) {
		String replacedName = hotelName.replace(" ", "-");
		return driver
				.findElement(By.cssSelector(
						"a[href='https://www.phptravels.net/supplier/hotels/manage/" + replacedName + "']"))
				.isDisplayed();
	}
}
