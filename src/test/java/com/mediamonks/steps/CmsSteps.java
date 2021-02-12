package com.mediamonks.steps;

import com.mediamonks.pages.admin.AdminDashboardPage;
import com.mediamonks.pages.admin.CMSAddPage;
import com.mediamonks.pages.admin.CMSManagementPage;
import com.mediamonks.utils.AttributeHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CmsSteps {

    private final AdminDashboardPage adminDashboardPage;
    private final CMSManagementPage cmsManagementPage;
    private final CMSAddPage cmsAddPage;
    private final AttributeHelper attributeHelper;
    private final WebDriver driver;
    private final Logger logger;

    public CmsSteps() {
        driver = StepHooks.driver;
        adminDashboardPage = new AdminDashboardPage(driver);
        cmsManagementPage = new CMSManagementPage(driver);
        cmsAddPage = new CMSAddPage(driver);
        attributeHelper = new AttributeHelper(driver);
        logger = Logger.getLogger("CMSSteps");
    }

    @Given("User navigates to CMS, {string} section")
    public void user_navigates_to_CMS_section(String subsection) {
        logger.info("Navigating to CMS and " + subsection + " subsection");
        adminDashboardPage.goToSubsection(subsection);
    }

    @When("User adds a new page with {string} page title, {string} meta keywords, {string} meta description")
    public void user_adds_a_new_page_with_page_title_meta_keywords_meta_description(String pageTitle, String metaKeywords, String metaDescription) {
        logger.info("Navigating to Add Page in CMS and creating a page with " + pageTitle + " pagetitle, " + metaKeywords + " meta keywords, and " + metaDescription + " meta description");
        cmsManagementPage.navigateToAddPage();
        cmsAddPage.setPageTitle(pageTitle);
        cmsAddPage.setMetaKeywords(metaKeywords);
        cmsAddPage.setMetaDescription(metaDescription);
        cmsAddPage.createPage();
    }

    @Then("User see a new page with {string} title is created")
    public void user_see_a_new_page_with_title_is_created(String title) {
        logger.info("Test whether the page is created with the title: " + title);
        assertEquals(title, cmsManagementPage.getLastCreatedPageTitle());
    }

    @Given("User navigates to {string} page")
    public void user_navigates_to_page(String url) {
        logger.info("Navigating to " + url);
        driver.get(url);
    }

    @Then("User see {string} as page title, {string} as meta keywords, {string} as meta description")
    public void user_see_as_page_title_as_meta_keywords_as_meta_description(String title, String metaKeywords, String metaDescription) {
        logger.info("Testing whether the new page has " + title + " as title, " + metaKeywords + " as meta keywords and " + metaDescription + " as meta description");
        assertAll(
                () -> assertEquals(title, attributeHelper.getPageTitleOfCurrentPage()),
                () -> assertEquals(metaKeywords, attributeHelper.getMetaKeywordContentOfCurrentPage()),
                () -> assertEquals(metaDescription, attributeHelper.getMetaDescriptionContentOfCurrentPage())
        );
    }
}
