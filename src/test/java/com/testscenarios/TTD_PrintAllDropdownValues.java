package com.testscenarios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class TTD_PrintAllDropdownValues extends CommonFunctions {

	@Parameters("browserName")
	@BeforeClass
	public void browserLaunch(@Optional("chrome") String browserName) {
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
		driver.get("https://tirupatibalaji.ap.gov.in/#/registration");
		Thread.sleep(5000);
		//print all proof Dropdown values
		printAllDropdownValues(By.name("proofS"));	
		System.out.println("***********************");
		printAllDropdownValues(By.name("countryS"));
		System.out.println("***********************");
		selectCustomiseOptionFromTheDropdownValues(By.name("proofS"), "PAN Card");
		
	}

}
