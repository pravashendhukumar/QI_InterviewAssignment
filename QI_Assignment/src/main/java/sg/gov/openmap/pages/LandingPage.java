package sg.gov.openmap.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='top-wrapper']//span[normalize-space(text())]")
	private WebElement landingPageTitle;

	@FindBy(id = "onemap3Btn")
	private WebElement landingPageOpenMapLogo;

	@FindBy(css = "div#essentialSvcsWrapper>div:not(.inactive) span")
	private List<WebElement> landingPageTopCenterMenus;

	@FindBy(xpath = "//span[@class='bar']//span[@class='progress' and contains(@style,'animation-play-state: paused')]")
	private WebElement pageLoadProgress;

	@FindBy(css = "div.footerLinks a")
	private List<WebElement> landingPageFooterRightCornerLinks;

	@FindBy(id = "btn2D3D")
	private WebElement MapSwitch2DTo3DBtn;

	@FindBy(xpath = "//div[@title='Click and drag to rotate the camera']")
	private WebElement CompassCamera3DView;

	@FindBy(xpath = "//p[normalize-sapce()='Help us improve']//parent::div")
	private WebElement RatingBtn;

	@FindBy(xpath = "//wog-sentiments")
	private WebElement HelpUsToImporveShadowDOMSource;
	
	SearchContext expandShadowRoot = HelpUsToImporveShadowDOMSource.getShadowRoot();

	public void clicksOnHelpUsToImproveBtn() {
		expandShadowRoot.findElement(By.cssSelector("div.wog--tabbed-button.wog--tabbed-button-side-left")).click();
		pause(5);
	}

	
	
	public String getOpenImprovePopUpTitleText() {
		return expandShadowRoot.findElement(By.cssSelector("#rating-heading")).getText().trim();
	}
	 		
	public void clicksOnDesiredElement(int desiredNo) {
		for (WebElement ratingNoBtn : expandShadowRoot.findElements(By.cssSelector("div.wog--flex.wog--justify-around>button"))) {
			if (Integer.parseInt(ratingNoBtn.getText()) == desiredNo) {
				ratingNoBtn.click();
				pause(5);
				break;
			}

		}
	}
		
	public Boolean fillAndCheckTellUsMoreSection(String WhatDoYouLikeInput,String email) {
		expandShadowRoot.findElement(By.cssSelector("wog-textarea-field textarea")).sendKeys(WhatDoYouLikeInput);
		expandShadowRoot.findElement(By.cssSelector("wog-email-field.hydrated input")).sendKeys(email);
		expandShadowRoot.findElement(By.cssSelector("button#wogSubmitForm")).click();
		pause(10);
		Boolean isFormSubmitted = expandShadowRoot.findElement(By.cssSelector("div.wog--tabbed-button.wog--tabbed-button-side-left")).isDisplayed();
	    return isFormSubmitted;
	}
			 
	 

	public boolean isLogoDisplayedOnLandingPage() {
		return isElementDisplayed(landingPageOpenMapLogo);
	}

	public Boolean isLandingPageProgressBarDisplayed() {
		return isElementDisplayed(pageLoadProgress);
	}

	public void waitTillLandingPageLoadProperly() {
		WebDriverWait wait = new WebDriverWait(driver, ELEMENT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(pageLoadProgress));
	}

	public String getLandingPageTitle() {
		return driver.getTitle();
	}

	public String getLandingPageHeading() {
		return landingPageTitle.getText().trim();
	}

	public ArrayList<String> getTopCenterMenuNames() {
		ArrayList<String> allActualTopCenterMenu = new ArrayList<>();
		wait.until(ExpectedConditions.visibilityOfAllElements(landingPageTopCenterMenus));
		for (WebElement topCenterMenuElement : landingPageTopCenterMenus) {
			String topCenterMenu = topCenterMenuElement.getText();
			allActualTopCenterMenu.add(topCenterMenu.trim());
		}
		return allActualTopCenterMenu;
	}

	public ArrayList<String> getFooterRightCornerLinks() {
		ArrayList<String> allActualFooterRightCornerLinks = new ArrayList<>();
		wait.until(ExpectedConditions.visibilityOfAllElements(landingPageFooterRightCornerLinks));
		for (WebElement footerRightCornerLinkElement : landingPageFooterRightCornerLinks) {
			String footerRightCornerLink = footerRightCornerLinkElement.getText();
			allActualFooterRightCornerLinks.add(footerRightCornerLink.trim());
		}
		return allActualFooterRightCornerLinks;
	}

	public void clicksOnThe2Dto3DMapSwitchBtn() {
		if (MapSwitch2DTo3DBtn.isDisplayed()) {
			MapSwitch2DTo3DBtn.click();
		}
		waitTillLandingPageLoadProperly();
	}

	public Boolean is3DViewOfMapIsLoaded() {
		String getAttributeToCheck2Dto3DviewChanged = getAttributeValue(MapSwitch2DTo3DBtn, "class");
		Boolean isCompassCameraVisible = isElementDisplayed(CompassCamera3DView);
		if (getAttributeToCheck2Dto3DviewChanged.contains("active") && isCompassCameraVisible) {
			return true;
		}
		return false;
	}

}
