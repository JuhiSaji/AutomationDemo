# Selenium BDD Framework
This project demonstrates automation of a web application JPetstore using Selenium WebDriver, Cucumber(BDD), TestNG, and Java.

## Technologies Used
- Java 21
- Selenium WebDriver
- Cucumber(BDD)
- TestNG
- Maven
- Extent Reports

## Features Covered
User Registration with dynamic data.
Sign In using the user credentials.
Add to Cart.
Negative Test Scenarios.
Pipeline using GitHub actions for manual and scheduled runs.

## Test Data Management
Test data is managed using a combination of:
  Excel files: For external data-driven testing.
  Scenario & Scenario Outline with Examples: For parameterized testing in feature files.
  Dynamic data generation: For generating unique user data (e.g., user ID, password) during runtime.

## Browser Configuration
To run tests on a different browser, pass parameter through config.properties file or mvn cmd prompt
- For Chrome: mvn test -Dbrowser=chrome
- For Edge: mvn test -Dbrowser=edge

## Run Tests
1. Open command prompt and take a clone of the code from git remote repository using below command :
	  git clone https://github.com/JuhiSaji/AutomationPOC.git
2. Navigate to local repository using command : cd local-project-directory-path
3. Execute command 'mvn clean test' to run the tests 
Alternatively, Go to GitHubRepo → Actions → Select "Run Workflow" to manually trigger workflow using GitHub actions or automated workflow trigger scheduled at 12 am everyday
 
## HTML Report
Find reports post execution at : 
	target/cucumber-reports/cucumber.html for cucumber html reports
	target/Extent-reports/ExtentReport.html for extent reports
