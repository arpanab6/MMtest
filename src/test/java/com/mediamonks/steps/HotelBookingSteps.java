package com.mediamonks.steps;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.mediamonks.pages.customer.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class HotelBookingSteps {

    private final HomePage homePage;
    private final DashBoardPage dashBoardPage;
    private final SearchPage searchPage;
    private final HotelDetailPage hotelDetailPage;
    private final SummaryPage summaryPage;
    private final Logger logger;

    public HotelBookingSteps(){
        WebDriver driver = StepHooks.driver;
        homePage = new HomePage(driver);
        dashBoardPage = new DashBoardPage(driver);
        searchPage = new SearchPage(driver);
        hotelDetailPage = new HotelDetailPage(driver);
        summaryPage = new SummaryPage(driver);
        logger = Logger.getLogger("HotelBookingSteps");
    }

    @Given("User navigates to HomePage")
    public void user_navigates_to_homepage() throws IOException {
        logger.info("Navigating to homepage");
        homePage.navigateToHomePage();
    }

    @When("User makes a search for a reservation to {string} from {string} to {string} for {int} adult and {int} children")
    public void user_makes_a_search_for_a_reservation_to_from_to_for_adult_and_children(String location, String checkInDate, String checkOutDate, int adultNumber, int childrenNumber) {
        logger.info("Making a hotel search with some specific preferences");
        homePage.makeASearch(location, checkInDate, checkOutDate, adultNumber, childrenNumber);
    }

    @Then("User see hotels are listed in {string}")
    public void user_see_hotels_are_listed_in(String location) {
        logger.info("Asserting the location is: " + location + " on listed hotels page and existence of listed hotels");

        assertAll(
                () -> assertTrue(searchPage.getTitle().contains(location)),
                () -> assertTrue(searchPage.getNumberOfListedHotels() > 0)
        );
    }

    @When("User filters with only {int} star hotels")
    public void user_filters_with_only_star_hotels(Integer stars) {
        logger.info("Using the star filter on the result page");
        searchPage.makeStarFilteredSearch(stars);
        assertEquals(stars, searchPage.getHotelRating());
    }

    @Then("User see {string} hotel is listed")
    public void user_see_hotel_is_listed(String hotelName) {
        logger.info("Assert the hotel name " + hotelName + " is listed");
        assertEquals(hotelName, searchPage.getHotelName());
    }

    @Given("User goes to hotel detail page")
    public void user_goes_to_hotel_detail_page() {
        logger.info("Navigating to hotel detail page");
        searchPage.goToHotelDetail();
    }

    @When("User selects a room")
    public void user_selects_a_room() {
        logger.info("Selecting a room on the hotel detail page");
        hotelDetailPage.bookARoom();
    }

    @When("confirms the booking")
    public void confirms_the_booking() {
        logger.info("Confirming the booking");
        hotelDetailPage.confirmBooking();
    }

    @Then("User see the {string}")
    public void user_see_the_booking_details(String details) {
        logger.info("Asserting the summary page title and expecting to see " + details);
        assertEquals(details, summaryPage.getTitle());
    }

    @When("User chooses to make purchase with pay on arrival option")
    public void user_chooses_to_make_purchase_with_pay_on_arrival_option() {
        logger.info("Choosing and accepting Pay on Arrival Option");
        summaryPage.choosePayOnArrivalOption();
    }

    @Then("User see {string} message")
    public void user_see_message(String message) {
        logger.info("Asserting reserved message and expecting to see: " + message);
        assertEquals(message, summaryPage.getReservedMessage());
    }

    @Then("User can see the booking {string} on their profile")
    public void user_can_see_the_booking_on_their_profile(String reservationStatus) {
        logger.info("Asserting the reservation status to be " + reservationStatus + " on the profile");
        summaryPage.goToAccountPage();
        assertEquals(reservationStatus, dashBoardPage.getReservationStatus());
    }
}