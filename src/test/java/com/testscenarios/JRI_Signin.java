package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class JRI_Signin extends CommonFunctions {

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

	@Test
	public void tc_01_openJRIapplication() throws Exception {
		getURL("JRI_Signin");
		System.out.println("JRI App opened successfully");
	}

	@Test
	public void tc_02_invalidLoginToJRI() throws Exception {
		sendKeysByAnyLocator(loc.jriSigninPage_EmailEditbox, "Email");
		sendKeysByAnyLocator(loc.jriSigninPage_PasswordEditbox, "Password");
		sendKeysByAnyLocator(loc.jriSigninPage_CaptchaEditbox, "Captcha");
		clickByAnyLocator(loc.jriSigninPage_SecureSigninButton);
		System.out.println("invalid Login  test case done");

	}

	@AfterMethod
	public void am(ITestResult res) throws Exception {
		screenshotWithStatus(res);
	}
}
