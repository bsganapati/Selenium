package com.cleartrip.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cleartrip.mainbase.MainBase;

/**
 * This class is related to Register Page
 *
 */

public class RegisterPage extends MainBase {
	
	private static final Logger logger = Logger.getLogger(RegisterPage.class.getName());
	
	/**
	 * WebElement declaration
	 */
	
	public RegisterPage initElements() {
		return PageFactory.initElements(driver, RegisterPage.class);
	}
	
	@FindBy(xpath="//a[contains(text(),'REGISTER')]")
	private WebElement btnRegister;
	@FindBy(xpath="//input[@name='firstName']")
	private WebElement txtFirstName;
	@FindBy(xpath="//input[@name='lastName']")
	private WebElement txtLastName;
	@FindBy(xpath="//input[@name='phone']")
	private WebElement txtPhone;
	@FindBy(xpath="//input[@id='userName']")
	private WebElement txtEmail;
	@FindBy(xpath="//input[@name='address1']")
	private WebElement txtAddress;
	@FindBy(xpath="//input[@name='city']")
	private WebElement txtCity;
	@FindBy(xpath="//input[@name='state']")
	private WebElement txtState;
	@FindBy(xpath="//input[@name='postalCode']")
	private WebElement txtPostalCode;
	@FindBy(xpath="//select[@name='country']")
	private WebElement selectCountry;
	@FindBy(xpath="//input[@name='email']")
	private WebElement txtUserName;
	@FindBy(xpath="//input[@name='password']")
	private WebElement txtPassword;
	@FindBy(xpath="//input[@name='confirmPassword']")
	private WebElement txtConfirmPassword;
	
	
	/**
	  * Enter Details
	  */
	
	public void enterDetails(String fname, String lname, String phone, String email, String address, String city, String state,
			String postalCode, String country, String username, String password, String confirmpassword)
	
	
	{
		RegisterPage objRegisterPage = new RegisterPage();
		logger.info("Enter Details");
		reporterTest.log(Status.INFO, "Enter the Details");
		
		btnRegister.click();
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
		txtPhone.sendKeys(phone);
		txtEmail.sendKeys(email);
		txtAddress.sendKeys(address);
		txtCity.sendKeys(city);
		txtState.sendKeys(state);
		txtPostalCode.sendKeys(postalCode);
		objRegisterPage.setDropdownValue(selectCountry, country, country);
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		txtConfirmPassword.sendKeys(confirmpassword);
		
	}
	
	
	/**
	  * Verifying Register page
	  */
	 public void verifyRegisterPage() {
		 reporterTest.log(Status.INFO, "Verify Register page title");
		String title= driver.getTitle();
		Assert.assertEquals("Register: Mercury Tours", title.trim());
	 }
	
	
}
