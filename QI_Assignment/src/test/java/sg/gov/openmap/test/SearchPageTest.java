package sg.gov.openmap.test;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import sg.gov.openmap.configs.DriverManager;
import sg.gov.openmap.pages.BasePage;
import sg.gov.openmap.steps.LandingPageSteps;
import sg.gov.openmap.steps.SearchPageSteps;
import sg.gov.openmap.utils.Log;

public class SearchPageTest extends TestBase {

	
	  @Test(testName = "Verify Presence of Search Box", description =
	  "To validate the presence of Search Box on the Landing page")
	  
	  @Description("Test Description: To validate the presence of Search Box on the Landing page"
	  ) public void SearchBoxPresenceTest() throws Exception { WebDriver driver =
	  DriverManager.getInstance().getDriver(); new
	  BasePage(driver).waitForPageLoad(); new
	  LandingPageSteps(driver).checkLandingPageLoadedOrNot(); new
	  SearchPageSteps(driver).verifySearchBoxIsPresent();
	  Log.info("Validated the presence of Search Box on Landing Page"); }
	  
	  @Test(dataProvider = "testData", testName = "Verify UI of Search Box",
	  description =
	  "To validate the presence of expected UI element in Search Box")
	  
	  @Description("Test Description: To validate the presence of expected UI element in Search Box"
	  ) public void SearchBoxUITest(Map<String, String> map) throws Exception {
	  WebDriver driver = DriverManager.getInstance().getDriver(); new
	  BasePage(driver).waitForPageLoad(); new
	  LandingPageSteps(driver).checkLandingPageLoadedOrNot(); new
	  SearchPageSteps(driver).getAndVerifySearchBoxPlaceHolderIsPresent(map.get(
	  "SearchBoxPlaceHolderText")); new
	  SearchPageSteps(driver).verifyRouteSearchIconPresence();
	  Log.info("Validated the UI elements of Search Box"); }
	 
	
	@Test(dataProvider = "testData",testName = "Verify Cancel button", description = "Verify the functionality of Cancel Button working as expected")

	@Description("Test Description: Verify the functionality of Cancel Button working as expected")
	public void CancelBtnTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).verifyPresenceOfCancelBtn(map.get("InputToTestCancelBtn"));	
		Log.info("Cancel Button validated");
	}
	
	@Test(dataProvider = "testData",testName = "Verify Search suggestion", description = "Verify Search suggestion matched with entered value")

	@Description("Test Description: Verify Search suggestion matched with entered value")
	public void SearchTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).verifySearchSuggesstions(map.get("InputForTestSearch"));	
		Log.info("Verified Search suggesstion respective to entered value");
	}
	
	
	  @Test(dataProvider = "testData",testName = "Verify Search functions",
	  description = "Verify Search functionality")
	  
	  @Description("Test Description: Verify Search functionality") public void
	  SearchFunctionalityTest(Map<String, String> map) throws Exception { WebDriver
	  driver = DriverManager.getInstance().getDriver(); new
	  BasePage(driver).waitForPageLoad(); new
	  LandingPageSteps(driver).checkLandingPageLoadedOrNot(); new
	  SearchPageSteps(driver).verifySearch(map.get("InputForTestSearch"));
	  Log.info("Verified Search functionality respective to entered value"); }
	 
	
	@Test(testName = "Verify Expected Search SubMenus", description = "Verify All expected Search Submenu are present")

	@Description("Test Description: Verify All expected Search Submenu are present")
	public void SearchSubmenuPresenceTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).getAndVerifyAllSearchMenu(TestData_SearchSubMenus);
		Log.info("Verified Search submenus as expected");
	}
	
	@Test(testName = "Verify Expected Route Menus", description = "Verify All expected Route menus are present")

	@Description("Test Description: Verify All expected Route menus are present")
	public void RoutemenuPresenceTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).getAndVerifyAllRouteMenu(TestData_SearchRouteMenus);
		Log.info("Verified Route Menus as expected");
	}
	
	@Test(testName = "Verify default departure time and Date", description = "Verify default setted time and Date in Departure section")

	@Description("Test Description: Verify By default current timestamp present in Departure section.")
	public void verifyDefaultSettedDateAndTime() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).getAndVerifyDefaultDepartureDateTime();
		Log.info("Verified default departure time and date");
	}
	
	@Test(dataProvider="testData", testName = "Verify setting of Departure Date and Time", description = "Verify user should able to set Departure Date and Time")

	@Description("Test Description: Verify By default current timestamp present in Departure section.")
	public void verifyUserShouldAbleToSetDepDateAndTime(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new SearchPageSteps(driver).setAndVerifyDepartureDateAndTime(map.get("DepartureTime"));
		Log.info("Verified setting of Departure Date and Time");
	}

	@DataProvider(name = "testData")
	public Object[][] testData() throws Exception {
		return getTestData(this.getClass().getSimpleName());
	}
}
