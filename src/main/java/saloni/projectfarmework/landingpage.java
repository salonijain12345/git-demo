 package saloni.projectfarmework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.abstractcomponents;

public class landingpage extends abstractcomponents{
	
	WebDriver driver;
	public product product;
	public landingpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordele;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormessage;
	public product loginApplication(String email,String password) throws InterruptedException
	{
		UserEmail.sendKeys(email);
		passwordele.sendKeys(password);
		//Thread.sleep(1000);
		submit.click();
	 product = new product(driver);
		return product;
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrormessage() {
		
		waitforwebelementtoapper(errormessage);
		return errormessage.getText();
		
	}
	
}
