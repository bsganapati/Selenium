package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;

public class FlightFinderPage extends MainBase{
	
	/**
	 * This class is related to Flight Finder Page
	 *
	 */
	
private static final Logger logger = Logger.getLogger(RegisterPage.class.getName());
	
	/**
	 * WebElement declaration
	 */
	
	public FlightFinderPage initElements() {
		return PageFactory.initElements(driver, FlightFinderPage.class);
	}
	
		@FindBy(xpath="//input[@value='oneway']")
		private WebElement radiobtnType;
		@FindBy(xpath="//select[@name='passCount']")
		private WebElement selectPassenger;
		@FindBy(xpath="//select[@name='fromPort']")
		private WebElement selectDepartFrom;
		@FindBy(xpath="//select[@name='fromMonth']")
		private WebElement selectMonth;
		@FindBy(xpath="//select[@name='fromDay']")
		private WebElement selectDay;
		@FindBy(xpath="//select[@name='toPort']")
		private WebElement selectArriving;
		@FindBy(xpath="//select[@name='toMonth']")
		private WebElement selectReturnMonth;
		@FindBy(xpath="//select[@name='toDay']")
		private WebElement selectReturnDay;
		@FindBy(xpath="//input[@value='First']")
		private WebElement btnServiceClass;
		@FindBy(xpath="//select[@name='airline']")
		private WebElement selectAirline;
		@FindBy(xpath="//input[@name='findFlights']")
		private WebElement btnContinue;
	




/**
 * Select Flight Details
 */

public void enterDetails()


{
	FlightFinderPage objFlightFinderPage = new FlightFinderPage();
	
	logger.info("Select Flight Details");
	reporterTest.log(Status.INFO, "Select Flight Details");
	radiobtnType.click();
	btnContinue.click();	
	
}

/**
 * Verifying Flight Finder page
 */
public void verifyFlightPage() {
	 reporterTest.log(Status.INFO, "Verify Flight Finder page title");
	String title= driver.getTitle();
	Assert.assertEquals("Find a Flight: Mercury Tours:", title.trim());
}

}
