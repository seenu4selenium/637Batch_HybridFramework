package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class FBloginExcel {
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
	public void f() throws IOException, Exception {

		driver.get("https://www.facebook.com/");
		Thread.sleep(2000);
		FileInputStream fi = new FileInputStream("./src/test/resources/testdata/td.xlsx");
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet("fb");
		// Get the row count and column count
		System.out.println("RowCount is: " + s.getLastRowNum());
		// Row =i , column=j
		for (int i = 1; i <= s.getLastRowNum(); i++) {// 2<=4
			Row r = s.getRow(i);
			Cell username = r.getCell(0);
			Cell password = r.getCell(1);
			driver.findElement(loc.fbLogin_EmailEditBox).clear();
			driver.findElement(loc.fbLogin_EmailEditBox).sendKeys(username.getStringCellValue());
			driver.findElement(loc.fbLogin_PasswordEditBox).clear();
			driver.findElement(loc.fbLogin_PasswordEditBox).sendKeys(password.getStringCellValue());
			driver.findElement(loc.fbLogin_LoginButton).click();
			Thread.sleep(2000);
			
			//Verify the error message
			if (driver.findElements(By.linkText("Find your account and log in.")).size()>0
					|| 
					driver.findElements(By.linkText("Create a new Facebook account.")).size()>0
					) {
				System.out.println("given credetials are invalid");
			} else {
				System.out.println("given credetials are valid");

			}
		}
	}
}
