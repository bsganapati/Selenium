package com.cleartrip.mainbase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 
 

public class MainBase {
	 
	public  static WebDriver driver;
	public static long id ;	 
	public static String executionType;
	public static String browserValue;
	public static String isGrid;
	public static long pageTimeOut;
	public static String url;	 
	 
	
	public static String excelpath=System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
	 
	public static String sheetname=null;
	public static String testCasename=null;
	public static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest reporterTest;
	 static DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	 static Date date = new Date();
	 public static String sReportFolderpath="Execution_"+dateFormat.format(date);
	 public static String path=System.getProperty("user.dir")+"//Reports//"+MainBase.sReportFolderpath;
	 public static String targetReportPath=System.getProperty("user.dir")+"//target//Reports//";
	 
	 private static final Logger logger = Logger.getLogger(MainBase.class);
	 
	 
	public static WebDriver getDriver()  {
	 
		return driver;
	}

	@BeforeSuite
	public   void setUpDriver() throws Exception  {
		File file= new File(targetReportPath);
		file.mkdir();
		 String filepath=targetReportPath+"//ExecutionReport.html";
			File file1= new File(filepath);
			file1.createNewFile();
		htmlReporter = new ExtentHtmlReporter(filepath);
		 extent = new ExtentReports ();		
		 htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("user", System.getProperty("user.name"));
		 extent.setSystemInfo("os", "Windows 10");
		 extent.setSystemInfo("browser", System.getProperty("browser"));
		
			File path = new File(MainBase.targetReportPath + "//Screenshots//");
			if (path.exists()) {
				File[] files = path.listFiles();
				 
				 for (File filedelete : files) {
					 filedelete.delete();
				    }
				if (path.exists()) {
					try {
						path.delete();
					 
					} catch (Exception e) {
						e.printStackTrace();
					}

					
				}
			}
 
 		 
	 }
	
	@BeforeClass
	public   void setUpWebDriver() throws Exception  {
 
			executionType=System.getProperty("executeOn");
			browserValue=System.getProperty("browser");
			isGrid=System.getProperty("remoteExecution");			 
			pageTimeOut=Long.parseLong(System.getProperty("pageWaitAndWaitTimeOut"));
		 
			browserValue=browserValue.toLowerCase();	
 
			if(executionType.equalsIgnoreCase("local")) {
			switch (browserValue.trim()) {
			case "chrome":
			 
				System.out.println("chromedriver location "+System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				 
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches", "--disable-extensions");
				DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
	 				HashMap<String, Object> chromePrefs = new HashMap<String, Object>(); 
				 
			 if (isGrid.equalsIgnoreCase("true")) {
					capabilitiesChrome.setBrowserName("chrome");
					try {
						driver = new RemoteWebDriver(new URL(System.getProperty("gridhub")), capabilitiesChrome);
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
					}
					System.out.println("Launching grid for Chrome browser.");
				} else {
					 
					chromePrefs.put("credentials_enable_service", false);
					chromePrefs.put("profile.password_manager_enabled", false);
					options.setExperimentalOption("prefs", chromePrefs);
					capabilitiesChrome.setCapability(ChromeOptions.CAPABILITY, options); 
					driver = new ChromeDriver();				 
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(pageTimeOut, TimeUnit.SECONDS);
					System.out.println("Going to launch Chrome driver!");

				}
				 
				break;

			case "firefox":
				System.out.println("  Firefox driver!");
				System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
						
			 
				if (isGrid.equalsIgnoreCase("true")) {
					 DesiredCapabilities capabilities = DesiredCapabilities.firefox();	
					capabilities.setBrowserName("firefox");
					try {
						driver = new RemoteWebDriver(new URL(System.getProperty("gridhub")), capabilities);
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
					}
				}
				driver = new FirefoxDriver();
				 
				System.out.println("Going to launch Firefox driver!");
				
				 
				
				break;

			case "ie":
				System.out.println("internet explorer");
				System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
				 
				DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
				capabilitiesIE.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				if (isGrid.equalsIgnoreCase("true")) {
					System.out.println("Inside remote::" + System.getProperty("gridhub"));
					capabilitiesIE.setPlatform(Platform.WINDOWS);
					capabilitiesIE.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
					capabilitiesIE.setCapability(CapabilityType.VERSION, "11");
					capabilitiesIE.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					capabilitiesIE.setJavascriptEnabled(true);
					capabilitiesIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					try {
						driver = new RemoteWebDriver(new URL(System.getProperty("gridhub")), capabilitiesIE);
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
					}
					System.out.println("Launching grid for IE browser.");
				} else {
					capabilitiesIE.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					driver = new InternetExplorerDriver();
					id= Thread.currentThread().getId();
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(pageTimeOut, TimeUnit.SECONDS);
					System.out.println("Going to launch IE driver!");
					 
					
				}
			 
				break;

			default:
				new RuntimeException("Unsupported browser type");
			}
	 
			
		} 
			
			logger.info("***********************************************************************************");
			logger.info("*** Start of Suite Execution: ");
			logger.info("***********************************************************************************");
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			naviagateToURL();
 		 
	 }
	
	public void getUrl(String url) {
		logger.info("Navigating to "+url);
		driver.get(url);
	}
	
	
	public void naviagateToURL() {
		 
			url= System.getProperty("applicationURL");
			System.out.println("url is :"+url);
			 extent.setSystemInfo("Application URL",url); 
			 driver.get(url);
	 
	}
	
	public void setDropdownValue(WebElement w, String s, String wname) {
		logger.info("Looking for the webelement " + wname);

		Select oSelect = new Select(w);
		oSelect.selectByVisibleText(s);

	}
	public void verifyVisibility(WebElement w, String s) {

		w.isDisplayed();
		Assert.assertTrue(true);

	}
	
	public MainBase assertTrue(boolean value,String message) {
		Assert.assertTrue(value,message);
		return this;
	}
	
	public MainBase assertEquals(String actual,String expected,String message) {
		Assert.assertEquals(actual, expected, message);
		return this;
	}
	
	public MainBase assertEquals(String actual,String expected) {
		Assert.assertEquals(actual, expected);
		return this;
	}
	
	public MainBase assertFalse(boolean value,String message) {
		Assert.assertFalse(value,message);
		return this;
	}
	
	public void waitForVisible(WebElement element){
 
		new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
		 
	}
	
	
	public MainBase waitForNotVisible(WebElement element) throws Exception{
		new WebDriverWait(driver, 30).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		return this;
	}
	

	
	public MainBase Wait(int timeInSec) throws Exception {
		Thread.sleep(timeInSec*1000);
		return this;
	}
	
 
		
}
