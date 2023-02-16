package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
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
	public void tc_02_validLoginToJRI() throws Exception {
		sendKeysByAnyLocatorWithProp(loc.jriSigninPage_EmailEditbox, "Email");
		sendKeysByAnyLocatorWithProp(loc.jriSigninPage_PasswordEditbox, "Password");
		//get captcha from Console by using Scanner
		driver.findElement(By.id("txtCaptcha")).sendKeys(s.next());
		
		clickByAnyLocator(loc.jriSigninPage_SecureSigninButton);
		System.out.println("valid Login  test case done");
		implicitWait(30);
	}

	@Test
	public void tc_03_recharge() throws Exception {
		clickByAnyLocator(By.id("listingtable_ImgRecharge_6"));
		implicitWait(30);
		
		//Get the parent window name
		String parentWindowName = driver.getWindowHandle();
		clickByAnyLocator(By.id("lnkPopularRecharge"));

		//Popuphandle
		Set<String> allWindowNames = driver.getWindowHandles();// 2
		
		//using FOREACH loop will print set/arraylist/list values
		for (String abc : allWindowNames) {
			//Verify parent and child popupwindows, if both the na,e are not matching switch to child popup
			if (!parentWindowName.equals(abc)) {
				driver.switchTo().window(abc);
				//do action on child window
				driver.close();
			} 
		}
		
				
	}

	@AfterMethod
	public void am(ITestResult res) throws Exception {
		screenshotWithStatus(res);
	}
}
