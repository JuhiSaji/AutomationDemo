package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Base.DriverManager;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import PageObjects.AddItem;
import PageObjects.UserLogin;
import PageObjects.UserRegister;
import Utils.DBManager;
import Utils.Utility;
import Utils.testUserData;

public class StepDefs {
	public Scenario scenario;
	public static WebDriver driver;
	UserLogin lp;
	UserRegister reg;
	AddItem addItem;
	Map<String, String> sTestData;
	String url;

	@Before
	public void initailSetUp(Scenario scenario) throws Exception {
		this.scenario = scenario;
		Properties prop = new Properties(); // properties file load for url and browser configuration
		FileInputStream file = new FileInputStream("src/main/resources/config.properties");
		prop.load(file);
		String browser = System.getProperty("browser", prop.getProperty("browser"));
		url = System.getProperty("url", prop.getProperty("url"));
		driver = DriverManager.getDriver(browser);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		DBManager dbManager = new DBManager(); // Excel integration for test data
		sTestData = dbManager.getExcelData("src\\test\\resources\\testData\\testdata.xlsx", "testdata",
				scenario.getName());
		if (testUserData.get("userId") == null) // Dynamic user credentials generation
			Utility.getUniqueUserId();

		reg = new UserRegister(driver, sTestData);
		lp = new UserLogin(driver, sTestData);
		addItem = new AddItem(driver, sTestData);
		System.out.println("Before Hook Executed");
	}

	@After
	public void tearDown() throws Exception {
		if (scenario.isFailed()) {
			scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
					"Error Screenshot");
			driver.quit();
			driver = null;
		}
	}

	@Given("user navigate to url")
	public void user_navigate_to_url() {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("user opens the registration page")
	public void user_opens_the_registration_page() {
		reg.launchRegistration();
	}

	@When("enters registration data")
	public void enters_registration_data() {
		reg.setRegisterData();
	}

	@Then("user account should be created")
	public void user_account_should_be_created() {
		Assert.assertEquals(driver.getTitle(), "JPetStore Demo");
		scenario.log("User registered successfully");
	}

	@When("enters existing username as {string} and password as {string} and register")
	public void enters_existing_username_as_and_passowrd_as_and_register(String un, String pw) {
		reg.duplicateUser(un, pw);
	}

	@Then("user should see an error message")
	public void user_should_see_an_error_message() {
		String errorMessage = reg.errorDisplay();
		scenario.log("Error Captured:" + errorMessage);
	}

	@Given("enters invalid username as {string} and password as {string} and signin")
	public void enters_invalid_username_as_and_password_as_and_signin(String user, String pw) {
		lp.invalidLogin(user, pw);
	}

	@Then("user should see an invalid signin error message")
	public void user_should_see_an_invalid_signin_error_message() {
		String text = lp.verifyErrorSignIn();
		scenario.log("Error Message: " + text);
	}

	@Given("user opens the login page")
	public void user_opens_the_login_page() {
		lp.navigateSignIn();
	}

	@When("user enters valid credentials")
	public void user_enters_valid_credentials() {
		lp.login();
	}

	@Then("user should be signed in")
	public void user_should_be_signed_in() {
		lp.verifySignIn();
		scenario.log("Successfull SignIn");
	}

	@Given("user is signed in")
	public void user_is_signed_in() {
		lp.verifySignIn();
	}

	@When("user choose {string} category")
	public void user_choose_any_category(String name) {
		addItem.selectCategory(name);
	}

	@When("select {string}")
	public void user_choose_any_breed(String name) {
		addItem.selectItem(name);
	}

	@When("select {string} and add to cart")
	public void select_and_add_to_cart(String breed) {
		addItem.selectBreed(breed);
	}

	@Then("item should appear in the shopping cart")
	public void item_should_appear_in_the_shopping_cart() {
		String addedItem = addItem.cartCheck();
		scenario.log("Item Added :" + addedItem);
	}

	@Given("user opens AddtoCart page")
	public void user_opens_addto_cart_page() {
		addItem.shoppingPage();
	}

	@When("cart is cleared")
	public void cart_is_cleared() {
		addItem.cartClear();
	}

	@Then("User should not be able to Proceed to Checkout")
	public void user_should_not_be_able_to_proceed_to_checkout() {
		String cartMsg = addItem.verifyCartProceed();
		scenario.log("Message:" + cartMsg);
	}

}
