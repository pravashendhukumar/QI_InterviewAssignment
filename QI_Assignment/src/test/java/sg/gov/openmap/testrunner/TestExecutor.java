package sg.gov.openmap.testrunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.testng.annotations.Factory;

import sg.gov.openmap.configs.GlobalVariables;
import sg.gov.openmap.utils.ExcelUtil;

public class TestExecutor implements GlobalVariables{
	
	@Factory
	public Object[] testCasesToExecute() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<Object> tests = new ArrayList<Object>();
		ExcelUtil excel = new ExcelUtil();
		excel.setExcelFile(DATA_FOLDER + WORKBOOK);
		for(String scenario : excel.getScenariosToRun(SCENARIO_SHEET_NAME, RUN_MODE_COLUMN, TEST_CASE_COLUMN)) {
			tests.add(createObject(scenario));
		}
		return tests.toArray();
	}

	public Object createObject(String test) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object object = null;
		try {
			Class<?> testClass = Class.forName(String.format(TEST_PACKAGE, test));
			object = testClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}
}
