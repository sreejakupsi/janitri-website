package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basic.ProjectBasicMethods;
import io.netty.handler.timeout.TimeoutException;

public class LoginPage extends ProjectBasicMethods {
	
	WebDriver driver;
	
	@FindBy(id="formEmail")
	WebElement userIdField;
	
	@FindBy(id="formPassword")
	WebElement passwordField;
	
	@FindBy(className ="login-button")
	WebElement loginBtn;
	
	@FindBy(className = "normal-text")
	WebElement errorMsg;
	
	@FindBy(className = "passowrd-visible")
	WebElement passwordIcon;
	
	@FindBy(className ="login-janitri-logo")
	WebElement title;
	
	@FindBy(xpath = "//img[@class='left-corner-login-image' and @alt='CDSCO']")
	WebElement firstLogo;
	
	@FindBy(xpath = "//img[@class='left-corner-login-image' and @alt='ISO ']")
	WebElement secondLogo;
	
	@FindBy(className = "passowrd-visible")
	WebElement icon;
	
	@FindBy(css = ".logo-track")
	WebElement logoTrack;
	
	@FindBy(css = ".logo-track .logo-item")
	List<WebElement> logos;

	
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//login details
	public void loginDetails(String userID, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.visibilityOf(userIdField));
	    if (userID != null) {
	        userIdField.sendKeys(userID);
	    } else {
	        userIdField.sendKeys(""); // Send empty string instead of null
	    }

	    if (password != null) {
	        passwordField.sendKeys(password);
	    } else {
	        passwordField.sendKeys(""); // Send empty string instead of null
	    }

	    loginBtn.click();
	}
	
	//error message for invalid login
	public String getErrorMsg() {
		 return errorMsg.getText();
		 
	}
	public void printPasswordMaskingStates() {
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		
		String password=passwordField.getAttribute("value");
		String typeBefore= passwordField.getAttribute("type");
		
		
		if("password".equals(typeBefore)) {
			System.out.println("Before clicking the icon: "+ ".".repeat(password.length()));
		}
		else {
			System.out.println("Before clciking the icon: "+ password);
		}
		
		  WebElement eyeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("passowrd-visible")));
		    try {
		        eyeIcon.click();
		    } catch (ElementClickInterceptedException e) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", eyeIcon);
		    }
		
		wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(passwordField, "type", typeBefore)));
		
		String typeAfter=passwordField.getAttribute("type");
		String updatedPassword=passwordField.getAttribute("value");
		
		if("updatedPassword".equals(typeAfter)) {
			System.out.println("After clicking icon: "+ ".".repeat(updatedPassword.length()));
		}
		else {
			System.out.println("After clciking the icon: "+ updatedPassword);
		}		
}

	public void pageTitle() {
		if(title.isDisplayed()) {
			System.out.println("The page title is displayed");
		}
		else {
			System.out.println("The page title is not displayed");
		}
	}

	public void CheckFirstLogo() {
		if(firstLogo.isDisplayed()) {
			System.out.println("The first logo is displayed");
		}
		else {
			System.out.println("The first logo is not displayed");
		}
	}
	
	public void CheckSecondLogo() {
		if(secondLogo.isDisplayed()) {
			System.out.println("The second logo is displayed");
		}
		else {
			System.out.println("The second logo is not displayed");
		}
	}
	
	public void checkIcon() {
		if(icon.isDisplayed()) {
			System.out.println("The eye icon is displayed");
		}
		else {
			System.out.println("The eye icon is not displayed");
		}
	}
	
	public void checkLoginBtn() {
		if(loginBtn.isDisplayed()) {
			System.out.println("The login button is displayed");
		}
		else {
			System.out.println("The login button is not displayed");
		}
	}

	public void URLCheck() {
		String expectedUrl="https://dev-dash.janitri.in/";
		String actualUrl=driver.getCurrentUrl();
		
		if(expectedUrl.equals(actualUrl)) {
			System.out.println("URL check passed");
		}
		else {
			System.out.println("URL check failed");
		}
	}
	
	
	
}
