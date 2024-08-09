package saloni.projectfarmework;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.webdriver.*;

public class santdalonetest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        String productname ="ZARA COAT 3";
		ChromeOptions chromeOption =new ChromeOptions();
		chromeOption.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(chromeOption);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(6)) ;
		landingpage page=new landingpage(driver);
		page.goTo();
		page.loginApplication("anshika@gmail.com", "Iamking@000");
		///product
		
		//List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		product product =new product(driver);
		List<WebElement> products=product.getproductlist();
		
		product.addproducttocart(productname);
		product.gotocartpage();
		
		
		//cart
		checkoutpage copage =new checkoutpage(driver);
		
		copage.checkoutfromcart();
		copage.selectcountry();
		copage.submit();
		//List<WebElement> cartproducts =driver.findElements(By.xpath("/html/body/app-root/app-profile/div/div[2]/ul[1]/li/div/div[1]/h3"));
		
		
		//Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		//Assert.assertTrue(match);
		
		//driver.findElement(By.xpath("/html/body/app-root/app-profile/div/div[3]/ul/li[3]/button")).click();
		
		//checkout
		//Actions a=new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]")));
		//driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]")).click();
		//driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")).click();
		//String confirmmsg =driver.findElement(By.className("hero-primary")).getText();
		//Assert.assertEquals(confirmmsg, "THANKYOU FOR THE ORDER.");
		driver.close();
	}

}
