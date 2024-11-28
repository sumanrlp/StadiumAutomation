package com.appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

public class SMC_QA_env {
	
	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";

	static AppiumDriver driver;
	static AppiumDriver driver1;

	@Test
	public void LaunchingApp() throws MalformedURLException, InterruptedException {

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "Galaxy S22 5G");
			capabilities.setCapability("platformVersion", "12");
			capabilities.setCapability("isRealMobile", true);
			capabilities.setCapability("app", "lt://APP1016033751686548153901206"); // Enter your app url
			capabilities.setCapability("deviceOrientation", "PORTRAIT");
			capabilities.setCapability("build", "Java Vanilla - Android");
			capabilities.setCapability("name", "Sample Test Java");
			capabilities.setCapability("console", true);
			capabilities.setCapability("network", false);
			capabilities.setCapability("visual", true);
			capabilities.setCapability("devicelog", true);
			capabilities.setCapability("tunnel", true);
			capabilities.setCapability("idleTimeout", 2);

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", "Android");
			caps.setCapability("deviceName", "Galaxy S22 5G");
			caps.setCapability("platformVersion", "12");
			caps.setCapability("isRealMobile", true);
			caps.setCapability("app", "lt://APP1016045801684494172782719"); // Enter your app url
			caps.setCapability("deviceOrientation", "PORTRAIT");
			caps.setCapability("build", "Java Vanilla - Android");
			caps.setCapability("name", "Sample Test Java");
			caps.setCapability("console", true);
			caps.setCapability("network", false);
			caps.setCapability("visual", true);
			caps.setCapability("devicelog", true);
			caps.setCapability("tunnel", true);
			caps.setCapability("idleTimeout", 2);

			driver = new AppiumDriver(
					new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
					capabilities);
			WebDriverWait wait = new WebDriverWait(driver, 240);

			System.out.println("Launching the app");
			System.out.println("----------------------------");

			driver.findElement(By.className("android.widget.Button")).click();
			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='QALab9_SMC']")));
				System.out.println("App Launched Successfully");
				System.out.println("--------------------------------");
			} catch (Exception e) {
				System.out.println("Failed to Launch the App");
				System.out.println("------------------------------");
			}
//			

		} catch (AssertionError a) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			a.printStackTrace();
		}
	}

	@Test
	public void LandingOnHomePage() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 240);
		System.out.println("Loading Home Page..");
		System.out.println("---------------------------");

		driver.findElement(By.xpath("//android.widget.TextView[@text='QALab9_SMC']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='Proceed']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='Confirm']")).click();

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='LOG IN']")));
			System.out.println("Home Page Loaded Successfully");
			System.out.println("---------------------------------");
		} catch (NoSuchElementException e) {
			System.out.println("Failed to load Home Page");
		}
	}

	@Test
	public void PlayerLogin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 240);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.view.View[@content-desc='LOG IN']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='']")));

		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();

		WebElement InnputEmail_or_AccountNo = driver
				.findElement(By.xpath("//android.widget.EditText[@text='Mlife Account Number or Email ID']"));

		InnputEmail_or_AccountNo.sendKeys("rishi04@gmai.com");

		WebElement Innput_Pass = driver.findElement(By.xpath("//android.widget.EditText[@text='Password']"));

		Innput_Pass.sendKeys("Test@123");

		driver.findElement(By.xpath("//android.widget.Button[@text='LOG IN']")).click();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='DEPOSIT']")));
			Thread.sleep(5000);

			System.out.println("Logged in Successfulyy");
			System.out.println("------------------------------");

			WebElement Player_balance = driver.findElement(By.xpath(
					"//android.view.View[@content-desc=\"DEPOSIT\"]/following-sibling::android.view.View/android.widget.TextView"));
			System.out.println("Player wallet balance are: " + Player_balance.getAttribute("text"));
			
		} catch (NoSuchElementException e) {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Login Failed']"));
			System.out.println("Invalid Accout no. or Password");
		}
		driver.quit();
	}
	
	@Test
	public void playerRegisteration() {
		
		
	}

}
