package sg.gov.openmap.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "search_property")
	private WebElement SearchMapElement;
	
	@FindBy(css = "div#cancel-icon img")
	private WebElement SearchBoxContentCancelOrClearIcon;
	
	@FindBy(id = "route-icon")
	private WebElement RouteSetIcon;
	
	@FindBy(css="div#icon-expand-menu")
	private WebElement SearchSubmenuExpandIcon;
	
	@FindBy(css="div#icon-menu-wrapper")
	private WebElement SearchSubmenuContainer;
	
	@FindBy(css="div#icon-menu-wrapper>div.icon-details")
	private List<WebElement> SearchSubmenus;
	
	@FindBy(css="div#routeSvcOptions div.routeOptionItem")
	private List<WebElement> RouteMenus;
	
	@FindBy(css="#routeSvcDepTime")
	private WebElement RouteDepartSection;
	
	@FindBy(css="div#routeSvcDepTimeSelectWrapper input#routeSvcDepTimeSelectBox")
	private WebElement PickATimeTextBox;
	
	@FindBy(css="div#routeSvcDepTimeSelectWrapper input#routeSvcDepDateSelectBox")
	private WebElement PickADateTextBox;
	
	@FindBy(css="div#routeSvcDepTimeClear")
	private WebElement DateTimeClearBtn;
	
	@FindBy(css="div#routeSvcDepTimeSubmit")
	private WebElement DateTimeSetBtn;
	
	@FindBy(css="div.swap-destination")
	private WebElement swapDestinationBtn;
	
	@FindBy(css="div#search-input-wrapper li span[id='searchresult_name']")
	private List<WebElement> SearchInputResults;
	
	@FindBy(css="div#search-input-wrapper div li:first-child")
	private WebElement suggested1stResult;
	
	@FindBy(css="div#markerInfobox")
	private WebElement AddressInfoSectionOnMap;
	
	@FindBy(xpath="//div[@id='markerInfoContent']//span[position()=2]")
	private WebElement AddressTextOnAddressInfoSectionOnMap;
	
	public boolean isSearchBoxDisplayed() {
		return isElementDisplayed(SearchMapElement);
	}
	
	public void enterAndGetAnyValueToSearchInSearchBox(String textToEnter) {
		clearAndEnterText(SearchMapElement,textToEnter);
	}
	
	public boolean isCancelIconDisplayed() {
		return isElementDisplayed(SearchBoxContentCancelOrClearIcon);
	}
	
	public String getEnteredTextInText() {
	return readText(SearchMapElement);	
	}
	
	public boolean isRootSelectIconDisplayed() {
		return isElementDisplayed(RouteSetIcon);
	}
	
	public String getPlaceHolderText() {
		return getAttributeValue(SearchMapElement,"placeholder");
	}
	
	public ArrayList<String> getAllSearchSubMenus(){
		ArrayList<String> allActualSearchSubMenu = new ArrayList<>();
		pause(3);
		//wait.until(ExpectedConditions.visibilityOfAllElements(SearchSubmenus));
		for (WebElement searchSubMenuElement : SearchSubmenus) {
			String searchSubMenu = searchSubMenuElement.getText();
			allActualSearchSubMenu.add(searchSubMenu.trim());
		}
		return allActualSearchSubMenu;
	}
	
	public ArrayList<String> getAllSearchResults(){
		ArrayList<String> allActualSearchResults = new ArrayList<>();
		pause(3);
		
		//wait.until(ExpectedConditions.visibilityOfAllElements(SearchInputResults));
		for (WebElement SearchInputResultEle : SearchInputResults) {
			String searchResult = SearchInputResultEle.getText();
			System.out.println("Populates Search Result are "+searchResult);
			allActualSearchResults.add(searchResult.trim());
			
		}
		return allActualSearchResults;
	}
    
	public void clicksOnTheFirstResult() {
		clickWithJavaScript(suggested1stResult);
		pause(2);
	}
	
	public String getSelectedAddressBySearch() {
		return getAttributeValue(SearchMapElement,"address");
	}
	
	public Boolean isSelectedAddressPanelDisplayedOnMap() {
		return isElementDisplayed(AddressInfoSectionOnMap);
	}
    
	public String getDisplayedAddressTextOnMap() {
		return readText(AddressTextOnAddressInfoSectionOnMap);
	}
	
	public void clicksOnTheSearchExpandMenu() {
		click(SearchSubmenuExpandIcon);
		pause(1);
	}
	
	public void clicksOnThRoutIcon() {
		click(RouteSetIcon);
		pause(3);
	}
	
	public ArrayList<String> getAllRouteMenus(){
		ArrayList<String> allActualRoutesMenus = new ArrayList<>();	
		wait.until(ExpectedConditions.visibilityOfAllElements(RouteMenus));
		for (WebElement RouteSelectorEle : RouteMenus) {
			String RouteSelector = RouteSelectorEle.getText();
			allActualRoutesMenus.add(RouteSelector.trim());
			
		}
		return allActualRoutesMenus;
	}
	
	public String getDepartTimeAndDateByDefault() {
		return readText(RouteDepartSection).split(": ")[1];
	}
	
	public void expandDepartSectionToSetTimeAndDtae() {
		click(RouteDepartSection);
	}
	
	public String setAndGetDepartTime(String timeToSet) {
		String IDtoSelectOptions=getAttributeValue(PickATimeTextBox,"id");
		WebElement locatorToSelectOptions = driver.findElement(By.xpath("//ul[@aria-controls='"+IDtoSelectOptions+"']//li[contains(text(),'"+timeToSet+"')]"));
		click(PickATimeTextBox);
		wait.until(ExpectedConditions.elementToBeClickable(locatorToSelectOptions));
		String getSelectedTime = locatorToSelectOptions.getText();
		click(locatorToSelectOptions);
		wait.until(ExpectedConditions.invisibilityOf(locatorToSelectOptions));
		String setTimeInFormat = getSelectedTime.replaceAll("^Time selected: (\\d{2}:\\d{2}) ([ap]\\.m\\.)$", "$1 $2");
        String getSettedTimeInFormatToValidate = setTimeInFormat.replaceAll("(?i)a\\.m\\.", "AM").replaceAll("(?i)p\\.m\\.", "PM");
        return getSettedTimeInFormatToValidate;
	}
	
	public String setAndGetDepartDate() {
		String IDtoSelectOptions=getAttributeValue(PickADateTextBox,"id");
		String getDesiredDateToSet = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));
		WebElement locatorToSelectDesiredDate = driver.findElement(By.xpath("//table[@aria-controls='"+IDtoSelectOptions+"']//tr//td//div[contains(@aria-label,'"+getDesiredDateToSet+"')]"));
		click(PickADateTextBox);
		wait.until(ExpectedConditions.elementToBeClickable(locatorToSelectDesiredDate));
		String getSelectedDate = getAttributeValue(locatorToSelectDesiredDate,"aria-label");
		click(locatorToSelectDesiredDate);
		wait.until(ExpectedConditions.invisibilityOf(locatorToSelectDesiredDate));
		return LocalDate.parse(getSelectedDate, DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.ENGLISH))
                .format(DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.ENGLISH));
	}
	
	public void clicksOnSetBtn() {
		click(DateTimeSetBtn);
		pause(2);
	}
}

