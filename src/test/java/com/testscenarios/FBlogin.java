package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objectrepository.Locators;

public class FBlogin {
	WebDriver driver;
	// Created Object for Locators class to call all locators here
	Locators loc = new Locators();
	Properties p = new Properties();

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
	public void f() throws IOException {
		// Read the test data from property file
		// Find the location of the file
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_TD.properties");
		// Load the data from property file to a varaible
		p.load(fi);
		driver.get(p.getProperty("FB_URL"));
		driver.findElement(loc.fbLogin_EmailEditBox).sendKeys(p.getProperty("Username"));
		driver.findElement(loc.fbLogin_PasswordEditBox).sendKeys(p.getProperty("Passsword"));
		driver.findElement(loc.fbLogin_LoginButton).click();
	}
}
