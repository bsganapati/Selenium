package com.cleartrip.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cleartrip.pages.FlightFinderPage;
import com.cleartrip.pages.HomePage;
import com.cleartrip.pages.LoginPage;
import com.cleartrip.pages.RegisterPage;
import com.cleartrip.testbase.TestBase;
import com.cleartrip.utils.ExcelReader;

/**
 * This class is for verifying Flight Finding Process
 * 
 *
 */

public class FlightFinderTest extends TestBase {
	
	@DataProvider(name = "loginData")
	public Object[][] loginTestData() throws Exception {

		Object[][] result = new ExcelReader().getDataProviderData(excelpath, "loginpage");
		return result;
	}
	
	
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
	
	
		/*@DataProvider(name = "FlightFinderData")
		public Object[][] RegisterTestData() throws Exception {

			Object[][] result = new ExcelReader().getDataProviderData(excelpath, "FlightFinder");
			return result;
		}*/
		
		
		@Test(dependsOnMethods="validSignInTest")
		
		public void flightFinderTest() throws InterruptedException {
			
			FlightFinderPage objFlightFinderPage = new FlightFinderPage().initElements();	
			System.out.println("This Test Case is related to Flight Finder");
			reporterTest=extent.createTest("Flight Finder Test");
			reporterTest.log(Status.INFO, "Test Case execution is Started: "+"Successful Flight Finder Test");
			
			objFlightFinderPage.verifyFlightPage();
			objFlightFinderPage.enterDetails();
			
			
		}
		}


