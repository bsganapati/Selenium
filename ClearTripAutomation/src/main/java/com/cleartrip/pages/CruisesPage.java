package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;

/**
 * This class is related to Cruises page
 *
 */

public class CruisesPage extends MainBase{
	
	private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	/**
	 * WebElement declaration
	 */
	
	public CruisesPage initElements() {
		return PageFactory.initElements(driver, CruisesPage.class);
	}
	
	
	@FindBy(xpath="//a[contains(text(),'Cruises')]")
	private WebElement linkCruises;
	
	
	/**
	  * Method is related to Cruises click
	  */
		 public void clickCruises() {
			 logger.info("Click on Cruises link");
			 reporterTest.log(Status.INFO, "Click on Cruises link");
			 linkCruises.click();
		 }
		 
	 /**
	  * Verify Cruises Page
	  */
		 
		 
		 public void verifyCruisesPage()
		 {
			 	reporterTest.log(Status.INFO, "Verify Cruises page title");
				String title= driver.getTitle();
				Assert.assertEquals("Cruises: Mercury Tours", title.trim());
		 }
	

}
