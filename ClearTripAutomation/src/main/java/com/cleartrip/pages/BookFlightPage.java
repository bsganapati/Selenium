package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;


/**
 * This class is related to Book Flight Page 
 *
 */

public class BookFlightPage extends MainBase {

	private static final Logger logger = Logger.getLogger(BookFlightPage.class.getName());
	/**
	 * WebElement declaration
	 */
		public BookFlightPage initElements() {
			return PageFactory.initElements(driver, BookFlightPage.class);
		}
		
		
		@FindBy(xpath="//input[@name='passFirst0']")
		private WebElement txtFirstName;
		@FindBy(xpath="//input[@name='passLast0']")
		private WebElement txtLastName;
		@FindBy(xpath="//select[@name='creditCard']")
		private WebElement selectCardType;
		@FindBy(xpath="//input[@name='creditnumber']")
		private WebElement selectCardNumber;
		@FindBy(xpath="//input[@name='buyFlights']")
		private WebElement btnPurchase;
		
		
		
		/**
		  * Book Flights
		  */
			 public void bookFlights(String username,String password) {
				 logger.info("Book Flights");
				 reporterTest.log(Status.INFO, "Book Flights");
				 
				
				 
			 }
			 /**
			  * Verifying Book Flight page
			  */
			 public void verifySelectFlightPage() {
				 reporterTest.log(Status.INFO, "Verify Book Flight page title");
				String title= driver.getTitle();
				Assert.assertEquals("Book a Flight: Mercury Tours", title.trim());
			 }
		
		
}
