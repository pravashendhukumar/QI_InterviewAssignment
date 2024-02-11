package sg.gov.openmap.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import sg.gov.openmap.configs.GlobalVariables;

public class BasePage implements GlobalVariables{

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT), this);
	}

	protected void click(WebElement element) {
		waitForElement(element).click();
	}

	protected void clearAndEnterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	protected String readText(WebElement element) {
		return waitForElement(element).getText();
	}

	protected String getAttributeValue(WebElement element, String attributeName) {
		return waitForElement(element).getAttribute(attributeName);
	}

	protected WebElement waitForElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected boolean waitForInvisibilityElement(WebElement element) {
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}


	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	protected void pause(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		pause(2);
	}
	
	protected void checkWhenUnchecked(WebElement element) {
		if(!isElementSelected(element))
			waitForElement(element).click();
	}
	
	protected boolean isElementSelected(WebElement element) {
		try {			
			return waitForElement(element).isSelected();
		} catch (Exception e) {
			return false;
		}
	}
	
	protected boolean isElementDisplayed(WebElement element) {
		try {			
			return waitForElement(element).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}


	protected String selectelementText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		return select.getFirstSelectedOption().getText();
	}

	protected String selectelementIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		return select.getFirstSelectedOption().getText();
	}
	
	public void waitForPageLoad() {
		pause(1);
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}
	
	public String switchToNewWindow() {
		String window = driver.getWindowHandle();
		for(String handle : driver.getWindowHandles()) {
			if(!window.equals(handle)) {
				driver.switchTo().window(handle);
			}
		}
		return window;
	}
	
	public void closeAndSwitchToParentWindow(String handle) {
		driver.close();
		driver.switchTo().window(handle);
	}
	
	protected void clickAction(WebElement element) {
		new Actions(driver).moveToElement(element).click().build().perform();
	}
	
	protected String getOrderNumber(WebElement element) {
		return element.getText().trim().replaceAll("[^0-9]", "");
	}
}
