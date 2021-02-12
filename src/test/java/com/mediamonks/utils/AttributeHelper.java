package com.mediamonks.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class AttributeHelper extends TestBase {
	
	@FindBy(css = "meta[name='keywords']")
	private WebElement metaKeywords;

	@FindBy(css = "meta[name='description']")
	private WebElement metaDescription;

	public AttributeHelper(WebDriver driver) {
		super(driver);
	}

	public String getMetaKeywordContentOfCurrentPage() {
		return metaKeywords.getAttribute("content");
	}

	public String getMetaDescriptionContentOfCurrentPage() {
		return metaDescription.getAttribute("content");
	}

	public String getPageTitleOfCurrentPage() {
		return driver.getTitle();
	}
}
