package Base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	static WebDriver driver;

	public static void setDriver(String browser) throws IOException {

		if (browser != null) {
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		}
	}

	public static WebDriver getDriver(String browser) throws IOException {
		if (driver == null) {
			setDriver(browser);
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Driver already closed or could not quit: " + e.getMessage());
			} finally {
				driver = null;
			}
		}
	}
}
