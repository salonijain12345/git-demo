package saloni.projectfarmework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saloni.testcomponents.basetest;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class submitordertest extends basetest {
	
	//jenkins java -jar jenkins.war --enable-future-java --httpPort=xxxx 

	@Test(dataProvider ="getData", groups="purchase",retryAnalyzer =saloni.testcomponents.Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		//String productname = "ZARA COAT 3";

		product product = landingpage.loginApplication(input.get("email"),input.get("password"));

		// product product =new product(driver);
		List<WebElement> products = product.getproductlist();

		product.addproducttocart(input.get("productname"));
		checkoutpage checkoutpage = product.gotocartpage();

		// Boolean match = product.VerifyOrderDisplay(input.get("productname"));
		// Assert.assertTrue(match);

		checkoutpage.checkoutfromcart();
		checkoutpage.selectcountry();
		checkoutpage.submit();

		String confirmmsg = driver.findElement(By.className("hero-primary")).getText();
		AssertJUnit.assertEquals(confirmmsg, "THANKYOU FOR THE ORDER.");

	}

	@Test(dataProvider ="getData",dependsOnMethods = { "submitOrder" }, groups="purchase",retryAnalyzer =saloni.testcomponents.Retry.class)
	public void orderhistorytest(HashMap<String,String> input) throws InterruptedException {

		//String productname = "ZARA COAT 3";

		product product = landingpage.loginApplication(input.get("email"),input.get("password"));
		orderPage orderPage = landingpage.gotoorderspage();
		AssertJUnit.assertTrue(orderPage.verifyorderdisplay(input.get("productname")));

	}
	
	

	//@DataProvider String email,String password ,String productname
	//public Object[][] getData() {
		//return new Object[][] { { "anshika@gmail.com", "Iamking@000" ,"ZARA COAT 3"}, { "saloni1799@gmail.com", "Iamking@000" ,"ADIDAS ORIGINAL"} };
	//}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{  
//		HashMap<String,String> map =new HashMap<String,String>();
//		map.put("email","anshika@gmail.com");
//		map.put("password","Iamking@000");
//		map.put("productname","ZARA COAT 3");
//		HashMap<String,String> map1 =new HashMap<String,String>();
//		
//		map1.put("email","saloni1799@gmail.com");
//		map1.put("password","Iamking@000");
//		map1.put("productname","ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//saloni//data//purchaseorder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
	}

}
