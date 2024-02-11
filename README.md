# QI_InterviewAssignment
# Overview:
The QI_Interview assignment aims to automate selected scenarios of One Map, demonstrating proficiency in automation skills for interviews. This assignment employs a Data-Driven Framework utilizing the Page Object design pattern, which effectively segregates the test scripts' logic from the test data. This approach enables the creation of test automation scripts by leveraging various sets of test data. The test data sets are stored in external files or resources such as MS Excel Sheets. The test scripts access these external resources to retrieve the necessary test data. This framework offers significant advantages, notably reducing the number of test scripts required compared to a modular-based framework, especially when testing for multiple data sets concerning the same functionality.

For the assignment, all test cases are designed specifically for the One Map website.

# Key Features of the Framework:
* Report Generation: Generates detailed Extent and Allure reports, including step details.
* Parallel Execution: Supports parallel execution of test cases for faster testing.
* Log File Generation: Creates a comprehensive test execution log file for tracking purposes.
* Command Line Execution: Enables test execution directly from the command line for convenience.
* Page Object Design Pattern: Utilizes the Page Object Design Pattern, ensuring a clear separation between test code and page-specific elements such as locators and layout.
* Rerun Failed Tests: Supports the rerun of failed test cases to ensure comprehensive test coverage.
* Test Control via Sheet: Allows control of the tests to be run using the "Sanity" sheet in the TestData.xlsx file.
# Required Setup :
* Java v17 should be installed and configured.
* Maven should be installed and configured.
* Download the files from Git repository either as zip file OR using Git.
* Download and install Allure command line application, suitable for your environment.
# Running Test:
* All the test to be executed can be configured in TestData.xlsx sheet placed in below path.

src\test\resources\data\TestData.xlsx

* List all the tests to be executed in Sanity. Update the config.properties file sheet parameter with sheet (Eg: Sanity) that needs to be executed.

* Open the command prompt and navigate to the folder in which pom.xml file is present. Run the below Maven command.

* mvn clean test -Dthreads=2
This will run 2 test cases in parallel (default thread count is 1).

* You can also change the execution sheet at run time by using set command as shown below. This will override the sheet value in config.properties file.

* set sheet=Sanity && mvn clean test -Dthreads=2
* Once the execution completes report will be generated in below folder structure.

# Extent Report: ./target/reports/extent/index.html

# Allure Report: 
To generate the report we need to go through below steps.

To generate and see the report from existing Allure results use below command.

* allure serve
it will generate and report and launched it in you local browser.

* Execution Log: Logs generated at the time of test execution will be available at ./target/logs/automation.log
