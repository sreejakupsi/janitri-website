package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class Utility {

	public static WebDriver driver;
	public String sheetName;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testName,testDescription,testCategory,testAuthor;
	
	
	public void launchBrowser() {
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	    driver.manage().window().maximize();
	    driver.get("https://dev-dash.janitri.in/");
	}



	
	//To close the browser
	public void closeBrowser() {
		driver.quit();
	}
	
	//To retrive data from excel sheet
	public static String[][] readExcel(String sheetName) throws IOException {
		if(sheetName == null || sheetName.isEmpty()) {
			throw new RuntimeException("The sheet is null or empty");	
		}
		
		try {
			String path= System.getProperty("user.dir") +"\\data\\Janitri data.xlsx";
			FileInputStream fil = new FileInputStream(path);
			
			XSSFWorkbook book = new XSSFWorkbook(fil);
			XSSFSheet sheet = book.getSheet(sheetName);
			if(sheet==null) {
				throw new RuntimeException("Sheet:" + sheetName + "not found in excel file");
			}
			int rowcount= sheet.getLastRowNum();
			short columncount=sheet.getRow(0).getLastCellNum();
			
			String[][] data= new String[rowcount][columncount];
			for(int i=1;i<=rowcount;i++) {
				XSSFRow row= sheet.getRow(i);
				if(row==null) continue;
				
				for(int j=0;j<columncount;j++) {
					XSSFCell cell =row.getCell(j);
					data[i-1][j]=getCellvalue(cell);
				}
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getCellvalue(Cell cell) {
		if(cell==null) {
			return "";
		}
		
		switch(cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			}else {
				return String.valueOf((long)cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return String.valueOf(cell.getCellFormula());
		default:
			return "";
		}
	}
	
	public static String screenshot(String name) throws IOException {
	    String folderPath = System.getProperty("user.dir") + "\\screenshort\\";
	    new File(folderPath).mkdirs(); // Create folder if not exists

	    String path = folderPath + name + ".png";
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File dest = new File(path);
	    FileUtils.copyFile(src, dest);

	    return path;
	}

	
}
