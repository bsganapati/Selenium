package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;
 

/**
 * This class is related to Login page
 *
 */
public class LoginPage extends MainBase {

	private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
/**
 * WebElement declaration
 */
	public LoginPage initElements() {
		return PageFactory.initElements(driver, LoginPage.class);
	}
 
	@FindBy(xpath = "//input[@name='userName']")
	private WebElement txtEmail;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement txtPassword;
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement btnLogIn;
	@FindBy(xpath = "//div[input[@name='email']]//small[@class='error']")
	private WebElement txtEmailErrorMessage;
	
	@FindBy(xpath = "//div[input[@name='password']]//small[@class='error']")
	private WebElement txtPasswordErrorMessage;
	
 /**
  * Enter credentials
  */
	 public void enterCredentials(String username,String password) {
		 logger.info("Enter credentails");
		 reporterTest.log(Status.INFO, "Enter Log in credentials");
		 txtEmail.sendKeys(username);
		 txtPassword.sendKeys(password);
		 btnLogIn.click();
		 
	 }
	 /**
	  * Verifying login page
	  */
	 public void verifyLoginPage() {
		 reporterTest.log(Status.INFO, "Verify Login page title");
		String title= driver.getTitle();
		Assert.assertEquals("Sign-on: Mercury Tours", title.trim());
	 }
 
	 
}
