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

public class AmazonTest extends CommonFunctions {

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
	public void f() throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_TD.properties");
		p.load(fi);

		chromeBrowserLaunch();
		driver.get(p.getProperty("AmazonURL"));
		Thread.sleep(5000);

		driver.findElement(loc.amazon_SearchEditBox).sendKeys(p.getProperty("SearchBoxText"));
		driver.findElement(loc.amazon_SearchIcon).click();
		Thread.sleep(5000);
		driver.findElement(loc.amazon_SearchResults).click();
		driver.findElement(loc.amazon_AddToCart).click();

	}
}
