package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	public void f() throws Exception {
		
		//chromeBrowserLaunch();
		getURL("JRI_Signin");
		sendKeysByAnyLocator(loc.jriSigninPage_EmailEditbox, "Email");
		sendKeysByAnyLocator(loc.jriSigninPage_PasswordEditbox, "Password");
		sendKeysByAnyLocator(loc.jriSigninPage_CaptchaEditbox, "Captcha");
		clickByAnyLocator(loc.jriSigninPage_SecureSigninButton);

		// driver.get(p.getProperty("JRI_Signin"));
		// driver.findElement(loc.jriSigninPage_EmailEditbox).sendKeys(p.getProperty("Email"));
		// driver.findElement(loc.jriSigninPage_PasswordEditbox).sendKeys(p.getProperty("Password"));
		// driver.findElement(loc.jriSigninPage_CaptchaEditbox).sendKeys(p.getProperty("Captcha"));
		// driver.findElement(loc.jriSigninPage_SecureSigninButton).click();

	}
}
