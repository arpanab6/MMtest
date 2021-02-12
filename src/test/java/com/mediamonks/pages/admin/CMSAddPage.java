package com.mediamonks.pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class CMSAddPage extends TestBase {

	@FindBy(css = "input[name='pagetitle']")
	private WebElement pageTitleInputField;

	@FindBy(css = "input[name='keywords']")
	private WebElement keywordsInputField;

	@FindBy(css = "input[name='pagedesc']")
	private WebElement descriptionInputField;

	@FindBy(css = "button.btn.btn-primary.btn-block.btn-lg")
	private WebElement submitButton;

	public CMSAddPage(WebDriver driver) {
		super(driver);
	}

	public void setPageTitle(String pageTitle) {
		waitUntilClickable(pageTitleInputField).sendKeys(pageTitle);
	}

	public void setMetaKeywords(String keywords) {
		scrollToElement(waitUntilClickable(keywordsInputField)).sendKeys(keywords);
	}

	public void setMetaDescription(String description) {
		scrollToElement(waitUntilClickable(descriptionInputField)).sendKeys(description);
	}

	public void createPage() {
		scrollToElement(waitUntilClickable(submitButton)).click();
	}
}
