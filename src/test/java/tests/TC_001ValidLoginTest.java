package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basic.ProjectBasicMethods;
import pages.LoginPage;

public class TC_001ValidLoginTest extends ProjectBasicMethods {

	//Valid credentialds are not given
	@BeforeTest
	public void setUp() {
		sheetName="Valid Login";
		testName="ValidLogin Test";
		testDescription="Testing the login functionality with positive cases";
		testAuthor="Sreeja";
		testCategory="Smoke Testing";
	}
	
	@Test(dataProvider = "readData")
	public void validLogin(String userID, String password) {
		LoginPage obj= new LoginPage(driver);
		obj.loginDetails(userID, password);
	}
}
