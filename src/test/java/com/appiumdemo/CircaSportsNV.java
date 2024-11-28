package com.appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Level;
import static java.time.Duration.*;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileBy;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.SessionNotCreatedException;
public class CircaSportsNV {
	
	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";

	static AppiumDriver driver;
	
	static Scanner s = new Scanner(System.in);
	
	@BeforeTest
	public void Capabilities() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:platformVersion", "12");
		capabilities.setCapability("appium:isRealMobile", true);
		capabilities.setCapability("appium:app", "lt://APP1016033751686554138486586"); // Enter your app url
		capabilities.setCapability("appium:deviceName", "Galaxy S22 5G");
		capabilities.setCapability("appium:deviceOrientation", "PORTRAIT");
		capabilities.setCapability("appium:build", "Java Vanilla - Android");
		capabilities.setCapability("appium:name", "Sample Test Java");
		capabilities.setCapability("appium:console", true);
		capabilities.setCapability("appium:network", false);
		capabilities.setCapability("appium:visual", true);
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:devicelog", true);
		capabilities.setCapability("appium:tunnel", true);
		capabilities.setCapability("appium:idleTimeout", 2);
		
//		UiAutomator2Options opt = new UiAutomator2Options();
//		
//		opt.setPlatformName(Platform.ANDROID.name());
//		opt.setAutomationName("StadiumApps");
//		opt.setCapability("app", "lt://APP1016033751686554138486586");
//		opt.setDeviceName("Galaxy S22 5G");
//		opt.setPlatformVersion("12");
//		opt.setCapability("isRealDevice", true);
//		opt.setCapability("deviceOrientation", "PORTRAIT");
//		opt.setCapability("build", "Java Projest");
//		opt.setCapability("console", true);
//		opt.setCapability("network", false);
//		opt.setCapability("visual", true);
//		opt.setCapability("devicelog", true);
//		opt.setCapability("tunnel", true);
//		opt.setCapability("idleTimeout", 2);
		

		driver = new AppiumDriver(
				new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
				capabilities);
	}
	
	@Test
	public void launchingApp() {
		
		WebDriverWait wait = new WebDriverWait(driver, 240);

		System.out.println("Launching the app");
		System.out.println("----------------------------");

		driver.findElement(By.className("android.widget.Button")).click();
		
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='Login']")).isDisplayed()) System.out.println("App launched successfully");
		} catch (NoSuchElementException e) {
			System.out.println("Failed to launch the app");
		}
		
	}
	
	@Test
	public void registerPlayer() throws InterruptedException {
		
		driver.findElement(By.xpath("//android.widget.Button[@text='REGISTER NOW']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='CONTINUE']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.EditText[@text='First name']")).sendKeys("Rishi");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Middle name']")).sendKeys("Potdar");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Last name']")).sendKeys("Ivy");
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='DD']")).sendKeys("01");
		driver.findElement(By.xpath("//android.widget.EditText[@text='MM']")).sendKeys("02");
		driver.findElement(By.xpath("//android.widget.EditText[@text='YYYY']")).sendKeys("1980");
		
		System.out.println("Enter email");
//		String EmailAdress = s.next();
		driver.findElement(By.xpath("//android.widget.EditText[@text='name@example.com']")).sendKeys("");
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='Address line 1']")).sendKeys("sample");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Address line 2']")).sendKeys("address");
		driver.findElement(By.xpath("//android.widget.EditText[@text='City']")).sendKeys("Hyderabad");
		
		driver.findElement(By.xpath("//android.widget.Button[@text='State']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Arizona']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Zip code']")).sendKeys("12345");
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		   js.executeScript("window.scrollBy(0, 0.2)");
		   
//		   driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Password\").instance(0))")).click();
		   
		driver.findElement(By.xpath("//android.widget.EditText[@text='Password']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Retype password']")).sendKeys("Test@1234");
		
		driver.findElement(By.xpath("//android.widget.Button[@text='CONTINUE']")).click();
		
		driver.findElement(By.xpath("//android.widget.Switch[@text='OFF']")).click();
		
		driver.findElement(By.xpath("//android.widget.Button[@text='CONTINUE']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='CREATE MY ACCOUNT']")).click();
		
		String AccountNo = driver.findElement(By.xpath("//android.widget.TextView[@text='Account number']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView")).getText();

		System.out.println("Your Account No: "+AccountNo);
		
	}

}
