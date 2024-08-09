package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import saloni.projectfarmework.checkoutpage;
import saloni.projectfarmework.orderPage;
import saloni.projectfarmework.product;

public class abstractcomponents{
	
	WebDriver driver;
	checkoutpage checkoutpage;
	orderPage orderpage;
	
	public abstractcomponents(WebDriver driver) {
		
		this.driver =driver;
		
	}
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement cartheader; 
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement orderheader;

	public void waitforelementtoapper(By findBy) {
		
		
	 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(6)) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	
	}
	public void waitforwebelementtoapper(WebElement findBy) {
		
		
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(6)) ;
			wait.until(ExpectedConditions.visibilityOf(findBy));
			
		
		}
	public void waitforelementtodisapper(WebElement ele) {
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(6)) ;
			wait.until(ExpectedConditions.invisibilityOf(ele));
		 
	}
	public checkoutpage gotocartpage() {
		cartheader.click();
		return new checkoutpage(driver);
		
	}
	public orderPage gotoorderspage() {
		orderheader.click();
		orderPage orderpage =new orderPage(driver);
		return  orderpage;
		

} }
