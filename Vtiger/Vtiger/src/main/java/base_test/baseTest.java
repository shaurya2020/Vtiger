package base_test;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import crm_reop.LoginPage;
import crm_reop.SignOut;
import genric_utility.FileUtility;
import genric_utility.WebDriverUtility;

public class baseTest {
	protected WebDriver driver ;

	@BeforeClass
	public void brosetup() throws IOException, ParseException {
		driver = new ChromeDriver();
//			JSON TEST DATA
		String url = FileUtility.GetDataFJsonFile("url");
		 System.out.println("[STEP 01] Reading login data from JSON file...");

		 System.out.println("[INFO] Application URL loaded successfully.");
		 System.out.println("[INFO] Username loaded successfully.");
		 System.out.println("[INFO] Password loaded successfully.");

		 System.out.println("[STEP 03] Launching Chrome browser...");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		 System.out.println("[INFO] Chrome browser launched successfully.");
		 System.out.println("[INFO] Browser window maximized.");
		 System.out.println("[INFO] Implicit wait configured : 15 seconds.");

//			OPEN APPLICATION

		 System.out.println("[STEP 04] Opening Vtiger CRM application...");
		driver.get(url);
		 System.out.println("[PASS] Vtiger CRM application opened successfully.");

	}

	@BeforeMethod
	public void brologin() throws IOException, ParseException {
		// LOGIN
		String username = FileUtility.GetDataFJsonFile("un");
		String password = FileUtility.GetDataFJsonFile("pwd");
		 System.out.println("[STEP 05] Performing application login...");

		LoginPage lg = new LoginPage(driver);

		// Locate username field using Page Object Model
		WebElement user = lg.getusername();
		user.sendKeys(username);
		 System.out.println("[INFO] Username entered successfully.");

		// Locate password field using Page Object Model
		WebElement pass = lg.getPassword();
		pass.sendKeys(password);
		 System.out.println("[INFO] Password entered successfully.");

		// Locate Login button
		WebElement login = lg.getbutton();
		login.click();

		 System.out.println("[PASS] Login button clicked.");
		 System.out.println("[PASS] User logged into the application.");

	}

	@AfterMethod
	public void brologout() {

		System.out.println("\n[STEP 16] Logging out from application...");
		SignOut sn = new SignOut(driver);
		WebElement profile = sn.getPro();

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.hover(profile);
		 System.out.println("[INFO] Profile menu opened.");
		WebElement Out = sn.getSingnOut();
		Out.click();
		 System.out.println("[PASS] User logged out successfully.");

	}

	@AfterClass
	public void broteardown() {
		driver.quit();
		 System.out.println("[PASS] Browser closed successfully.");

	}

}
