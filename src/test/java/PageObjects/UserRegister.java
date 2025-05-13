package PageObjects;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.testUserData;

public class UserRegister {
	public WebDriver driver;
	Map<String, String> sTestData;
	String signIn = "//*[contains(text(),'Sign')]";
	String register = "//*[contains(text(),'Register')]";
	String userid = "username";
	String pwd = "password";
	String repeatPwd = "repeatedPassword";
	String fName = "account.firstName";
	String lName = "account.lastName";
	String email = "account.email";
	String phone = "account.phone";
	String addr1 = "account.address1";
	String add2 = "account.address2";
	String city = "account.city";
	String state = "account.state";
	String zip = "account.zip";
	String country = "account.country";
	String langPref = "account.languagePreference";
	String favCategory = "account.favouriteCategoryId";
	String submit = "newAccount";
	String error = "//h1[contains(text(),'Error')]";

	public UserRegister(WebDriver driver, Map<String, String> sTestData) {
		this.driver = driver;
		this.sTestData = sTestData;
	}

	public void launchRegistration() {
		driver.findElement(By.xpath(signIn)).click();
		driver.findElement(By.xpath(register)).click();
	}

	public void setRegisterData() {
		String userId = (String) testUserData.get("userId");
		String pass = (String) testUserData.get("password");
		driver.findElement(By.name(userid)).sendKeys(userId);
		driver.findElement(By.name(pwd)).sendKeys(pass);
		driver.findElement(By.name(repeatPwd)).sendKeys(pass);
		driver.findElement(By.name(fName)).sendKeys(sTestData.get("FirstName"));
		driver.findElement(By.name(lName)).sendKeys(sTestData.get("LastName"));
		driver.findElement(By.name(email)).sendKeys(sTestData.get("EmailID"));
		driver.findElement(By.name(phone)).sendKeys(sTestData.get("Phone"));
		driver.findElement(By.name(addr1)).sendKeys(sTestData.get("Add1"));
		driver.findElement(By.name(add2)).sendKeys(sTestData.get("Add2"));
		driver.findElement(By.name(city)).sendKeys(sTestData.get("City"));
		driver.findElement(By.name(state)).sendKeys(sTestData.get("State"));
		driver.findElement(By.name(zip)).sendKeys(sTestData.get("Zip"));
		driver.findElement(By.name(country)).sendKeys(sTestData.get("Country"));
		driver.findElement(By.name(submit)).click();
	}

	public void duplicateUser(String user, String pass) {
		driver.findElement(By.name(userid)).sendKeys(user);
		driver.findElement(By.name(pwd)).sendKeys(pass);
		driver.findElement(By.name(repeatPwd)).sendKeys(pass);
		driver.findElement(By.name(submit)).click();
	}

	public String errorDisplay() {
		Assert.assertTrue(driver.findElement(By.xpath(error)).isDisplayed());
		return driver.findElement(By.xpath(error)).getText();
	}
}
