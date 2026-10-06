package Contacts;

import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base_test.baseTest;
import crm_reop.ContactPage;
import crm_reop.VeyContactPage;
import genric_utility.FileUtility;
import genric_utility.WebDriverUtility;


public class createContacts extends baseTest {
//	public static void main(String[] args)throws InterruptedException,IOException, ParseException {

	@Test
	public void cratecontact() throws IOException, ParseException, InterruptedException {
		// TEST CASE START
		System.out.println("==================================================");
		System.out.println("          TEST CASE STARTED : TC_CON_001");
		System.out.println("          Test Name : Create Contact");
		System.out.println("==================================================");

//		JSON TEST DATA
		
//		EXCEL TEST DATA
		System.out.println("[STEP 02] Reading Contact test data from Excel file...");

		String LastName = FileUtility.GetDataExcellFile("Contact", 4, 0);
		String Email = FileUtility.GetDataExcellFile("Contact", 4, 4);
		String asi = FileUtility.GetDataExcellFile("Contact", 4, 2);

		System.out.println("[INFO] Last Name test data loaded.");
		System.out.println("[INFO1] Email test data loaded.");
		System.out.println("[INFO] Assistant test data loaded.");

		// BROWSER SETUP

		// LOGIN
//		WebDriver driver = new ChromeDriver();
//		WebDriver driver = null;
		
		// NAVIGATE TO CONTACT MODULE

		System.out.println("[STEP 06] Navigating to Contacts module...");

		ContactPage ct = new ContactPage(driver);

		// Locate Contacts module link
		WebElement module = ct.getLink();
		module.click();
		System.out.println("[PASS] Contacts module opened successfully.");

		// CREATE NEW CONTACT

		System.out.println("[STEP 07] Opening Create Contact page...");

		// Locate Create Contact icon
		WebElement addicon = ct.getAddicon();
		addicon.click();

		System.out.println("[PASS] Create Contact page opened successfully.");

		// ENTER CONTACT LAST NAME

		System.out.println("[STEP 08] Entering Contact Last Name...");

		WebElement lastname = ct.getLastname();
		lastname.sendKeys(LastName);
		System.out.println("[INFO] Last Name entered : " + LastName);

		// ORGANIZATION POPUP
		System.out.println("[STEP 09] Selecting Organization from popup...");

		String PID2 = driver.getWindowHandle();
		System.out.println("[INFO] Parent window handle stored.");

//		 Click Organization Select button.
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@alt='Select']")).click();
		System.out.println("[INFO] Organization selection popup opened.");

//		Capture all available window handles.
		Set<String> CID2 = driver.getWindowHandles();
//		 Switch to the popup window.
		for (String i : CID2) {
			driver.switchTo().window(i);
		}
		System.out.println("[INFO] Switched to Organization popup window.");
//		 * Select Organization.
		driver.findElement(By.id("3")).click();
		System.out.println("[INFO] Organization selected successfully.");
		Thread.sleep(2000);
//		 * Switch back to the parent Contact window.
		driver.switchTo().window(PID2);
		System.out.println("[INFO] Returned to Contact creation window.");

		// SELECT LEAD SOURCE
		System.out.println("[STEP 10] Selecting Lead Source...");
		String emp = "Employee";
		WebElement ld = ct.getLead();
//		 * Select Lead Source using WebDriverUtility.
		WebDriverUtility ns = new WebDriverUtility(driver);
		ns.select(ld, emp);
		System.out.println("[INFO] Lead Source selected : " + emp);

		// ENTER PHONE NUMBER
		System.out.println("[STEP 11] Entering Contact Phone Number...");

		WebElement phone = ct.getPhone();
		phone.sendKeys("1234567892");
		System.out.println("[INFO] Phone number entered successfully.");

		// ENTER EMAIL
		System.out.println("[STEP 12] Entering Contact Email...");
		WebElement email = ct.getEmail();
		email.sendKeys(Email);
		System.out.println("[INFO] Email entered : " + Email);

		// ENTER ASSISTANT

		System.out.println("[STEP 13] Entering Assistant name...");
		WebElement assistant = ct.getAssitance();
		assistant.sendKeys(asi);
		System.out.println("[INFO] Assistant entered : " + asi);

		// ENTER DESCRIPTION
		System.out.println("[STEP 14] Entering Contact Description...");
		String Dis = "its is done";
		WebElement description = ct.getDescription();
		description.sendKeys(Dis);
		System.out.println("[INFO] Description entered : " + Dis);

		// SAVE CONTACT
		System.out.println("[STEP 15] Saving Contact...");
		WebElement but = ct.getButton();
		but.click();

		System.out.println("[PASS] Contact Save button clicked.");
		System.out.println("[INFO] Contact creation process completed.");

		// VALIDATION
		
		System.out.println("==================================================");
		System.out.println("              CONTACT VALIDATION");
		System.out.println("==================================================");
//		 * Fetch actual values displayed on Contact details page.
		VeyContactPage vr = new VeyContactPage(driver);
		String Vlastna = vr.getlastname().getText();
		String VEmail = vr.getEmail().getText();
		String Vlead = vr.getLead().getText();
		String VAssis = vr.getAssis().getText();
		String VDescrpt = vr.getDiscript().getText();

		// LAST NAME VERIFICATION

		
		System.out.println("\n========== LAST NAME VERIFICATION ==========");
		System.out.println("Expected Result : " + LastName);
		System.out.println("Actual Result   : " + Vlastna);
		Assert.assertEquals(LastName,Vlastna);
		/*
		if (LastName.equals(Vlastna)) {
			System.out.println("PASS : Last Name is matched");
		} else {
			System.out.println("FAIL : Last Name is not matched");
		}
*/
		// EMAIL VERIFICATION

		System.out.println("\n========== EMAIL VERIFICATION ==========");
		System.out.println("Expected Result : " + Email);
		System.out.println("Actual Result   : " + VEmail);

//		Assert.assertEquals("Email","VEmail");
		/*
		if (Email.equals(VEmail)) {
			System.out.println("PASS : Email is matched");
		} else {
			System.out.println("FAIL : Email is not matched");
		}
*/
		// LEAD SOURCE VERIFICATION

		System.out.println("\n========== LEAD SOURCE VERIFICATION ==========");
		System.out.println("Expected Result : " + emp);
		System.out.println("Actual Result   : " + Vlead);

		Assert.assertEquals(Vlead,emp);
		/*
		if (emp.equals(Vlead)) {
			System.out.println("PASS : Lead Source is matched");
		} else {
			System.out.println("FAIL : Lead Source is not matched");
		}
*/
		// ASSISTANT VERIFICATION

		System.out.println("\n========== ASSISTANT VERIFICATION ==========");
		System.out.println("Expected Result : " + asi);
		System.out.println("Actual Result   : " + VAssis);

		Assert.assertEquals(asi,VAssis);
		/*
		if (asi.equals(VAssis)) {
			System.out.println("PASS : Assistant is matched");
		} else {
			System.out.println("FAIL : Assistant is not matched");
		}
*/
	
		// DESCRIPTION VERIFICATION
		
		System.out.println("\n========== DESCRIPTION VERIFICATION ==========");
		System.out.println("Expected Result : " + Dis);
		System.out.println("Actual Result   : " + VDescrpt);

		Assert.assertEquals(VDescrpt,Dis);
		/*
		if (Dis.equals(VDescrpt)) {
			System.out.println("PASS : Description is matched");
		} else {
			System.out.println("FAIL : Description is not matched");
		}
*/
		// LOGOUT

		// CLOSE BROWSER
		

		// TEST CASE END
		System.out.println("\n==================================================");
		System.out.println("          TEST CASE COMPLETED : TC_CON_001");
		System.out.println("          Test Name : Create Contact");
		System.out.println("==================================================");

	}
}