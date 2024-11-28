package com.appiumdemo;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.browserstack.ExcelReadingData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CircaIL22_08 {
	
	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";

	static AppiumDriver driver;
	static AppiumDriver driver1;
	
	ExcelReadingData playerdata = new ExcelReadingData();
	ExcleWriting testResult = new ExcleWriting();

	@Test
	public void launchingApp() throws InterruptedException, IOException {

		try {
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			capabilities.setCapability("platformName", "Android");
//			capabilities.setCapability("deviceName", "Galaxy S23+");
//			capabilities.setCapability("platformVersion", "13");
//			capabilities.setCapability("isRealMobile", true);
//			capabilities.setCapability("app", "lt://APP1016033751686549182532381"); // Enter your app url
//			capabilities.setCapability("deviceOrientation", "PORTRAIT");
//			capabilities.setCapability("build", "Java Vanilla - Android");
//			capabilities.setCapability("name", "Sample Test Java");
//			capabilities.setCapability("console", true);
//			capabilities.setCapability("network", false);
//			capabilities.setCapability("visual", true);
//			capabilities.setCapability("devicelog", true);
//			capabilities.setCapability("tunnel", true);
//			capabilities.setCapability("idleTimeout", 2);
			
			UiAutomator2Options options = new UiAutomator2Options();
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("w3c", true);
			ltOptions.put("platformName", "android");
			ltOptions.put("deviceName", "OnePlus 11");
			ltOptions.put("platformVersion", "13");
			ltOptions.put("app", "lt://APP10160381691724908504570468");
			ltOptions.put("build", "Java Sample");
			ltOptions.put("devicelog", true);
			ltOptions.put("visual", true);
			ltOptions.put("name", "CCL Web app");
			ltOptions.put("project", "com.stadiumapps");
			ltOptions.put("deviceOrientation", "portrait");
//			  ltOptions.put("geoLocation", "IN");
			HashMap<String, String> locationObj = new HashMap<String, String>();
			locationObj.put("lat", "17.65992");
			locationObj.put("long", "75.906387");
			ltOptions.put("location", locationObj);
//			  HashMap<String, String> option = new HashMap<String, String>();
//	        option.put("user", "rushikesh.potadar");
//	        option.put("key", "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1");
			ltOptions.put("language", "en");
			ltOptions.put("locale", "en");
			ltOptions.put("tunnel", true);
			ltOptions.put("autoGrantPermissions", true);
			ltOptions.put("autoAcceptAlerts", true);
			ltOptions.put("isRealMobile", true);
			options.setCapability("lt:options", ltOptions);

			driver = new AppiumDriver(
					new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
					options);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			System.out.println("Launching the app");
			System.out.println("----------------------------");
			
			try {
				driver.findElement(By.className("android.widget.Button")).click();
			} catch (NoSuchElementException e) {

			}
			
//			Map<Integer, String[]> testData = new LinkedHashMap<>();
//			testData = playerdata.getTestData();
//			
//			String EnvName = testData.get(1)[7];
//			try {
//				wait.until(ExpectedConditions
//						.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+EnvName+"']")));
//				System.out.println("App Launched Successfully");
//				System.out.println("--------------------------------");
//			} catch (Exception e) {
//				System.out.println("Failed to Launch the App");
//				System.out.println("------------------------------");
//			}
		} catch (AssertionError a) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			a.printStackTrace();
		}
	}

	@Test
	public void landingOnHomePage() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		System.out.println("Loading Home Page..");
		System.out.println("---------------------------");
		
//		Map<Integer, String[]> testData = new LinkedHashMap<>();
//		testData = playerdata.getTestData();
//		
//		String EnvName = testData.get(1)[7];
//
//		driver.findElement(By.xpath("//android.widget.TextView[@text='"+EnvName+"']")).click();// Hard coded value
//		driver.findElement(By.xpath("//android.widget.Button[@text='Proceed']")).click();
//		driver.findElement(By.xpath("//android.widget.Button[@text='Confirm']")).click();

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='LOG IN']")));
			System.out.println("Home Page Loaded Successfully");
			System.out.println("---------------------------------");
		} catch (NoSuchElementException e) {
			System.out.println("Failed to load Home Page");
		}
	}
	
	// Page 1 - Home Page ... But1 - Login But 2 - Proceed , But3 Registration button ...  Values .. Text or 

	@Test
	public void playerLogin() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		Map<String, String> playerData = new LinkedHashMap<>();
		Map<Integer, String[]> testData = new LinkedHashMap<>();
		playerData = playerdata.getPlayerData();
		testData = playerdata.getTestData();
		
		int playerNo= Integer.parseInt(testData.get(1)[2]);

		String EmailAddress = (String) playerData.keySet().toArray()[playerNo - 1];
		String Pass = playerData.get(EmailAddress);
		
		String Login_Button = "//android.widget.TextView[@text='LOG IN']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Login_Button)));
		
		int oddCLickableFlag = 0;
		int timeLimit = 0;
		while (oddCLickableFlag == 0 && timeLimit < 10) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath(Login_Button)).click();
					driver.findElement(By.xpath("//android.widget.EditText[@text='Email Address']"));
					oddCLickableFlag = 1;
				} catch (ElementClickInterceptedException | NoSuchElementException e) {
					oddCLickableFlag = 0;
					timeLimit++;
				}
			}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Email Address']")));

		WebElement InnputEmail_or_AccountNo = driver
				.findElement(By.xpath("//android.widget.EditText[@text='Email Address']"));

		InnputEmail_or_AccountNo.sendKeys(EmailAddress);

		WebElement Innput_Pass = driver.findElement(By.xpath("//android.widget.EditText[@text='Password']"));

		Innput_Pass.sendKeys(Pass);

		driver.findElement(By.xpath("//android.widget.Button[@text='LOG IN']")).click();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='DEPOSIT']")));

			System.out.println("Logged in Successfulyy");
			System.out.println("------------------------------");
			
			try {
				driver.findElement(By.xpath("//android.widget.TextView[@text='BETSLIP']"));
				System.out.println("Elemet found");
			} catch (Exception e) {
				System.out.println("Elenemt not fount");
			}

			WebElement Player_balance = driver.findElement(By.xpath(
					"//android.view.View[@content-desc=\"DEPOSIT\"]/following-sibling::android.view.View/android.widget.TextView"));
			System.out.println("Player wallet balance are: " + Player_balance.getAttribute("text"));
			
		} catch (NoSuchElementException e) {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Login Failed']"));
			System.out.println("Invalid Accout no. or Password");
		}
		
	}
	
	@Test
	public void placingBet() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			
			if (driver.findElement(By.xpath("//android.view.View[@index='2']")).isDisplayed()) System.out.println("Games are there");
			
			driver.findElement(By.xpath("//android.view.View[@index='0']/android.view.View[@index='2']/android.view.View/android.view.View/android.view.View[@index='1']/android.widget.Button[@index='7']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='BETSLIP']")));
			String betsCount = driver.findElement(By.xpath("//android.widget.TextView[@text='BETSLIP']/following-sibling::android.widget.TextView")).getAttribute("text");
			System.out.println(betsCount+" bets are added to bet slip");
			driver.findElement(By.xpath("//android.widget.TextView[@text='BETSLIP']")).click();
			
		} catch (NoSuchElementException e) {
			System.out.println("No games are there");
		}
		
		try {
			driver.findElement(By.xpath(accessKey));
			System.out.println("Element found");
		} catch (NoSuchElementException e) {
			System.out.println("No such Element");
		}
		
		System.out.println(driver.getPageSource());
		driver.quit();
	}
	
	@Test
	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		try {
			String profileButtonXpath = "/hierarchy/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView";
			
			int oddCLickableFlag = 0;
			int timeLimit = 0;
			while (oddCLickableFlag == 0 && timeLimit < 120) {
					try {
						Thread.sleep(1000);
						driver.findElement(By.xpath(profileButtonXpath)).click();
						driver.findElement(By.xpath("//android.view.View[@content-desc=\" Log out\"]/android.widget.TextView"));
						oddCLickableFlag = 1;
					} catch (ElementClickInterceptedException | NoSuchElementException e) {
						oddCLickableFlag = 0;
						timeLimit++;
					}
				}
			
			driver.findElement(By.xpath("//android.view.View[@content-desc=\" Log out\"]/android.widget.TextView")).click();
			
		} catch (Exception e) {
			
		}
		
	}

}
