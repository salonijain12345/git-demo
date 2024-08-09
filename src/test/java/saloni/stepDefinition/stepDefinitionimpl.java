package saloni.stepDefinition;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import io.cucumber.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import saloni.projectfarmework.checkoutpage;
import saloni.projectfarmework.landingpage;
import saloni.projectfarmework.product;
import saloni.testcomponents.basetest;

public class stepDefinitionimpl extends basetest{
	public product product;
	public landingpage landingpage;
	@Given ("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in(String username , String Password) throws Exception
	{
		 product = landingpage.loginApplication(username,Password);
	}
	
	
	@When ("^i add product (.+) to cart$")
	public void i_add_product_to_cart(String productname) throws InterruptedException
	{
		List<WebElement> products = product.getproductlist();
        System.out.println(productname);
		product.addproducttocart(productname);
		
	}
	
	
	@And ("^checkout and submit the order$")
	public void checkout_and_submit_the_order() throws InterruptedException
	{   checkoutpage checkoutpage = product.gotocartpage();
		checkoutpage.checkoutfromcart();
		checkoutpage.selectcountry();
		checkoutpage.submit();
		
		

	}
	
       @Then("{string} message is displayed on confirmationPage")
       public void message_is_displayed_on_confirmation_page(String string)
       {
    	   String confirmmsg = driver.findElement(By.className("hero-primary")).getText();
   		AssertJUnit.assertEquals(confirmmsg, string);
   		driver.close();
   		
       }

}
