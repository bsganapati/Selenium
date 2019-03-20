package com.mercurytours.testcases;
import java.util.HashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.mercurytours.pages.HomePage;
import com.mercurytours.pages.LoginPage;
import com.mercurytours.testbase.TestBase;
import com.mercurytours.utils.ExcelReader;

/**
 * This class is for verifying Valid and Invalid Sign in process
 * 
 *
 */

public class LoginVerificationTest extends TestBase {
	String page = "loginpage";

	/**
	 * Data provider for registration test cases
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "loginData")
	public Object[][] loginTestData() throws Exception {

		Object[][] result = new ExcelReader().getDataProviderData(excelpath, "loginpage");
		return result;
	}
	/**
	 * Test case is related to valid credentials verification
	 * @param strUsername
	 * @param strPassword
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "loginData")
	public void validSignInTest(String strUsername,String strPassword) throws InterruptedException {
		
		HomePage objHomePage=new HomePage().initElements();
		LoginPage objLoginPage= new LoginPage().initElements();
		
		System.out.println("This Test Case is related to Valid sign in");
		reporterTest=extent.createTest("Valid Sign in Test");
		reporterTest.log(Status.INFO, "Test Case execution is Started: "+"Successful Sign in Test");
		objHomePage.clickLogIn();
		objLoginPage.verifyLoginPage();
		objLoginPage.enterCredentials(strUsername, strPassword);
			
	}	
	
	 
	
}
