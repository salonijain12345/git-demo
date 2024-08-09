package saloni.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import saloni.resources.ExtentreporterNG;


public class Listeners extends basetest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent =ExtentreporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal();
	

	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		//extentTest.set(test);a
		extentTest.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS,"Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().log(Status.FAIL,"Test failed");
		extentTest.get().fail(result.getThrowable());
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}
		//screenshort
		String testcasename=result.getMethod().getMethodName();
		String filePath = null;
		try {
			
			filePath = getscreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath ,testcasename);
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
