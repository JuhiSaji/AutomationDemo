/**
 * @author Juhi_Saji
 *
 */
package CucumberExecutor;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import Base.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/Features/UserFlow.feature", // Path to your feature files
		glue = "StepDefinition", // Path to your step definition package
		plugin = { "pretty", // Prints the Gherkin steps in the console
				"html:target/cucumber-reports/cucumber.html", // Generates an HTML report
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // ExtentReports
		}, monochrome = true, // Makes the console output more readable
		dryRun = false, tags = "@smoke" // Tags to run specific scenarios (optional)
)

public class TestRunner extends AbstractTestNGCucumberTests {

	@AfterSuite
	public void closeBrowser() {

		DriverManager.quitDriver();
	}

}