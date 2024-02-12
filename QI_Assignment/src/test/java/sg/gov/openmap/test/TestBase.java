package sg.gov.openmap.test;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import sg.gov.openmap.configs.DriverManager;
import sg.gov.openmap.configs.GlobalVariables;
import sg.gov.openmap.listeners.TestListener;
import sg.gov.openmap.reports.ReportManager;
import sg.gov.openmap.utils.Directory;
import sg.gov.openmap.utils.ExcelUtil;

@Listeners({TestListener.class})
public class TestBase implements GlobalVariables{
	protected WebDriverWait wait;

	@BeforeSuite
	public void configurations(ITestContext context) {
		Directory dir = new Directory();
		dir.clearFolder(SCREENSHOT_FOLDER);
		dir.clearFolder(ALLURE_RESULTS);
	}
    
	@BeforeMethod (alwaysRun = true)
	public void setup(Method method) {
		WebDriver driver = null;
		String browserName=BROWSER_NAME;
		switch (browserName.toLowerCase()) {
        case "chrome":
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
		ChromeOptions options = new ChromeOptions();  
		options.setExperimentalOption("prefs", chromePrefs);  
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--headless=new");
		options.addArguments("--start-maximized");
		options.setAcceptInsecureCerts(true);
		driver = new  ChromeDriver(options);
		break;
        case "firefox":        	
        	driver = new FirefoxDriver();
            break;
        case "edge": driver=new EdgeDriver(); 
            break;
        
		}
		driver.get(BASE_URL);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		DriverManager.getInstance().setDriver(driver);
		ReportManager.startTest(method.getName());
	}

	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		DriverManager.getInstance().getDriver().quit();
	}

	public Object[][] getTestData(String className) throws Exception{		
		ExcelUtil excel = new ExcelUtil();
		excel.setExcelFile(DATA_FOLDER + WORKBOOK);		
		int testRow = excel.getNumberOfRows(className);
		Object[][] data = new Object[testRow-1][1];
		for(int i=1; i<testRow; i++) {
			data[i-1][0] = excel.getData(className, i);
		}
		return data;
	}

	protected void pause(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
