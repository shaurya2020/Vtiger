package opportunities;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base_test.baseTest;
import crm_reop.OppertunityPage;
import crm_reop.VerifyOppertunityPage;
import genric_utility.WebDriverUtility;

public class CreateOpprtunities extends baseTest {
	@Test
	public void Oppertunity() throws IOException, ParseException {

		long ran = System.currentTimeMillis();
		String Persentage = "88%";
		String opport_type = "New Business";
		String zd = "Zudio" + ran;
		String amt = "5000";
		String ldd = "Partner";
		String cloasing_Date = "2026/09/18";
		
		VerifyOppertunityPage VOpp = new VerifyOppertunityPage(driver);
		OppertunityPage OpP = new OppertunityPage(driver);
		OpP.getmoduleLink().click();
		OpP.getmodule().click();
		OpP.getOppName().sendKeys(zd);

		driver.findElement(By.cssSelector("img[src='themes/softed/images/select.gif']")).click();

		String PID = driver.getWindowHandle();

		WebDriverUtility wdutil = new WebDriverUtility(driver);
		wdutil.switchToWindowByTitle("sd");

		driver.findElement(By.id("1")).click();
		driver.switchTo().window(PID);

		OpP.getAmount().sendKeys(amt);


		WebElement ss = OpP.getOpport_type();

		Select singleselect = new Select(ss);
		singleselect.selectByValue(opport_type);

		OpP.getClosingdate().sendKeys(cloasing_Date);

		WebElement lead = OpP.getleadsource();
		Select ld = new Select(lead);
		ld.selectByValue(ldd);

		WebElement Assigned = OpP.getAssigned();
		Select As = new Select(Assigned);
		As.selectByValue("1");

		WebElement Sales = OpP.getSales();
		Select sl = new Select(Sales);
		sl.selectByValue("Perception Analysis");

		String PID2 = driver.getWindowHandle();

		driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img[@alt='Select']")).click();

		WebDriverUtility webUtility = new WebDriverUtility(driver);
		webUtility.switchToWindowByTitle("User Conference");
		driver.switchTo().window(PID2);

		OpP.getProbability().sendKeys(Persentage);
		OpP.getClickbut().click();

		String Vopptnames = VOpp.getVopptname().getText();
		String actualopport_type = VOpp.getOpport_type().getText();
		Assert.assertEquals(Vopptnames, zd);
		Assert.assertEquals(actualopport_type, opport_type);
	}
}
