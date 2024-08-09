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


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.abstractcomponents;

public class product extends abstractcomponents{
	
	WebDriver driver;
	
	public product(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	//List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement load;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css ="/dashboard/myorders")
	WebElement orders;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	By productsBy = By.cssSelector(".mb-3");
	By addtocart =By.cssSelector(".card-body button:last-of-type");
	By tostMessage=(By.cssSelector("#toast-container"));
	
	public List<WebElement> getproductlist()
	{
		waitforelementtoapper(By.cssSelector(".mb-3"));
		return products;
		
	}
	public WebElement getproductbyname(String pn)
	{
		WebElement prod = getproductlist().stream().filter(productt->
		productt.findElement(By.cssSelector("b")).getText().equals(pn)).findFirst().orElse(null);
		return prod;
	}
	
	public void addproducttocart(String pro) throws InterruptedException {
		
		WebElement prod =getproductbyname(pro);
		prod.findElement(addtocart).click();
		//waitforelementtoapper(tostMessage);
		//waitforelementtodisapper(load);
		Thread.sleep(1000);
		
	}
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	
		
	
	
}
