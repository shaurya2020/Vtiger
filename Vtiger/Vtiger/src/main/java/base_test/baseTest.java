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
	public WebDriver driver;

	@BeforeClass
	public void brosetup() throws IOException, ParseException {
//		JSON TEST DATA
		String url = FileUtility.GetDataFJsonFile("url");
//		 WebDriver 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
	}

	@BeforeMethod
	public void brologin() throws IOException, ParseException {
		// LOGIN
		String username = FileUtility.GetDataFJsonFile("un");
		String password = FileUtility.GetDataFJsonFile("pwd");

		LoginPage lg = new LoginPage(driver);
		lg.getusername().sendKeys(username);
		lg.getPassword().sendKeys(password);
		lg.getbutton().click();
	}

	@AfterMethod
	public void brologout() {

		SignOut sn = new SignOut(driver);
		WebElement profile = sn.getPro();

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.hover(profile);
		WebElement Out = sn.getSingnOut();
		Out.click();
	}

	@AfterClass
	public void broteardown() {
		driver.quit();
		System.out.println("[PASS] Browser closed successfully.");

	}

}
