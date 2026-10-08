package leads;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_test.baseTest;
import crm_reop.Leadspage;
import crm_reop.VeryLeadPage;
import genric_utility.FileUtility;
import genric_utility.WebDriverUtility;

public class createLead extends baseTest {
	@Test
	public void createlead() throws EncryptedDocumentException, IOException {
		
		ExtentTest test = report.createTest("createLead");

		String LastName = FileUtility.GetDataExcellFile("Lead", 4, 0);
		String CompanyName = FileUtility.GetDataExcellFile("Lead", 4, 1);
		String Num = FileUtility.GetDataExcellFile("Lead", 4, 4);
		String Emp = FileUtility.GetDataExcellFile("Lead", 4, 2);
		String IndSel = FileUtility.GetDataExcellFile("Lead", 4, 3);

		Leadspage lp = new Leadspage(driver);
		lp.getModule().click();
		lp.getMod().click();

		lp.getLastname().sendKeys(LastName);
		lp.getCompany().sendKeys(CompanyName);

		WebDriverUtility lsp = new WebDriverUtility(driver);
		WebElement lead = lp.getLead();
		lsp.select(lead, Emp);
		
		WebElement Ind = lp.getInd();
		lsp.select(Ind, IndSel);
		
		lp.getPhone().sendKeys(Num);
		lp.getButt().click();

		VeryLeadPage vlp = new VeryLeadPage(driver);
		String VLastName = vlp.getLastname().getText();
		String Vcompaney = vlp.getCompany().getText();
		String VleadS = vlp.getLeads().getText();
		String VInd = vlp.getInd().getText();
		String VNum = vlp.getNum().getText();

		Assert.assertEquals(LastName, VLastName);
		Assert.assertEquals(CompanyName, Vcompaney);
		Assert.assertEquals(Emp, VleadS);
		Assert.assertEquals(IndSel, VInd);
		Assert.assertEquals(Num, VNum);
		test.log(Status.PASS, "Lead page  is passed");
	}

}