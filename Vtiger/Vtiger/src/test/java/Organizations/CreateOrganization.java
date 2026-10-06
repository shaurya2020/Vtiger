package Organizations;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base_test.baseTest;
import crm_reop.OrgPage;
import crm_reop.VeryOrgPage;
import genric_utility.FileUtility;
import genric_utility.JavaUtility;
import genric_utility.WebDriverUtility;


public class CreateOrganization extends baseTest {

	@Test
	public void createOrg() throws IOException, ParseException {

		// ============================================================
		// TEST CASE START
		// ============================================================

		Reporter.log("============================================================", true);
		Reporter.log("TEST CASE STARTED : TC_ORG_001", true);
		Reporter.log("Test Name : Create Organization", true);
		Reporter.log("============================================================", true);


		// ============================================================
		// STEP 01 : READ JSON TEST DATA
	

		// ============================================================
		// STEP 02 : GENERATE RANDOM ORGANIZATION NUMBER
		// ============================================================

		Reporter.log("[STEP 02] Generating random number for Organization Name...", true);

		JavaUtility jd = new JavaUtility();

		int zs = jd.generateRandomNumber(1000);

		Reporter.log("[INFO] Random number generated : " + zs, true);


		// ============================================================
		// STEP 03 : READ EXCEL TEST DATA
		// ============================================================

		Reporter.log("[STEP 03] Reading Organization test data from Excel...", true);

		String accountName = FileUtility.GetDataExcellFile("ORGname", 4, 0) + zs;
		String email = FileUtility.GetDataExcellFile("ORGname", 5, 4);

		Reporter.log("[INFO] Organization Name loaded : " + accountName, true);
		Reporter.log("[INFO] Email test data loaded : " + email, true);


		// ============================================================
		// STEP 07 : NAVIGATE TO ORGANIZATIONS MODULE
		// ============================================================
		OrgPage Og = new OrgPage(driver);

		Reporter.log("[STEP 07] Navigating to Organizations module...", true);

		WebElement module = Og.getLink();

		module.click();

		Reporter.log("[PASS] Organizations module opened successfully.", true);


		// ============================================================
		// STEP 08 : OPEN CREATE ORGANIZATION PAGE
		// ============================================================

		Reporter.log("[STEP 08] Opening Create Organization page...", true);

		WebElement mod = Og.getCss();

		mod.click();

		Reporter.log("[PASS] Create Organization page opened successfully.", true);


		// ============================================================
		// STEP 09 : ENTER ORGANIZATION NAME
		// ============================================================

		Reporter.log("[STEP 09] Entering Organization Name...", true);

		WebElement OrgNam = Og.getAccname();

		OrgNam.sendKeys(accountName);

		Reporter.log("[INFO] Organization Name entered : " + accountName, true);


		// ============================================================
		// STEP 10 : GENERATE AND ENTER PHONE NUMBER
		// ============================================================

		Reporter.log("[STEP 10] Generating and entering Phone Number...", true);

		JavaUtility jds = new JavaUtility();

		int jz = jds.generateRandomNumber(1000);

		String Num = "9140050" + jz;

		Reporter.log("[INFO] Generated Phone Number : " + Num, true);

		WebElement phone = Og.getPhone();

		phone.sendKeys(Num);

		Reporter.log("[PASS] Phone Number entered successfully.", true);


		// ============================================================
		// STEP 11 : ENTER EMAIL
		// ============================================================

		Reporter.log("[STEP 11] Entering Organization Email...", true);

		WebElement Email = Og.getEmail();

		Email.sendKeys(email);

		Reporter.log("[INFO] Email entered : " + email, true);


		// ============================================================
		// STEP 12 : SELECT INDUSTRY
		// ============================================================

		Reporter.log("[STEP 12] Selecting Organization Industry...", true);

		String sell = "Education";

		WebElement Ind = Og.getIndustry();

		WebDriverUtility sg = new WebDriverUtility(driver);

		sg.select(Ind, sell);

		Reporter.log("[PASS] Industry selected : " + sell, true);


		// ============================================================
		// STEP 13 : SELECT ORGANIZATION TYPE
		// ============================================================

		Reporter.log("[STEP 13] Selecting Organization Type...", true);

		String cs = "Customer";

		WebElement Type = Og.getAccounttype();

		WebDriverUtility Tys = new WebDriverUtility(driver);

		Tys.select(Type, cs);

		Reporter.log("[PASS] Organization Type selected : " + cs, true);


		// ============================================================
		// STEP 14 : SAVE ORGANIZATION
		// ============================================================

		Reporter.log("[STEP 14] Saving Organization...", true);

		WebElement but = Og.getButton();

		but.click();

		Reporter.log("[PASS] Save button clicked successfully.", true);
		Reporter.log("[INFO] Organization creation process completed.", true);


		// ============================================================
		// STEP 15 : ORGANIZATION VALIDATION
		// ============================================================

		Reporter.log("============================================================", true);
		Reporter.log("ORGANIZATION VALIDATION STARTED", true);
		Reporter.log("============================================================", true);

		VeryOrgPage os = new VeryOrgPage(driver);


		// Fetch actual Organization Name

		Reporter.log("[INFO] Fetching actual Organization Name...", true);

		String VOrgNam = os.getOrgname().getText();


		// Fetch actual Phone

		Reporter.log("[INFO] Fetching actual Phone Number...", true);

		String Vphone = os.getPhone().getText();


		// Fetch actual Email

		Reporter.log("[INFO] Fetching actual Email...", true);

		String VEmail = os.getEmail().getText();


		// Fetch actual Industry

		Reporter.log("[INFO] Fetching actual Industry...", true);

		String VInd = os.getIndustery().getText();


		// Fetch actual Type

		Reporter.log("[INFO] Fetching actual Organization Type...", true);

		String Vtype = os.getType().getText();


		// ============================================================
		// ORGANIZATION NAME VERIFICATION
		// ============================================================

		Reporter.log("========== ORGANIZATION NAME VERIFICATION ==========", true);

		Reporter.log("Expected Result : " + accountName, true);
		Reporter.log("Actual Result   : " + VOrgNam, true);

		if (VOrgNam.equals(accountName)) {

			Reporter.log("[PASS] Organization Name is matched.", true);

		} else {

			Reporter.log("[FAIL] Organization Name is not matched.", true);
		}


		// ============================================================
		// PHONE VERIFICATION
		// ============================================================

		Reporter.log("========== PHONE VERIFICATION ==========", true);

		Reporter.log("Expected Result : " + Num, true);
		Reporter.log("Actual Result   : " + Vphone, true);

		if (Vphone.equals(Num)) {

			Reporter.log("[PASS] Phone is matched.", true);

		} else {

			Reporter.log("[FAIL] Phone is not matched.", true);
		}


		// ============================================================
		// EMAIL VERIFICATION
		// ============================================================

		Reporter.log("========== EMAIL VERIFICATION ==========", true);

		Reporter.log("Expected Result : " + email, true);
		Reporter.log("Actual Result   : " + VEmail, true);

		if (VEmail.equals(email)) {

			Reporter.log("[PASS] Email is matched.", true);

		} else {

			Reporter.log("[FAIL] Email is not matched.", true);
		}


		// ============================================================
		// INDUSTRY VERIFICATION
		// ============================================================

		Reporter.log("========== INDUSTRY VERIFICATION ==========", true);

		Reporter.log("Expected Result : " + sell, true);
		Reporter.log("Actual Result   : " + VInd, true);

		if (VInd.equals(sell)) {

			Reporter.log("[PASS] Industry is matched.", true);

		} else {

			Reporter.log("[FAIL] Industry is not matched.", true);
		}


		// ============================================================
		// TYPE VERIFICATION
		// ============================================================

		Reporter.log("========== ORGANIZATION TYPE VERIFICATION ==========", true);

		Reporter.log("Expected Result : " + cs, true);
		Reporter.log("Actual Result   : " + Vtype, true);

		if (Vtype.equals(cs)) {

			Reporter.log("[PASS] Organization Type is matched.", true);

		} else {

			Reporter.log("[FAIL] Organization Type is not matched.", true);
		}


		// ============================================================
		// TEST CASE END
		// ============================================================

		Reporter.log("============================================================", true);
		Reporter.log("TEST CASE COMPLETED : TC_ORG_001", true);
		Reporter.log("Test Name : Create Organization", true);
		Reporter.log("============================================================", true);
	}
}
