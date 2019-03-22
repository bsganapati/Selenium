package com.cleartrip.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cleartrip.pages.CruisesPage;
import com.cleartrip.pages.HomePage;
import com.cleartrip.pages.LoginPage;
import com.cleartrip.testbase.TestBase;

public class CruisesVerificationTest extends TestBase {
	
	
	@Test
	public void validSignInTest() throws InterruptedException {
		
		CruisesPage objCruisesPage=new CruisesPage().initElements();
		
		
		System.out.println("This Test Case is related to click on Cruises ");
		reporterTest=extent.createTest("Valid Cruises Test");
		reporterTest.log(Status.INFO, "Test Case execution is Started: "+"Successful click on cruises Test");
		objCruisesPage.clickCruises();
		objCruisesPage.verifyCruisesPage();
	}	
	

}
