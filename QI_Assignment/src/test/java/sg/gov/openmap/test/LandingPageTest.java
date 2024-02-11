package sg.gov.openmap.test;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import sg.gov.openmap.configs.DriverManager;
import sg.gov.openmap.pages.BasePage;
import sg.gov.openmap.steps.LandingPageSteps;
import sg.gov.openmap.utils.Log;

public class LandingPageTest extends TestBase {

	@Test(dataProvider = "testData", testName = "Landing page Title Test", description = "To validate the expected Title when landing page loaded")

	@Description("Test Description: To validate the Title")
	public void TitleTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).verifyOnLandingPage(map.get("LandingPageTitle"));
		Log.info("Validated the Landing page title");
	}

	@Test(dataProvider = "testData", testName = "Landing page Heading Test", description = "To validate the expected Heading when landing page loaded")

	@Description("Test Description: To validate the Heading")
	public void HeadingTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).verifyLandingPageHeader(map.get("LandingPageHeader"));
		Log.info("Validated the Landing page Header");
	}

	@Test(testName = "Landing page Logo Test", description = "To validate the expected Logo displayed when landing page loaded")

	@Description("Test Description: To validate the Logo is display on Landing Page")
	public void logoTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).verifyLandingPageLogo();
		Log.info("Validated the Landing page Logo");
	}

	@Test(testName = "Top Center Menu Validation", description = "Verify all expected menu present on Landing page top center")

	@Description("Test Description: Verify all expected menu present on Landing page top center")
	public void topCenterMenuTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).retrieveAndValidateTopCenterMenu(TestData_topCenterMenus);

		Log.info("Validated the Landing page top center menu");
	}

	@Test(testName = "Footer Right Corner Links Validation", description = "Verify all expected Links are present on footer right corner")

	@Description("Test Description: Verify all expected Links are present on footer right corner")
	public void footerRightCornerLinksTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).retrieveAndValidateFooterRightCornerLinks(TestData_FooterRightCornerLinks);

		Log.info("Validated the Landing page footer right corner links");
	}

	@Test(testName = "Verify 2D to 3D and vice-versa view Changes", description = "Verify that able to switch from 2D view to 3D and vice versa view")

	@Description("Test Description: Verify that able to switch from 2D view to 3D view and vice versa")
	public void verify2Dto3DviewChangesTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).ViewChangesFrom2Dto3DAndViceVersa();

		Log.info("Successfully switched from 2D view to 3D view and vice-versa");
	}

	@Test(dataProvider = "testData", testName = "Verify Help Us Improve functionality", description = "To verify able to give rating and comment or not for Improvement")

	@Description("Test Description: Verify able to give rating and comments or not for Improvement")
	public void HelpUsImproveTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new BasePage(driver).waitForPageLoad();
		new LandingPageSteps(driver).checkLandingPageLoadedOrNot();
		new LandingPageSteps(driver).clicksOnTheHelpUsImproveBtn();
		new LandingPageSteps(driver).getAndVerifyTitleFromTheRatingPopup(map.get("RatingPopupTitle"));
		new LandingPageSteps(driver).clicksOnTheRating(Integer.parseInt(map.get("Rating")));
		new LandingPageSteps(driver).submitFewMoreDetailsToSubmit(map.get("Comment"), map.get("Email"));
		Log.info("Verified Help Us Improve functionality.");
	}

	@DataProvider(name = "testData")
	public Object[][] testData() throws Exception {
		return getTestData(this.getClass().getSimpleName());
	}
}
