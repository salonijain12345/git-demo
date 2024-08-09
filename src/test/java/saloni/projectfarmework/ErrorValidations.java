package saloni.projectfarmework;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import saloni.testcomponents.basetest;

public class ErrorValidations extends basetest{
	
	@Test(groups={"ErrorHandling"})
	public  void  errorlogin() throws IOException, InterruptedException  {
		
	String productname ="ZARA COAT 3";
	
	
	
	product product = landingpage.loginApplication("saloni1799@gmail.com", "Iking@000");
	//Assert.assertEquals("Incorrect email or password.",landingpage.getErrormessage());
	

}
	//(retryAnalyzer =saloni.testcomponents.Retry.class ,enabled =false)
	@Test(retryAnalyzer =saloni.testcomponents.Retry.class ,enabled =true)
	public  void  errorproduct() throws IOException, InterruptedException  {
		
	String productname ="ZARA COAT 3";
	
	
	
	product product = landingpage.loginApplication("saloni170899@gmail.com", "Iamking@000");

	//product product =new product(driver);
	List<WebElement> products=product.getproductlist();
	
	product.addproducttocart(productname);
	
	checkoutpage checkoutpage = product.gotocartpage();

	Boolean match = product.VerifyOrderDisplay("ZARA COAT 3");
	Assert.assertTrue(match);
	
	

}



}
