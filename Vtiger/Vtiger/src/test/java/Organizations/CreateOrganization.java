package Organizations;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_test.baseTest;
import crm_reop.OrgPage;
import crm_reop.VeryOrgPage;
import genric_utility.FileUtility;
import genric_utility.JavaUtility;
import genric_utility.WebDriverUtility;

public class CreateOrganization extends baseTest {

	@Test
	public void createOrg() throws EncryptedDocumentException, IOException  {
		
		ExtentTest test = report.createTest("createOrg");

		JavaUtility jd = new JavaUtility();
		int zs = jd.generateRandomNumber(1000);
		
		String accountName = FileUtility.GetDataExcellFile("ORGname", 4, 0) + zs;
		String email = FileUtility.GetDataExcellFile("ORGname", 5, 4);
		String cs = FileUtility.GetDataExcellFile("ORGname", 1, 2);
		String Num = FileUtility.GetDataExcellFile("ORGname", 3, 3);
		String sell = FileUtility.GetDataExcellFile("ORGname", 4, 1);
		
		OrgPage Og = new OrgPage(driver);

		Og.getLink().click();
		WebElement mod = Og.getCss();
		mod.click();
		Og.getAccname().sendKeys(accountName);
		Og.getPhone().sendKeys(Num);
		Og.getEmail().sendKeys(email);

		WebElement Ind = Og.getIndustry();
		WebDriverUtility sg = new WebDriverUtility(driver);
		sg.select(Ind, sell);

		WebElement Type = Og.getAccounttype();
		WebDriverUtility Tys = new WebDriverUtility(driver);
		Tys.select(Type, cs);
		Og.getButton().click();
		
		VeryOrgPage os = new VeryOrgPage(driver);
		String VOrgNam = os.getOrgname().getText();
		String Vphone = os.getPhone().getText();
		String VEmail = os.getEmail().getText();
		String VInd = os.getIndustery().getText();
		String Vtype = os.getType().getText();

		Assert.assertEquals(VOrgNam, accountName);
		Assert.assertEquals(Vphone, Num);
		Assert.assertEquals(VEmail, email);
		Assert.assertEquals(VInd, sell);
		Assert.assertEquals(Vtype, cs);
		test.log(Status.PASS, "Org Test is passed");
	}
}
