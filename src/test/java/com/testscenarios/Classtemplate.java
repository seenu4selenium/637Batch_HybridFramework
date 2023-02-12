package com.testscenarios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class Classtemplate extends CommonFunctions {

	@Parameters("browserName")
	@BeforeClass
	public void browserLaunch(@Optional("Firefox") String browserName) {
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

	@Test
	public void f() {
		// Develop the script from here

	}

	@AfterClass
	public void takescreenshot() throws Exception {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("MMMddyyyy_HHmmss");
		String timeStamp = df.format(d);

		// Takescreenshot
		// Take screenshot by using Selenium interface then temp store into RAM location
		File abc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Move screenshot to specified location
		FileHandler.copy(abc, new File(".\\Screenshots\\test_" + timeStamp + ".png"));
	}
}
