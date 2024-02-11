package sg.gov.openmap.steps;

import static org.testng.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import sg.gov.openmap.pages.LandingPage;

public class LandingPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private LandingPage landingPage;

	public LandingPageSteps(WebDriver driver) {
		this.landingPage = new LandingPage(driver);
	}
	
	@Step("Wait for Landing page loaded properly")
    public void checkLandingPageLoadedOrNot() throws InterruptedException {
		if(!landingPage.isLandingPageProgressBarDisplayed()) {
		landingPage.waitTillLandingPageLoadProperly();
		}
		else {
			Thread.sleep(10000);
			landingPage.waitTillLandingPageLoadProperly();
			
		}
		log.info("Landing Page laoded successfully");
	}
	
	@Step("Get Landing Page Title")
	public void verifyOnLandingPage(String actualTitle) {
		String getLandingPageTitle = landingPage.getLandingPageTitle();
		String expectedLandingPageTitle = actualTitle;
		assertEquals(getLandingPageTitle,expectedLandingPageTitle);
		log.info("Landing Page title: "+landingPage.getLandingPageTitle());
	}
	
	@Step("Verify Landing page header")
	public void verifyLandingPageHeader(String expectedHeader) {
		String actualLandingPageHeader = landingPage.getLandingPageHeading();
		String expectedLandingPageHeader = expectedHeader;
		assertEquals(actualLandingPageHeader,expectedLandingPageHeader);
		log.info("Landing Page title: "+actualLandingPageHeader);
		
	}
	
	@Step("Verify Logo is displayed on Landing Page")
	public void verifyLandingPageLogo() {
		Boolean isLogoDisplayed = landingPage.isLogoDisplayedOnLandingPage();
		assertTrue(isLogoDisplayed);
		log.info("Is Logo Displayed on landing page: "+isLogoDisplayed);
		
	}
	
	@Step("Retrieve and Validate Top Center Menu")
	public void retrieveAndValidateTopCenterMenu(List<String> expectedTopCenterMenus) {
		List<String> actualTopCenterMenus = landingPage.getTopCenterMenuNames();
		assertEquals(actualTopCenterMenus,expectedTopCenterMenus);
		log.info("Actual Top Center Menu: "+actualTopCenterMenus);
		log.info("Expected Top Center Menu: "+expectedTopCenterMenus);
		
	}
	
	@Step("Retrieve and Validate Footer right corner links")
	public void retrieveAndValidateFooterRightCornerLinks(List<String> expectedFooterRightLinks) {
		List<String> actualFooterRightLinks = landingPage.getFooterRightCornerLinks();
		assertEquals(actualFooterRightLinks,expectedFooterRightLinks);
		log.info("Actual Footer right corner links: "+actualFooterRightLinks);
		log.info("Expected Footer right corner links: "+expectedFooterRightLinks);	
	}
	
	@Step("2D to 3D and vice-versa map view Changes validations")
	public void ViewChangesFrom2Dto3DAndViceVersa() {
		landingPage.clicksOnThe2Dto3DMapSwitchBtn();
		log.info("Clicks on the 2D to 3D view");
		assertTrue(landingPage.is3DViewOfMapIsLoaded());
		log.info("Switched to 2D to 3D view of open map.");
		landingPage.clicksOnThe2Dto3DMapSwitchBtn();
		log.info("Again Clicks on the 3D to 2D view to Switch back to 2D view");
		assertFalse(landingPage.is3DViewOfMapIsLoaded());
		log.info("Switched back to 3D to 2D view of open map.");
		
	}
	
	@Step("Clicks on the Help Us Improve button")
	public void clicksOnTheHelpUsImproveBtn() {
		landingPage.clicksOnHelpUsToImproveBtn();
		log.info("Clicked on the Help Us Improve button");
	}
	
	
	
	@Step("Get and Verify Title for Rating Popup")
	public void getAndVerifyTitleFromTheRatingPopup(String expectedTitleText) {
		String actualTitleText = landingPage.getOpenImprovePopUpTitleText();
		assertEquals(actualTitleText, expectedTitleText);
		log.info("Expected Title : " + expectedTitleText + " and Actual Title : " + actualTitleText);
	}
	 
	  
	
	@Step("Submit a rating as any no between 1 to 6.")
	public void clicksOnTheRating(int ratingNoBetween1to6) {
		landingPage.clicksOnDesiredElement(ratingNoBetween1to6);
		log.info("Given Rating as : " + ratingNoBetween1to6);
	}
		
	@Step("Submit few more details for improvemnt on Tell Us More section")
	public void submitFewMoreDetailsToSubmit(String comment,String emailAddress) {
		Boolean isFormSubmitted = landingPage.fillAndCheckTellUsMoreSection(comment, emailAddress);
		assertTrue(isFormSubmitted);
		log.info("Tell Us More Section fields : What did you like most? && Email is filled as - "+comment+" "+emailAddress+" respectively");
	}
	
	
	 	 
	

	
}