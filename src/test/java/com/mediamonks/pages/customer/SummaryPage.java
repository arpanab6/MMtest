package com.mediamonks.pages.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class SummaryPage extends TestBase {

	@FindBy(css = "div.content-wrapper.pt-30.pb-30.bg-white-shadow.col-12 > h3 > span")
	private WebElement title;

	@FindBy(css = "button.btn.btn-default.arrivalpay")
	private WebElement payOnArrivalOption;

	@FindBy(css = "div.success-box.reserved > div:nth-child(2) > h4")
	private WebElement reservedMsg;

	@FindBy(id = "dropdownCurrency")
	private WebElement dropdownCurrency;

	@FindBy(css = "a.dropdown-item.active.tr")
	private WebElement accountOption;

	public SummaryPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return waitUntilVisible(title).getText();
	}

	public void choosePayOnArrivalOption() {
		waitUntilClickable(payOnArrivalOption).click();
		confirmAlert();
	}

	public String getReservedMessage() {
		return waitUntilVisible(reservedMsg).getText();
	}

	public void goToAccountPage() {
		waitUntilClickable(dropdownCurrency).click();
		waitUntilClickable(accountOption).click();
	}
}
