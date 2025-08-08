package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basic.ProjectBasicMethods;
import pages.LoginPage;

public class TC_002InvalidLoginTest extends ProjectBasicMethods{

	
	@BeforeTest
	public void setup() {
		sheetName="Invalid Login";
		testName="InvalidLogin Test";
		testDescription="Testing the login functionality with negative cases";
		testAuthor="Sreeja";
		testCategory="Smoke Testing";
	}
	
	@Test(dataProvider = "readData")
	public void invalidLogin(String userID, String password) {
		LoginPage obj= new LoginPage(driver);
		obj.loginDetails(userID, password);
		String actualError = obj.getErrorMsg();
        System.out.println("Captured Error Message: " + actualError);
	}
}

