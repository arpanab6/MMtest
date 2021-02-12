package com.mediamonks.pages.supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class SupplierDashboardPage extends TestBase {

	@FindBy(css = "a[href='#Hotels']")
	private WebElement hotelsSection;

	public SupplierDashboardPage(WebDriver driver) {
		super(driver);
	}

	public void goToHotelsSubsection(String subsectionName) {
		waitUntilClickable(hotelsSection).click();
		waitUntilClickable(driver.findElement(By.linkText(subsectionName))).click();
	}
}
