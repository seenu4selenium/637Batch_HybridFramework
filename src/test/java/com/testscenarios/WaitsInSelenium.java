package com.testscenarios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class WaitsInSelenium extends CommonFunctions {

	@Parameters("browserName")
	@BeforeClass
	public void browserLaunch(@Optional("Chrome") String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please give valid browserName");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void takescreenshot(ITestResult res) throws Exception {
		screenshotWithStatus(res);
	}

	@Test
	public void f() throws Exception {
//		driver.get("file:///C:/Users/nalla/OneDrive/Desktop/Selenium/Exam_14Oct2022/QE%20-%20index.html");
//		//Thread.sleep(9000);
//		//implicit wait: Wait until webpage has loaded..
//		//if my webpage has loaded within 6 sec, 7sec eclipse will move to next line
//		//implicitWait(30);
//		
//		//Explicit wait: Wait until with condition matches
//		explicitWaitElementToBeClickable(By.id("test5-button"), 25);
//		
//		
//		
//		scrollIntoView(By.id("test5-button"));
//		clickByAnyLocator(By.id("test5-button"));
		
		driver.get("https://collegeweeklive.com/go-signup/");
		//implicitWait(30);
		//explicitWaitElementToBeClickable(By.id("firstName"), 20);
		waitforElement(By.id("firstName"));
		sendKeysByAnyLocator(By.id("firstName"), "Hello");

	}
	
	

}
