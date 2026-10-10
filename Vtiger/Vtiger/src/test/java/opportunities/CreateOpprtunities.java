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
import genric_utility.WebDriverUtility;

public class CreateOpprtunities extends baseTest {
	@Test
	public void Oppertunity() throws IOException, ParseException {
		
		OppertunityPage OpP = new OppertunityPage(driver);
		WebElement module = OpP.getLink();
		module.click();

		WebElement mod = driver.findElement(
				By.cssSelector("img[alt='Create Opportunity...']"));
		mod.click();

		long ran = System.currentTimeMillis();
		String zd = "Zudio" + ran;
		WebElement OppName = driver.findElement(By.name("potentialname"));
		OppName.sendKeys(zd);
		
		driver.findElement(
				By.cssSelector("img[src='themes/softed/images/select.gif']"))
				.click();

		String PID = driver.getWindowHandle();
		
		WebDriverUtility wdutil = new WebDriverUtility(driver);
		wdutil.switchToWindowByTitle("sd");
		
		driver.findElement(By.id("1")).click();
		driver.switchTo().window(PID);
		
		String amt = "5000";
		WebElement amount = driver.findElement(By.name("amount"));
		amount.sendKeys(amt);
		
		String opport_type = "New Business";
		WebElement ss = driver.findElement(By.name("opportunity_type"));
		Select singleselect = new Select(ss);
		
		singleselect.selectByValue(opport_type);
		WebElement date = driver.findElement(By.name("closingdate"));
		date.sendKeys("2026/09/18");
		
		String ldd = "Partner";
		WebElement lead = driver.findElement(By.name("leadsource"));
		Select ld = new Select(lead);
		ld.selectByValue(ldd);
		
		WebElement Assigned = driver.findElement(By.name("assigned_user_id"));
		Select As = new Select(Assigned);
		As.selectByValue("1");
		
		WebElement Sales = driver.findElement(By.name("sales_stage"));
		Select sl = new Select(Sales);
		sl.selectByValue("Perception Analysis");
		
		String PID2 = driver.getWindowHandle();

		driver.findElement(
				By.xpath("//input[@name='campaignname']/following-sibling::img[@alt='Select']"))
				.click();

		WebDriverUtility webUtility = new WebDriverUtility(driver);
		webUtility.switchToWindowByTitle("User Conference");
		driver.switchTo().window(PID2);
		
		WebElement Probability = driver.findElement(By.id("probability"));
		Probability.sendKeys("88");
		
		driver.findElement(By.name("button")).click();
	
		String Vopptname = driver.findElement(By.cssSelector("[id='dtlview_Opportunity Name']")).getText();
		String actualopport_type = driver.findElement(By.id("dtlview_Type"))
				.getText();
		Assert.assertEquals(Vopptname,zd); 
			Assert.assertEquals(actualopport_type,opport_type); 
}
}
