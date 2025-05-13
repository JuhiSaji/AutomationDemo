/**
 * @author Juhi_Saji
 *
 */
package CucumberExecutor;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import Base.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/Features/UserFlow.feature", glue = "StepDefinition", plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", }, monochrome = true, dryRun = false, tags = "@smoke")

public class TestRunner extends AbstractTestNGCucumberTests {

	@AfterSuite
	public void closeBrowser() {
		DriverManager.quitDriver();
	}

}