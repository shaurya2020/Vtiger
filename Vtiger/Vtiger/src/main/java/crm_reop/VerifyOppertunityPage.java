package crm_reop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOppertunityPage {
	public VerifyOppertunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpport_type() {
		return opport_type;
	}

	@FindBy(css = "[id='dtlview_Opportunity Name']")
	private WebElement Vopptname;

	@FindBy(id = "dtlview_Type")
	private WebElement opport_type;

	public WebElement getVopptname() {
		return Vopptname;
	}

}
