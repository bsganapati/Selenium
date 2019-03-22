package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;

/**
 * This class is related to Select Flight Page 
 *
 */

public class SelectFlightPage extends MainBase{
	private static final Logger logger = Logger.getLogger(SelectFlightPage.class.getName());
	/**
	 * WebElement declaration
	 */
		public SelectFlightPage initElements() {
			return PageFactory.initElements(driver, SelectFlightPage.class);
		}
		
		@FindBy(xpath = "//input[@value='Blue Skies Airlines$361$271$7:10']")
		private WebElement radiobtnDepartFlight;
		@FindBy(xpath = "//input[@value='Pangea Airlines$632$282$16:37']")
		private WebElement radiobtnReturnFlight;
		@FindBy(xpath = "//input[@name='reserveFlights']")
		private WebElement btnContinue;
		
		/**
		  * Select Flights
		  */
			 public void selectFlights() {
				 logger.info("Select Flights");
				 reporterTest.log(Status.INFO, "Select Flights");
				 radiobtnDepartFlight.click();
				 radiobtnReturnFlight.click();
				 btnContinue.click();
				 
				
				 
			 }
			 /**
			  * Verifying Select Flight page
			  */
			 public void verifySelectFlightPage() {
				 reporterTest.log(Status.INFO, "Verify Select Flight page title");
				String title= driver.getTitle();
				Assert.assertEquals("Select a Flight: Mercury Tours", title.trim());
			 }
		
		
		
		
		

}
