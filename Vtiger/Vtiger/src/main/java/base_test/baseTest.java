package base_test;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import crm_reop.LoginPage;
import crm_reop.SignOut;
import genric_utility.FileUtility;
import genric_utility.JavaUtility;
import genric_utility.WebDriverUtility;

public class baseTest {
	public WebDriver driver;
	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	
	@BeforeSuite
	public void repConfig() {
//		configuration
		String time = JavaUtility.getCurrentDateTime();
		spark = new ExtentSparkReporter("./ad_reports/" + time + ".html");

		spark.config().setDocumentTitle("Viger");
		spark.config().setReportName("Reports");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("browser", "chrome");
		report.setSystemInfo("window", "11");
	} 
	@BeforeClass
	public void brosetup() throws IOException, ParseException {
//		JSON TEST DATA
		String browser = FileUtility.GetDataFJsonFile("bro");
		String url = FileUtility.GetDataFJsonFile("url");
//		 WebDriver 

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			Reporter.log("[WARN] Invalid browser specified. Launching Chrome by default.", true);
			driver = new ChromeDriver();
		}

//		driver = new ChromeDriver();
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
		@AfterSuite
		public void repbackup() {
			report.flush();
		
	}

}
