package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basic.ProjectBasicMethods;
import pages.LoginPage;

public class TC_003UITest extends ProjectBasicMethods {

	@BeforeTest
	public void setup() {
	    sheetName = "Valid login";
	    testName = "UI Test - Password Masking";
	    testDescription = "Verify password masking/unmasking";
	    testAuthor = "Sreeja";
	    testCategory = "UI Testing";
	}

	@Test(dataProvider = "readData")
	public void checkPasswordMaskingAndUnmasking(String userId, String password) {
		LoginPage obj= new LoginPage(driver);
		obj.loginDetails(userId, password);
		obj.printPasswordMaskingStates();
		obj.pageTitle();
		obj.CheckFirstLogo();
		obj.CheckSecondLogo();
		obj.checkIcon();
		obj.checkLoginBtn();
		obj.URLCheck();

	}
}
