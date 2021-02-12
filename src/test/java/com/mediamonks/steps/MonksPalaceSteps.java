package com.mediamonks.steps;

import com.mediamonks.pages.customer.*;
import com.mediamonks.pages.supplier.AddHotelPage;
import com.mediamonks.pages.supplier.AddRoomPage;
import com.mediamonks.pages.supplier.HotelsManagementPage;
import com.mediamonks.pages.supplier.SupplierDashboardPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class MonksPalaceSteps {

    private final HomePage homePage;
    private final HotelDetailPage hotelDetailPage;
    private final SupplierDashboardPage supplierDashboardPage;
    private final HotelsManagementPage hotelsManagementPage;
    private final AddHotelPage addHotelPage;
    private final AddRoomPage addRoomPage;
    private final Logger logger;

    public MonksPalaceSteps(){
        WebDriver driver = StepHooks.driver;
        homePage = new HomePage(driver);
        hotelDetailPage = new HotelDetailPage(driver);
        supplierDashboardPage = new SupplierDashboardPage(driver);
        hotelsManagementPage = new HotelsManagementPage(driver);
        addHotelPage = new AddHotelPage(driver);
        addRoomPage = new AddRoomPage(driver);
        logger = Logger.getLogger("MonksPalaceSteps");
    }

    @Given("Supplier is on the {string} section")
    public void supplier_is_on_the_Hotels_section(String subsection) {
        logger.info("Navigating to " + subsection + " section");
        supplierDashboardPage.goToHotelsSubsection(subsection);
    }

    @When("Supplier adds a hotel with {string} name, {string} type, {string} description, {string} location and without Laundry Service option")
    public void supplier_adds_a_hotel_with_name_description_location_and_without_option(String hotelName, String type, String description, String location) {
        logger.info("Adding a new hotel with name:" + hotelName + " ,type: " + type + " ,description: " + description + " and location: " + location);
        hotelsManagementPage.navigateToAddHotelPage();
        addHotelPage.setHotelDetails(hotelName, type, description, location);
        addHotelPage.setAllFacilitiesButLaundry();
    }

    @When("Supplier adds Spanish translation {string} as name")
    public void supplier_adds_Spanish_translation_as_description(String translatedName) {
        logger.info("Adding Spanish translated hotel name and submitting hotel");
        addHotelPage.addSpanishTranslation(translatedName);
        addHotelPage.addHotel();
    }

    @Then("Supplier see {string} is listed")
    public void supplier_see_is_listed(String hotelName) {
        logger.info("Test whether the hotel is listed on the hotel management page");
        assertTrue(hotelsManagementPage.isHotelListed(hotelName));
    }

    @When("Supplier adds a {string} for {string} hotel with price {string}")
    public void supplier_adds_a_for_hotel_with_price(String roomType, String hotelName, String price) {
        logger.info("Adding a " + roomType + " for " + hotelName + " with price " + price);
        addRoomPage.addRoom(roomType, hotelName, price);
    }

    @Then("Supplier see {string} is added for {string}")
    public void supplier_see_is_added_for(String room, String hotelName) {
        logger.info("Test whether the room is listed for " + hotelName);
        assertAll(
                () -> assertEquals(room, addRoomPage.getRoomNameListed()),
                () -> assertEquals(hotelName, addRoomPage.getHotelName())
        );
    }

    @When("User changes the language to {string}")
    public void user_changes_the_language_to(String language) {
        logger.info("Changing language to " + language);
        homePage.changeLanguageTo(language);
    }

    @Then("User see {string} as name")
    public void user_see_as_name(String name) {
        logger.info("Test whether the hotel name is shown properly on heading");
        assertEquals(name, hotelDetailPage.getHotelNameTitle());
    }
}
