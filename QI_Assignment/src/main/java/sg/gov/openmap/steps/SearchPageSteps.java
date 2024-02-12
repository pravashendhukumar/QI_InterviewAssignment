package sg.gov.openmap.steps;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import sg.gov.openmap.pages.SearchPage;
import sg.gov.openmap.utils.DateUtil;

public class SearchPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private SearchPage searchPage;

	public SearchPageSteps(WebDriver driver) {
		this.searchPage = new SearchPage(driver);
	}
	
	@Step("Check the presence of Search Box")
	public void verifySearchBoxIsPresent() {
		assertTrue(searchPage.isSearchBoxDisplayed());
		log.info("Search Box presented on landing page");
	}
	
	@Step("Get and Verify placeholder text of Search Box")
	public void getAndVerifySearchBoxPlaceHolderIsPresent(String expectedPlaceholderText) {
		String actualPlaceHolderText = searchPage.getPlaceHolderText().trim();
		assertEquals(actualPlaceHolderText,expectedPlaceholderText);
		log.info("Actual && Expected Placeholder Text as : "+actualPlaceHolderText+ " && "+ expectedPlaceholderText+" respectively");
	}
	
	@Step("Check the presence of Route Search icon in Search Box")
	public void verifyRouteSearchIconPresence() {
		assertTrue(searchPage.isRootSelectIconDisplayed());
		log.info("Root Select Icons presented in Search Box");
	}
	
	@Step("Check the presence and functionlaity of Cancel Icon in Search Box after entering anything into the Searchbox")
	public void verifyPresenceOfCancelBtn(String inputTextForClearValidation) {
		searchPage.enterAndGetAnyValueToSearchInSearchBox(inputTextForClearValidation);
		log.info("Entered "+inputTextForClearValidation+" in the Search box");
		assertTrue(searchPage.isCancelIconDisplayed());
		log.info("Cancel button/Icons presented in Search Box after entered any text");
	}
	
	@Step("Check the search suggesstion")
	public void verifySearchSuggesstions(String inputTextForValidatingSearch) {
		searchPage.enterAndGetAnyValueToSearchInSearchBox(inputTextForValidatingSearch);
		log.info("Entered "+inputTextForValidatingSearch+" in the Search box");
		boolean flag=false;
		ArrayList<String> searchResults = searchPage.getAllSearchResults();
		for(String result : searchResults) {
			if(result.toUpperCase().contains(inputTextForValidatingSearch.toUpperCase())) {
				flag=true;
			}
			else {
				flag=false;
			}
		}
		assertTrue(flag);
		log.info("Populated results : "+searchResults+ " having matched with entered value- "+ inputTextForValidatingSearch );
	}
	
	@Step("Check Search functionality")
	public void verifySearch(String inputToSearch) {
		searchPage.enterAndGetAnyValueToSearchInSearchBox(inputToSearch);
		log.info("Entered "+inputToSearch+" in the Search box");
		searchPage.clicksOnTheFirstResult();
		log.info("Clicks on the first suggessted result");
		String actualSelectedAddressBySearch = searchPage.getSelectedAddressBySearch();
		String ExpectedSelectedAddressBySearch = searchPage.getDisplayedAddressTextOnMap();
		assertTrue(searchPage.isSelectedAddressPanelDisplayedOnMap());
		log.info("As per selection "+ExpectedSelectedAddressBySearch+ " displayed on the map");
		assertEquals(actualSelectedAddressBySearch.toUpperCase(),ExpectedSelectedAddressBySearch.toUpperCase());
		log.info("Actual Searched Address : "+actualSelectedAddressBySearch+ " && Expected displayed address :"+ ExpectedSelectedAddressBySearch+ " matched.");	
	}
	
	@Step("Get and Verify all Search Menu")
	public void getAndVerifyAllSearchMenu(List<String> expectedSearchSubMenus) {
		searchPage.clicksOnTheSearchExpandMenu();
		log.info("Clicks on the Search Expand Menu");
		ArrayList<String> actualSearchSubMenus = searchPage.getAllSearchSubMenus();
		assertEquals(actualSearchSubMenus,expectedSearchSubMenus);
		log.info("Actual Search Submenu: "+actualSearchSubMenus+ " && Expected Search Submenu :"+ expectedSearchSubMenus+ " matched.");	
	}
	
	@Step("Get and Verify all Route Menu")
	public void getAndVerifyAllRouteMenu(List<String> expectedRouteSubMenus) {
		searchPage.clicksOnThRoutIcon();
		log.info("Clicks on the Route Icons");
		ArrayList<String> actualSearchSubMenus = searchPage.getAllRouteMenus();
		assertEquals(actualSearchSubMenus,expectedRouteSubMenus);
		log.info("Actual Search Submenu: "+actualSearchSubMenus+ " && Expected Search Submenu :"+ expectedRouteSubMenus+ " matched.");	
		
	}
	
	@Step("Get and Verify Deafult Departure date and Time")
	public void getAndVerifyDefaultDepartureDateTime() {
		searchPage.clicksOnThRoutIcon();
		log.info("Clicks on the Route Icons");
		String getDefaultDepartTimeDate = searchPage.getDepartTimeAndDateByDefault();
		assertTrue(DateUtil.isTimeIsInRangeFor5Minute(getDefaultDepartTimeDate));
		log.info("By default Departure date and Time is set to current local time as : "+getDefaultDepartTimeDate);
	}
	

	@Step("Set and Verify Departure Date and Time")
	public void setAndVerifyDepartureDateAndTime(String timeToSet) {
		searchPage.clicksOnThRoutIcon();
		log.info("Clicks on the Route Icons");
		searchPage.expandDepartSectionToSetTimeAndDtae();
		log.info("Depart section expanded to set time and Date");
		String getSettedTime =searchPage.setAndGetDepartTime(timeToSet);
		log.info("Depart time set to: "+ getSettedTime);
		String getSettedDate =searchPage.setAndGetDepartDate();
		log.info("Depart Date set to: "+ getSettedDate);
		searchPage.clicksOnSetBtn();
		log.info("Clicks on the Date Time Set Button.");
        String DateTimeAfterSetted=searchPage.getDepartTimeAndDateByDefault();
        assertTrue(DateTimeAfterSetted.contains(getSettedTime));
        assertTrue(DateTimeAfterSetted.contains(getSettedDate));
        log.info("Departure Date adn Time setted to : "+DateTimeAfterSetted);
	}
	
}