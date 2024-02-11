# QI_InterviewAssignment
# Overview:
The QI_Interview assignment aims to automate selected scenarios of One Map, demonstrating proficiency in automation skills for interviews. This assignment employs a Data-Driven Framework utilizing the Page Object design pattern, which effectively segregates the test scripts' logic from the test data. This approach enables the creation of test automation scripts by leveraging various sets of test data. The test data sets are stored in external files or resources such as MS Excel Sheets. The test scripts access these external resources to retrieve the necessary test data. This framework offers significant advantages, notably reducing the number of test scripts required compared to a modular-based framework, especially when testing for multiple data sets concerning the same functionality.

For assignment purpose all the test cases are created for [One Map](https://www.onemap.gov.sg/) site.

### **Some of the key features of this framework:**

1. Generates Extent & Allure reports with all the step details.
2. Support parallel execution of test cases.
3. Generates test execution log file.
4. Test execution can be triggered form command line.
5. Framework uses Page Object Design Pattern, hence there is clean separation between test code and page specific code such as locators and layout.
6. Supports re-run of failed test cases.
7. Allows to control the tests to be run using sheet (Sanity/Regression) in TestData.xlsx sheet.

## **Required Setup :**

- [Java v17](https://java.tutorials24x7.com/blog/how-to-install-java-17-on-windows) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- Download the files from Git repository either as zip file OR using [Git](https://phoenixnap.com/kb/how-to-install-git-windows).
- Download and install [Allure](https://docs.qameta.io/allure/#_manual_installation) command line application, suitable for your environment.

## **Running Test:**
All the test to be executed can be configured in TestData.xlsx sheet placed in below path.<br><br>
src\test\resources\data\TestData.xlsx<br><br>
List all the tests to be executed in Sanity or Regression sheet. Update the config.properties file sheet parameter with sheet (Eg: Regression/Sanity) that needs to be executed.

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

mvn clean test -Dthreads=10

This will run 10 test cases in parallel (default thread count is 1).

You can also change the execution sheet at run time by using set command as shown below. This will override the sheet value in config.properties file.

set sheet=Sanity && mvn clean test -Dthreads=10

Once the execution completes report will be generated in below folder structure.

**Extent Report:** 	./target/reports/extent/index.html

**Allure Report:** To generate the report we need to go through below steps.

To generate and see the report from existing Allure results use below command.

allure serve

it will generate and report and launched it in you local browser.
**Execution Log:** 
Logs generated at the time of test execution will be available at ./target/logs/automation.log
