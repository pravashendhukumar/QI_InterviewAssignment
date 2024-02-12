package sg.gov.openmap.configs;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public interface GlobalVariables {
    String BROWSER_NAME = System.getenv("browser") != null ? System.getenv("browser").trim().toLowerCase(): Config.getProperty("browser").toLowerCase();	//"Sanity";
	String BASE_URL = Config.getProperty("url");	//"https://www.onemap.gov.sg/";
	String NOT_APPLICABLE = "NA";
	
	String WORKBOOK = Config.getProperty("workbook");	//"TestData.xlsx";
	String SCENARIO_SHEET_NAME = System.getenv("sheet") != null ? System.getenv("sheet").trim(): Config.getProperty("sheet");	//"Sanity";
	int RUN_MODE_COLUMN = 3;
	int TEST_CASE_COLUMN = 1;
	String RUN_MODE_YES = "YES";
	
	String START = "STARTED";
	String PASS = "PASSED";
	String FAIL = "FAILED";
	String SKIP = "SKIPED";
	
	String CONFIG_FILE = "config.properties";
	String DATA_FOLDER = new File(Config.getProperty("dataPath")).getAbsolutePath()+File.separator;
	String SCREENSHOT_FOLDER = new File("screenshots").getAbsolutePath()+File.separator;
	String ALLURE_RESULTS = new File("allure-results").getAbsolutePath()+File.separator;
	String IMAGE_TYPE = ".png";
	String TEST_PACKAGE = "sg.gov.openmap.test.%s";
	
	// Time unit in seconds
	int TIME_OUT = Integer.valueOf(Config.getProperty("explicitWait"));
	Duration EXPLICIT_WAIT = Duration.ofSeconds(Long.valueOf(Config.getProperty("explicitWait"))); // 15;
	Duration IMPLICIT_WAIT = Duration.ofSeconds(Long.valueOf(Config.getProperty("implicitWait"))); //Integer.valueOf(Config.getProperty("implicitWait"));	//5;
	Duration ELEMENT_TIMEOUT = Duration.ofSeconds(Long.valueOf(Config.getProperty("elementLoadTime")));
	int MAX_TRY = Config.getProperty("retry").equalsIgnoreCase("TRUE")? 1: 0;	//1;
	
	String EXTENT_REPORT = Config.getProperty("reportPath");	//"target/reports/extent/index.html";
	String EXTENT_CONFIG = Config.getProperty("configPath");	//"src/test/resources/html-config.xml";
	
    List<String> TestData_topCenterMenus = new ArrayList<>(Arrays.asList("Community", "School Query", "Medical", "Hawker Centres"));
    List<String> TestData_FooterRightCornerLinks = new ArrayList<>(Arrays.asList("Contact Us", "Terms of Use", "Report Vulnerability"));
    
    List<String> TestData_SearchSubMenus = new ArrayList<>(Arrays.asList("Land Query", "Drone","Nearby","Map Styles","Shadow","Window View","More Services","About"));
    List<String> TestData_SearchRouteMenus = new ArrayList<>(Arrays.asList("Transit", "Bus", "Car","Cycle","Walk"));
}