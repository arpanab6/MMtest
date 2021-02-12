package com.mediamonks.pages.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mediamonks.base.TestBase;

public class SearchPage extends TestBase {

	 @FindBy(css = "div.product-long-item")
	    private List<WebElement> hotelList;

	    @FindBy(css = "div.mb-5.custom-ratting")
	    private List<WebElement> starFilter;

	    @FindBy(css = "h3.heading-title")
	    private WebElement headingTitle;

	    @FindBy(id = "searchform")
	    private WebElement searchform;

	    @FindBy(css = "div.o2 > h5 > a")
	    private WebElement firstHotel;

	    @FindBy(css = "div.rating-icons > span.rating-icon.fas.fa-star.rating-rated")
	    private List<WebElement> hotelRating;
	    
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfListedHotels() {
        return hotelList.size();
    }

    public String getTitle() {
        return waitUntilVisible(headingTitle).getText();
    }

    public void makeStarFilteredSearch(int star) {
        waitUntilClickable(starFilter.get(star - 1)).click();
        scrollToElement(searchform).click();
    }

    public String getHotelName() {
        return waitUntilVisible(firstHotel).getText();
    }

    public int getHotelRating() {
        waitUntilVisible(hotelRating.get(0));
        return hotelRating.size();
    }

    public void goToHotelDetail(){
    	firstHotel.click();
    }
}
