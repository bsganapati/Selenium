package com.cleartrip.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cleartrip.pages.FlightFinderPage;
import com.cleartrip.pages.HomePage;
import com.cleartrip.pages.LoginPage;
import com.cleartrip.pages.SelectFlightPage;
import com.cleartrip.testbase.TestBase;
import com.cleartrip.utils.ExcelReader;

/**
 * This class is for verifying Selecting Flights
 * 
 *
 */

public class SelectFlightTest extends TestBase{
	
	
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
	
	
	@Test(dependsOnMethods="validSignInTest")
	public void selectFlightTest() throws InterruptedException {
		
		FlightFinderPage objFlightFinderPage = new FlightFinderPage().initElements();	
		SelectFlightPage objSelectFlightPage = new SelectFlightPage().initElements();
		
		System.out.println("This Test Case is related to Select Flights");
		reporterTest=extent.createTest("Select Flight Test");
		reporterTest.log(Status.INFO, "Test Case execution is Started: "+"Select Flight Test");
		objFlightFinderPage.enterDetails();
		objSelectFlightPage.verifySelectFlightPage();
		objSelectFlightPage.selectFlights();
		
	}	

}
