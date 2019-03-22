package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;

/**
 * This class is related to Home Page
 *
 */
public class HomePage extends MainBase {

	private static final Logger logger = Logger.getLogger(HomePage.class.getName());
/**
 * WebElement declaration
 */
	public HomePage initElements() {
		return PageFactory.initElements(driver, HomePage.class);
	}
 
	@FindBy(xpath = "//a[contains(text(),'SIGN-ON')]")
	private WebElement lnkLoginIn;
	 
	
	
	
 /**
  * Method is related to log in click
  */
	 public void clickLogIn() {
		 logger.info("Click on Log In link");
		 reporterTest.log(Status.INFO, "Click on Log In link");
		 lnkLoginIn.click();
	 }
	 
	 
}
