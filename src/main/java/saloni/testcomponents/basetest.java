package saloni.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import saloni.projectfarmework.landingpage;

  
  
public class basetest {
	public WebDriver driver;
	public landingpage landingpage;
	
	public  WebDriver initializeDriver() throws IOException
	{
		//Properties prop =new Properties();
		//FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+" //src//main//java//saloni//resources//GlobalData.properties");
		//prop.load(fis);
		
		//String browserName =prop.getProperty("browser");
		
//		ChromeOptions chromeOption =new ChromeOptions();
//		chromeOption.addArguments("--remote-allow-origins=*");
//		WebDriver driver = new ChromeDriver(chromeOption);
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		return driver;
//		
		 Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "//src//main//java//saloni//resources//GlobalData.properties");
			prop.load(fis);
			
			String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
			//prop.getProperty("browser");
				System.out.println(browserName);
			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")){
				options.addArguments("headless");
				}		
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));//full screen

			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"D:\\selenium\\geckodriver");
				driver = new FirefoxDriver();
				// Firefox
			} else if (browserName.equalsIgnoreCase("edge")) {
				// Edge
				System.setProperty("webdriver.edge.driver", "C:\\Users\\saloni jain\\eclipse-workspace\\edgedriver_win64\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	        driver.manage().window().maximize();
     		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		String jsoncontent=FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//string to hashmap jackson databind
		
		ObjectMapper mapper =new ObjectMapper();
		
		List<HashMap<String ,String>> data =mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String ,String>>>(){});
		
		
		return data;
		
	}

	@BeforeMethod(alwaysRun=true)
	public landingpage launchApplication()throws IOException
	{
		driver =initializeDriver();
		 landingpage =new landingpage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();

	}
	
	public String getscreenshot(String testcasename,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		}
	

}
