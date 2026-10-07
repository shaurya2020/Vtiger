package Contacts;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base_test.baseTest;
import crm_reop.ContactPage;
import crm_reop.VeyContactPage;
import genric_utility.FileUtility;
import genric_utility.WebDriverUtility;


public class createContacts extends baseTest {
//	public static void main(String[] args)throws InterruptedException,IOException, ParseException {

	@Test
	public void cratecontact() throws EncryptedDocumentException, IOException  {

		ExtentTest test = report.createTest("createContact");
		
		String LastName = FileUtility.GetDataExcellFile("Contact", 4, 0);
		String Email = FileUtility.GetDataExcellFile("Contact", 4, 4);
		String asi = FileUtility.GetDataExcellFile("Contact", 4, 2);
		ContactPage ct = new ContactPage(driver);

		ct.getLink().click();
		ct.getAddicon().click();
		ct.getLastname().sendKeys(LastName);
		
		String PID2 = driver.getWindowHandle();
//		WebDriverUtility wdutil = new WebDriverUtility(driver);
//		wdutil.switchToWindowByTitle("gooduivtiger");
		
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@alt='Select']")).click();
		Set<String> CID2 = driver.getWindowHandles();
		for (String i : CID2) {
			driver.switchTo().window(i);
		}
		driver.findElement(By.id("3")).click();
		driver.switchTo().window(PID2);
		
		String emp = "Employee";
		WebElement ld = ct.getLead();
		WebDriverUtility ns = new WebDriverUtility(driver);
		ns.select(ld, emp);
		WebElement phone = ct.getPhone();
		phone.sendKeys("1234567892");
		ct.getEmail().sendKeys(Email);
		ct.getAssitance().sendKeys(asi);
		String Dis = "its is done";
		ct.getDescription().sendKeys(Dis);
		ct.getButton().click();
//		 * Fetch actual values displayed on Contact details page.
		VeyContactPage vr = new VeyContactPage(driver);
		String Vlastna = vr.getlastname().getText();
		String VEmail = vr.getEmail().getText();
		String Vlead = vr.getLead().getText();
		String VAssis = vr.getAssis().getText();
		String VDescrpt = vr.getDiscript().getText();
		// LAST NAME VERIFICATION
		Assert.assertEquals(LastName,Vlastna);
		Assert.assertEquals(Email,VEmail);
		Assert.assertEquals(Vlead,emp);
		Assert.assertEquals(asi,VAssis);
		Assert.assertEquals(VDescrpt,Dis);
		
		test.log(Status.PASS, "Contact is passed");
	}
}