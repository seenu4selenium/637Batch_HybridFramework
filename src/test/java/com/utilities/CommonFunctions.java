package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.objectrepository.Locators;

public class CommonFunctions {

	public static WebDriver driver;
	public FileInputStream fi;
	public String propertyFile = "QA_TD.properties";
	public String screenshotPath;
	public String className;
	public String methodName;
	public String projectDir = System.getProperty("user.dir");

	public Locators loc = new Locators();
	public Properties p = new Properties();

	public void chromeBrowserLaunch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public void getURL(String URL) throws IOException {
		fi = new FileInputStream(".\\src\\test\\resources\\testdata\\" + propertyFile);
		p.load(fi);
		driver.get(p.getProperty(URL));
	}

	/*******
	 * SendKeys
	 * 
	 * @throws Exception
	 ************************/
	public void sendKeysByAnyLocatorWithProp(By locator, String inputdata) throws Exception {
		fi = new FileInputStream(".\\src\\test\\resources\\testdata\\" + propertyFile);
		p.load(fi);

		WebElement element = driver.findElement(locator);

		// Check your locator is displayed?
		if (driver.findElements(locator).size() > 0) {
			// Check your element is in enable state?
			if (element.isEnabled()) {
				System.out.println("Given locator is enable state ***");
				// Clear any existing data
				highlightElement(element);
				element.clear();
				// Send the test data to Edit box
				highlightElement(element);
				element.sendKeys(p.getProperty(inputdata));
			} else {
				System.out.println("Given locator is not enable state on DOM(Current page***");
			}
		} else {
			System.out.println("Given locator is not displayed on DOM(Current page***");
		}
	}

	public void sendKeysByAnyLocator(By locator, String inputdata) throws Exception {	
		WebElement element = driver.findElement(locator);

		// Check your locator is displayed?
		if (driver.findElements(locator).size() > 0) {
			// Check your element is in enable state?
			if (element.isEnabled()) {
				System.out.println("Given locator is enable state ***");
				// Clear any existing data
				highlightElement(element);
				element.clear();
				// Send the test data to Edit box
				highlightElement(element);
				element.sendKeys(inputdata);
			} else {
				System.out.println("Given locator is not enable state on DOM(Current page***");
			}
		} else {
			System.out.println("Given locator is not displayed on DOM(Current page***");
		}
	}
	/*******
	 * Click
	 * 
	 * @throws Exception
	 ************************/
	public void clickByAnyLocator(By locator) throws Exception {
		// implicitWait(10);
		WebElement element = driver.findElement(locator);
		// Check your locator is displayed?
		if (driver.findElements(locator).size() > 0) {
			// Check your element is in enable state?
			if (element.isEnabled()) {
				// Click on Button/radiobutton/checkbox/Link...
				highlightElement(element);
				element.click();
			} else {
				System.out.println("Given locator is not enable state on DOM(Current page***");
			}
		} else {
			System.out.println("Given locator is not displayed on DOM(Current page***");
		}
	}

	public void clickUsingJavaScript(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		highlightElement(element);
		executor.executeScript("arguments[0].click();", element);
	}

	public void highlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''",
		// element);
	}

	/*********** timestamp **********/
	public String timestamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyy_HHmmss");
		String timeTamp = df.format(d);
		return timeTamp;
	}

	/*****
	 * takescreenshot
	 * 
	 * @throws Exception
	 ************/
	public void takeScreenshot(String name) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = ".\\Screenshots\\";
		FileHandler.copy(scrFile, new File(screenshotPath + name + "_" + timestamp() + ".PNG"));
		System.out.println("Screenshot taken*** ");
	}

	public void screenshotWithStatus(ITestResult res) throws Exception {
		projectDir = System.getProperty("user.dir");
		screenshotPath = projectDir + "\\Screenshots\\";
		className = res.getTestClass().getName().trim();
		methodName = res.getName().trim();
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "PASS_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}

	}

	/****************** Dropdown selection **************************************/

	public void selectByVisibleText(By locater, String visibleText) {

		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(visibleText);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByIndex(By locater, int index) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByIndex(index);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByValue(By locater, String value) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByValue(value);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void printAllDropdownValues(By locater) {
		WebElement element = driver.findElement(locater);

		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {

				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());

					// Select Aug option from the dropdown
					if (dropdownValues.get(i).getText().equals(visibleText)) {
						dropdown.selectByIndex(i);
						break;
					}
				}

			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.accept();
	}

	public void alertHandleByDismiss() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.dismiss();
	}

	// Scrolling down the page till the element is found
	public void scrollIntoView(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Page scroll done");

	}

	/************ waits in selenium ***********************/
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		System.out.println("Implicit wait method used***");
	}

	public void explicitWaitVisibilityOf(By locator, int timeinSeconds) {
		WebElement element = driver.findElement(locator);
		System.out.println("using explicitWaitVisibilityOf ****");
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		ww.until(ExpectedConditions.visibilityOf(element));
	}

	public void explicitWaitElementToBeClickable(By locator, int timeinSeconds) {
		WebElement element = driver.findElement(locator);
		System.out.println("using explicitWaitElementToBeClickable ****");
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		ww.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void explicitWaiTeltextToBePresentInElement(By locator, String expectedText, int timeinSeconds) {
		WebElement element = driver.findElement(locator);
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		ww.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
	}

	/***
	 * instead of fluent wait use customized While loop statement**@throws Exception
	 *****/
	public void waitforElement(By locater) throws Exception {
		int i = 0;
		while (driver.findElements(locater).size() < 1) {
			Thread.sleep(500);
			System.out.println("Wait for the element***************");			
			if (i > 30) {
				System.out.println("Element is not present");
				break;
			}
			i++;
		}
	}
	
	

}
