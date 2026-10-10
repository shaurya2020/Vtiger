package crm_reop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OppertunityPage {
	public OppertunityPage (WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Opportunities")
	private WebElement link;

	public WebElement getLink() {
		return link;
	}

	public WebElement getMod() {
		return mod;
	}

	public WebElement getOppName() {
		return OppName;
	}

	public WebElement getClick() {
		return click;
	}

	public WebElement getAmount() {
		return amount;
	}

	public WebElement getOpport_type() {
		return opport_type;
	}

	public WebElement getClosingdate() {
		return closingdate;
	}

	public WebElement getAssigned() {
		return Assigned;
	}

	public WebElement getSales() {
		return Sales;
	}

	public WebElement getProbability() {
		return Probability;
	}

	public WebElement getClickbut() {
		return clickbut;
	}



	@FindBy(css = "img[alt='Create Opportunity...']")
	private WebElement mod;
	
	@FindBy(name = "potentialname")
	private WebElement OppName;
	
	@FindBy(css = "img[src='themes/softed/images/select.gif']")
	private WebElement click;
	
	@FindBy(name = "amount")
	private WebElement amount;
	
	@FindBy(name = "opportunity_type")
	private WebElement opport_type;
	
	@FindBy(name = "closingdate")
	private WebElement closingdate;
	
	@FindBy(name = "assigned_user_id")
	private WebElement Assigned;
	
	@FindBy(name = "sales_stage")
	private WebElement Sales;
	
	@FindBy(id = "probability")
	private WebElement Probability;
	
	@FindBy(name = "button")
	private WebElement clickbut;
	
	
}
