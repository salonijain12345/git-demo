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

public class project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String productname ="ZARA COAT 3";
		ChromeOptions chromeOption =new ChromeOptions();
		chromeOption.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(chromeOption);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//landingpage page=new landingpage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div[2]/div[1]/div/div/h5/b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div[2]/div[1]/div/div/button[2]/i")).click();
		
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(6)) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-sidebar/nav/ul/li[4]/button")).click();
		
		List<WebElement> cartproducts =driver.findElements(By.xpath("/html/body/app-root/app-profile/div/div[2]/ul[1]/li/div/div[1]/h3"));
		
		
		Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("/html/body/app-root/app-profile/div/div[3]/ul/li[3]/button")).click();
		
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]")));
		driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button[2]")).click();
		
		driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")).click();
		String confirmmsg =driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(confirmmsg, "THANKYOU FOR THE ORDER.");
		driver.close();
	}

}
