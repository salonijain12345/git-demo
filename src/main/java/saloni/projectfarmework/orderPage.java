package saloni.projectfarmework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractcomponents.abstractcomponents;

public class orderPage extends abstractcomponents{

	WebDriver driver;
	
	public orderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public boolean verifyorderdisplay(String productname)
	{
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;
	}
    
	
}
