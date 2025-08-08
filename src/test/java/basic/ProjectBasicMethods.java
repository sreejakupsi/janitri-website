package basic;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.Utility;

public class ProjectBasicMethods extends Utility{
	 
	@BeforeSuite
	public void reportInitilization() {
		String path= System.getProperty("user.dir")+"\\report";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		
		//to capture test data
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);	
	}
	
	
	@BeforeClass
	public void testDetails() {
		test=extent.createTest(testName,testDescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	}
	
	@BeforeMethod
	public void browserLaunch() {
		launchBrowser();
	}
	
	@AfterMethod
	public void browserClose() {
		closeBrowser();
	}
	
	@DataProvider(name="readData")
	public String[][] readData() throws IOException{
		String[][] data=readExcel(sheetName);
		return data;
	}
	
	@AfterSuite
	public void closeReport() {
		extent.flush();
	}
}
