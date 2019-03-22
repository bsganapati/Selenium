package com.cleartrip.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cleartrip.pages.HomePage;
import com.cleartrip.pages.LoginPage;
import com.cleartrip.pages.RegisterPage;
import com.cleartrip.testbase.TestBase;
import com.cleartrip.utils.ExcelReader;

/**
 * This class is for verifying Registration Process
 * 
 *
 */

public class RegistrationVerificationTest extends TestBase{
	
	@DataProvider(name = "RegisterData")
	public Object[][] RegisterTestData() throws Exception {

		Object[][] result = new ExcelReader().getDataProviderData(excelpath, "RegistrationPage");
		return result;
	}
	
	@Test(dataProvider = "RegisterData")
	public void RegistrationTest(String strFname, String strLname, String strPhone, String strEmail, String strAddress, String strCity,
			String strState,String strPostalCode, String strCountry, String strUserName, String strPassword, String strConfirmPassword ) throws InterruptedException {
		
		
		RegisterPage objRegisterPage = new RegisterPage().initElements();
		
		System.out.println("This Test Case is related to Registration");
		reporterTest=extent.createTest("Valid Registration Test");
		reporterTest.log(Status.INFO, "Test Case execution is Started: "+"Successful Registration Test");
		objRegisterPage.enterDetails(strFname, strLname,strPhone, strEmail, strAddress, strCity, strState,strPostalCode,strCountry, strUserName, strPassword, strConfirmPassword);
		objRegisterPage.verifyRegisterPage();
		
}
}
