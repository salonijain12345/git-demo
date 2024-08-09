package saloni.projectfarmework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractcomponents.abstractcomponents;

public class checkoutpage extends abstractcomponents{

	WebDriver driver;
	
	public checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="/html/body/app-root/app-profile/div/div[3]/ul/li[3]/button")
	WebElement checkout;
	
	@FindBy (css="[placeholder='Select Country']")
	WebElement selectcountry;
	
	@FindBy( xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")
	WebElement orderconfirm;
	
	@FindBy( xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")
	WebElement submit;
	
	//@FindBy (xpath= ""/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")
	//WebElement selectindia;
	
	By selectindia =By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]");
	
	public  void checkoutfromcart()
	{
		checkout.click();
	}
	public void selectcountry() throws InterruptedException
	{
		Actions a=new Actions(driver);
		a.sendKeys(selectcountry,"india").build().perform();
		
		waitforelementtoapper(selectindia);
		//Thread.sleep(1000);
		//FindElement 
		//selectindia.click();
		driver.findElement(selectindia).click();
	}
	public checkoutpage submit()
	{
		submit.click();
		return new checkoutpage(driver);
	}
    
	
}
