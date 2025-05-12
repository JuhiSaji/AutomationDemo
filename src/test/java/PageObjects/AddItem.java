package PageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddItem {
	public WebDriver driver;
	Map<String, String> sTestData;
	String breedItem;
	String category = "(//*[contains(@src,'dynamicText')])[last()]";
	String item = "//tr[td[text()='dynamicText']]/td[1]";
	String breed = "//tr[td[normalize-space()='dynamicText']]//*[contains(text(), 'Cart')]";
	String cartItem = "//td[normalize-space()='dynamicText']";
	String cart = "//*[contains(text(),'Shopping Cart')]";
	String cartImg = "img_cart";
	String removeItem = "//*[text()='Remove']";
	String emptyCart = "//*[contains(text(),'empty')]";

	public AddItem(WebDriver driver, Map<String, String> sTestData) {
		this.driver = driver;
		this.sTestData = sTestData;

	}

	public void selectCategory(String name) {
		category = category.replace("dynamicText", name);
		System.out.println(category);
		driver.findElement(By.xpath(category)).click();
	}

	public void selectItem(String name) {
		item = item.replace("dynamicText", name);
		System.out.println(item);
		driver.findElement(By.xpath(item)).click();
	}

	public void selectBreed(String name) {
		breed = breed.replace("dynamicText", name);
		breedItem = name;
		System.out.println(breed);
		driver.findElement(By.xpath(breed)).click();
	}

	public String cartCheck() {

		cartItem = cartItem.replace("dynamicText", breedItem);
		System.out.println(cartItem);
		System.out.println("BreedItem:" + breedItem);

		Assert.assertTrue(driver.findElement(By.xpath(cartItem)).isDisplayed());
		return breedItem;
	}

	public void shoppingPage() {
		if (!driver.findElement(By.xpath(cart)).isDisplayed())
			driver.findElement(By.name(cart)).click();
	}

	public void cartClear() {

		while (true) {

			List<WebElement> list = driver.findElements(By.xpath(removeItem));
			if (list.isEmpty())
				break;
			WebElement item = list.get(0);
			item.click();
			new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.stalenessOf(item));
		}
	}

	public String verifyCartProceed() {
		Assert.assertTrue(driver.findElement(By.xpath(emptyCart)).isDisplayed());
		return driver.findElement(By.xpath(emptyCart)).getText();
	}

}
