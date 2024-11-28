package com.appiumdemo;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SportsAdmin {
	
	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";
	public static String hub = "@hub.lambdatest.com/wd/hub";
	
	ExcelReadingData TestData = new ExcelReadingData();
	
	static RemoteWebDriver driver;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	ExcelReadingData playerdata = new ExcelReadingData();
	ExcleWriting testResult = new ExcleWriting();

	public static void clickExceptionMethod(String webelement) throws InterruptedException {
		int oddCLickableFlag = 0;
		int timeLimit = 0;
		while (oddCLickableFlag == 0 && timeLimit < 10) {
			if (timeLimit < 5) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath(webelement)).click();
					oddCLickableFlag = 1;
				} catch (ElementClickInterceptedException |NoSuchElementException e) {
					oddCLickableFlag = 0;
					timeLimit++;
				}
			}
			if (timeLimit >= 5) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath(webelement)).click();
					oddCLickableFlag = 1;
				} catch (ElementClickInterceptedException | NoSuchElementException e) {
					oddCLickableFlag = 0;
					Actions action = new Actions(driver);
					action.scrollToElement(driver.findElement(By.xpath(webelement))).perform();
					action.scrollByAmount(0, 50).perform();
					timeLimit++;
				}
			}
		}
	}

	@BeforeTest
	public void BeforeTest() throws IOException {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("127");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "rushikesh.potadar");
		ltOptions.put("accessKey", "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "SportsAdmin");
		ltOptions.put("project", "SportsAdminTesting");
		ltOptions.put("name", "SportsAdmin");
		ltOptions.put("tunnel", true);
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		driver = new RemoteWebDriver(new URL("https://" + userName + ":" + accessKey + hub), browserOptions);
	}
	
	@Test
	public void login() throws IOException, NoSuchElementException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
		
		String[] EnvDetails = TestData.SportsAdminEnvDetails();
		
		String SportsAdminURL = "http://"+EnvDetails[0]+":81/AdminWeb/";
		
		try {
			driver.get(SportsAdminURL);
			
			driver.findElement(By.xpath("//div[@class='form-group has-feedback'][1]/input")).sendKeys("sa");
			driver.findElement(By.xpath("//div[@class='form-group has-feedback'][2]/input")).sendKeys("1234");
			
			driver.findElement(By.xpath("//*[text()='Sign In']")).click();
			
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='logo']")));
		} catch (WebDriverException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void freeCash() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		String[] freeCashDetails = TestData.FreeCashDetails();
		
		String freeCashDropdown = "//span[text()='FreeCash']";
		
		SportsAdmin.clickExceptionMethod(freeCashDropdown);
		
		driver.findElement(By.xpath("//a[text()='FreeCash']")).click();
		
		if (freeCashDetails[0].equals("Single Player")) {
			
			String radioButtonSingle_Bulk = "//*[text()=' Single Player ']";
			SportsAdmin.clickExceptionMethod(radioButtonSingle_Bulk);
			
		} else if(freeCashDetails[0].equals("Bulk upload")) {
			
		} else {
			System.out.println("Error: Please mention Upload type(Single or bulk player) in your Free cash test data file");
			driver.quit();
		}
		
		driver.findElement(By.xpath("//option[text()='Select Book']")).click();
		
		driver.findElement(By.xpath("//option[text()='"+freeCashDetails[1].toUpperCase()+"']")).click();
		
		driver.findElement(By.xpath("//label[text()='Account Number']/following-sibling::input")).sendKeys(freeCashDetails[2]);
		
		driver.findElement(By.xpath("//label[text()='Amount']/following-sibling::input")).sendKeys(freeCashDetails[3]);
		
		driver.findElement(By.xpath("//label[text()='Cash Type']/following-sibling::select")).click();
		
		if (freeCashDetails[4].equals("FreeCash") || freeCashDetails[4].equals("FreeCash")) {
			driver.findElement(By.xpath("//option[text()='FreeCash']")).click();
		} else {
			driver.findElement(By.xpath("//option[text()='CashBack']")).click();
		}
		
		driver.findElement(By.xpath("//label[text()='Reason']/following-sibling::input")).sendKeys(freeCashDetails[5]);
		
		driver.findElement(By.xpath("//button[text()='Validate User']")).click();
		
//		Actions action = new Actions(driver);
//		action.scrollToElement(driver.findElement(By.xpath("//button[text()='Submit']"))).perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Submit']")));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
		if(!driver.findElement(By.xpath("//*[text()='Submit']")).isEnabled()) {
			System.out.println("Action is allowed");
			
		} else {
			String Error= driver.findElement(By.xpath("//*[text()=' Error ']/preceding-sibling::td[1]")).getText();
			
			System.out.println(Error);
		}
		
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void AfterTest() {
		driver.quit();
	}

}
