package PageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utils.Utility;
import Utils.testUserData;

public class UserLogin {
	public WebDriver driver;
	Map<String, String> sTestData;
	String signIn="//*[contains(text(),'Sign')]";
	String username="username";
	String password="password";
	String signOn="signon";
	String signout="//*[contains(text(),'Out')]";
	String invalidMsg="//*[contains(text(),'Invalid username or password.  Signon failed.')]";
	
	public UserLogin(WebDriver driver,Map<String, String> sTestData) {
		this.driver=driver;
		this.sTestData=sTestData;
		
	}
	public void navigateSignIn() {
		driver.get("https://petstore.octoperf.com/actions/Account.action?signonForm=");
		driver.findElement(By.xpath(signIn)).click();
	}
	
	public void login() 
	{	System.out.println("Registered"+(String) testUserData.get("userId")+" "+(String) testUserData.get("password"));
		Utility.performWait(driver, By.name(username));
		WebElement user=driver.findElement(By.name(username));
		WebElement pWord=driver.findElement(By.name(password));
		
		user.clear();
		user.sendKeys((String) testUserData.get("userId"));
		pWord.clear();
		pWord.sendKeys((String) testUserData.get("password"));
		driver.findElement(By.name(signOn)).click();
	}
	
	public void verifySignIn() {
		Assert.assertEquals(driver.findElement(By.xpath(signout)).getText(),"Sign Out");
	}
	
	public void invalidLogin(String uname,String pass) {
		driver.findElement(By.name(username)).sendKeys(uname);
		driver.findElement(By.name(password)).sendKeys(pass);
		driver.findElement(By.name(signOn)).click();
	}
	public String verifyErrorSignIn() {
		driver.findElement(By.xpath(invalidMsg)).getText().contains("Invalid username or password");
		return driver.findElement(By.xpath(invalidMsg)).getText();
	}
	
}
