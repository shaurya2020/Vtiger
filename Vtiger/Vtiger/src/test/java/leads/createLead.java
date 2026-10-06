package leads;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base_test.baseTest;
import crm_reop.Leadspage;
import crm_reop.VeryLeadPage;
import genric_utility.FileUtility;
import genric_utility.WebDriverUtility;



public class createLead extends baseTest{

//	public static void main(String[] args) throws IOException, ParseException {
	@Test
	public void createlead() throws IOException, ParseException {
		// TEST CASE START
		System.out.println("============================================================");
		System.out.println("              TEST CASE STARTED : TC_LEAD_001");
		System.out.println("              Test Name : Create Lead");
		System.out.println("============================================================");

	
		// READ EXCEL TEST DATA
		System.out.println("\n[STEP 02] Reading Lead test data from Excel file...");

		String LastName = FileUtility.GetDataExcellFile("Lead", 4, 0);
		String CompanyName = FileUtility.GetDataExcellFile("Lead", 4, 1);
		String Num = FileUtility.GetDataExcellFile("Lead", 4, 4);

//		 * Lead Source and Industry are currently provided as
		String Emp = "Employee";
		String IndSel = "Education";


		System.out.println("[INFO] Last Name loaded : " + LastName);
		System.out.println("[INFO] Company Name loaded : " + CompanyName);
		System.out.println("[INFO] Lead Source selected : " + Emp);
		System.out.println("[INFO] Industry selected : " + IndSel);
		System.out.println("[INFO] Phone Number loaded successfully.");


		// STEP 03 : LAUNCH CHROME BROWSER
		
		// LOGIN

		// ============================================================
		// STEP 06 : NAVIGATE TO LEADS MODULE
		// ============================================================

		System.out.println("\n[STEP 06] Navigating to Leads module...");

		Leadspage lp = new Leadspage(driver);

		WebElement module = lp.getModule();

		module.click();

		System.out.println("[PASS] Leads module opened successfully.");


		// ============================================================
		// STEP 07 : OPEN CREATE LEAD PAGE
		// ============================================================

		System.out.println("\n[STEP 07] Opening Create Lead page...");

		WebElement mod = lp.getMod();

		mod.click();

		System.out.println("[PASS] Create Lead page opened successfully.");


		// ============================================================
		// STEP 08 : ENTER LAST NAME
		// ============================================================

		System.out.println("\n[STEP 08] Entering Lead Last Name...");

		WebElement lastname = lp.getLastname();

		lastname.sendKeys(LastName);

		System.out.println("[INFO] Last Name entered : " + LastName);


		// ============================================================
		// STEP 09 : ENTER COMPANY NAME
		// ============================================================

		System.out.println("\n[STEP 09] Entering Company Name...");

		WebElement Company = lp.getCompany();

		Company.sendKeys(CompanyName);

		System.out.println("[INFO] Company Name entered : " + CompanyName);


		// ============================================================
		// STEP 10 : SELECT LEAD SOURCE
		// ============================================================

		System.out.println("\n[STEP 10] Selecting Lead Source...");

		WebElement lead = lp.getLead();

		WebDriverUtility lsp = new WebDriverUtility(driver);

		lsp.select(lead, Emp);

		System.out.println("[INFO] Lead Source selected : " + Emp);


		// ============================================================
		// STEP 11 : SELECT INDUSTRY
		// ============================================================

		System.out.println("\n[STEP 11] Selecting Industry...");

		WebElement Ind = lp.getInd();

		lsp.select(Ind, IndSel);

		System.out.println("[INFO] Industry selected : " + IndSel);


		// ============================================================
		// STEP 12 : ENTER PHONE NUMBER
		// ============================================================

		System.out.println("\n[STEP 12] Entering Lead Phone Number...");

		WebElement Phone = lp.getPhone();

		Phone.sendKeys(Num);

		System.out.println("[INFO] Phone Number entered successfully.");


		// ============================================================
		// STEP 13 : SAVE LEAD
		// ============================================================

		System.out.println("\n[STEP 13] Saving Lead...");

		WebElement butt = lp.getButt();

		butt.click();

		System.out.println("[PASS] Save button clicked successfully.");
		System.out.println("[INFO] Lead creation process completed.");


		// ============================================================
		// STEP 14 : LEAD VALIDATION
		// ============================================================

		System.out.println("\n============================================================");
		System.out.println("                    LEAD VALIDATION");
		System.out.println("============================================================");

		VeryLeadPage vlp = new VeryLeadPage(driver);


		// Fetch actual Last Name
		System.out.println("[INFO] Fetching actual Last Name...");

		String VLastName = vlp.getLastname().getText();


		// Fetch actual Company Name
		System.out.println("[INFO] Fetching actual Company Name...");

		String Vcompaney = vlp.getCompany().getText();


		// Fetch actual Lead Source
		System.out.println("[INFO] Fetching actual Lead Source...");

		String VleadS = vlp.getLeads().getText();


		// Fetch actual Industry
		System.out.println("[INFO] Fetching actual Industry...");

		String VInd = vlp.getInd().getText();


		// Fetch actual Phone Number
		System.out.println("[INFO] Fetching actual Phone Number...");

		String VNum = vlp.getNum().getText();


		// ============================================================
		// LAST NAME VERIFICATION
		// ============================================================

		System.out.println("\n========== LAST NAME VERIFICATION ==========");

		System.out.println("Expected Result : " + LastName);
		System.out.println("Actual Result   : " + VLastName);

		if (LastName.equals(VLastName)) {

			System.out.println("PASS : Last Name is matched");

		} else {

			System.out.println("FAIL : Last Name is not matched");
		}


		// ============================================================
		// COMPANY NAME VERIFICATION
		// ============================================================

		System.out.println("\n========== COMPANY NAME VERIFICATION ==========");

		System.out.println("Expected Result : " + CompanyName);
		System.out.println("Actual Result   : " + Vcompaney);

		if (CompanyName.equals(Vcompaney)) {

			System.out.println("PASS : Company Name is matched");

		} else {

			System.out.println("FAIL : Company Name is not matched");
		}


		// ============================================================
		// LEAD SOURCE VERIFICATION
		// ============================================================

		System.out.println("\n========== LEAD SOURCE VERIFICATION ==========");

		System.out.println("Expected Result : " + Emp);
		System.out.println("Actual Result   : " + VleadS);

		if (Emp.equals(VleadS)) {

			System.out.println("PASS : Lead Source is matched");

		} else {

			System.out.println("FAIL : Lead Source is not matched");
		}


		// ============================================================
		// INDUSTRY VERIFICATION
		// ============================================================

		System.out.println("\n========== INDUSTRY VERIFICATION ==========");

		System.out.println("Expected Result : " + IndSel);
		System.out.println("Actual Result   : " + VInd);

		if (IndSel.equals(VInd)) {

			System.out.println("PASS : Industry is matched");

		} else {

			System.out.println("FAIL : Industry is not matched");
		}


		// ============================================================
		// PHONE NUMBER VERIFICATION
		// ============================================================

		System.out.println("\n========== PHONE NUMBER VERIFICATION ==========");

		System.out.println("Expected Result : " + Num);
		System.out.println("Actual Result   : " + VNum);

		if (Num.equals(VNum)) {

			System.out.println("PASS : Phone Number is matched");

		} else {

			System.out.println("FAIL : Phone Number is not matched");
		}



		// ============================================================
		// TEST CASE END
		// ============================================================

		System.out.println("\n============================================================");
		System.out.println("              TEST CASE COMPLETED : TC_LEAD_001");
		System.out.println("              Test Name : Create Lead");
		System.out.println("============================================================");

	}

}