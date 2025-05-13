package Utils;

import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	public static void getUniqueUserId() {
		String userId = "MTUser_" + UUID.randomUUID().toString().substring(0, 6);
		testUserData.set("userId", userId);
		String pwd = "Pwd@" + UUID.randomUUID().toString().substring(0, 4);
		testUserData.set("password", pwd);

	}

	public static void performWait(WebDriver driver, By elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elem));
	}

}
