package com.mercurytours.testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mercurytours.mainbase.MainBase;

public class TestBase extends MainBase {
	public static String pagevalue;
	public static HashMap<String, String> taxData = null;
	public static HashMap<String, String> nsVerificationData = null;

	public static String screenshotFolderpath = System.getProperty("user.dir") + "\\Screenshots\\";
	public static String screenshotFoldername = "Execution_"
			+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	private static final Logger LOGGER = Logger.getLogger(TestBase.class);

	@AfterMethod(alwaysRun = true)
	public void afterTestMethod(ITestResult result) throws IOException {
		captureScreenShotOnTestFailure(result);
		LOGGER.info("***********************************************************");
		LOGGER.info(" End of Execution ");
		LOGGER.info("***********************************************************");
		 reporterTest.log(Status.INFO, "Test Case execution is completed");
	}
@AfterClass
public void afterclassMethod() {
	closeDriver();
}
	@AfterSuite
	public void afterTestSuite() {
		 extent.flush();
		
		LOGGER.info("***********************************************************************************");
		LOGGER.info("*** End of Suite Execution: ");
		LOGGER.info("***********************************************************************************");
		
		
		File srcfile = new File(MainBase.targetReportPath);
		
		      
		      File dstfile = new File(MainBase.path);
				try {
					FileUtils.copyDirectory(srcfile, dstfile);
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
				 
				System.out.println("Report movement is completed. Path is: "+MainBase.path);
		
	 
	}

	public void captureScreenShotOnTestFailure(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE)
        {
			File path = new File(MainBase.targetReportPath + "//Screenshots//");
			if (!path.exists()) {
				try {
					path.mkdir();
				} catch (Exception e) {
					e.printStackTrace();
				}

				LOGGER.info("Path is " + path);
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenShotFileName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".jpg";
			
			String screenShotPath = MainBase.targetReportPath + "//Screenshots//"+screenShotFileName;
			LOGGER.info("Screenshot path " + screenShotPath);
			try {
				FileUtils.copyFile(screenshotFile, new File(screenShotPath));
			} catch (IOException e) {

				e.printStackTrace();
			}
		
			reporterTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
			reporterTest.fail(result.getThrowable());
			reporterTest.fail("Snapshot below: " + reporterTest.addScreenCaptureFromPath("."+"\\Screenshots\\"+screenShotFileName));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
        	reporterTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
        	reporterTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
        	reporterTest.skip(result.getThrowable());
        }
	 

	}

	public void closeDriver() {
		if (getDriver() != null) {
			try {
				LOGGER.info("Trying to Stop WebDriver");
				getDriver().quit();
				LOGGER.info("WebDriver Stopped");
			} catch (Exception e) {
				LOGGER.error("Error in stopping WebDriver " + e.getMessage());
			}
		}
	}
	
	

}
