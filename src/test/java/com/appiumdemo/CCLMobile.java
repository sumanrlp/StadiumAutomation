package com.appiumdemo;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.jar.Attributes.Name;

import javax.xml.xpath.XPath;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import net.bytebuddy.asm.Advice.Enter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsActions;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.*;
import java.awt.Button;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.appiumdemo.ExcleWriting.*;
import com.browserstack.ExcelReadingData;
import com.browserstack.ExcelReadingData.*;

public class CCLMobile {

	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";

	static AppiumDriver driver;

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
				} catch (ElementClickInterceptedException e) {
					oddCLickableFlag = 0;
					timeLimit++;
				}
			}
			if (timeLimit >= 5) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath(webelement)).click();
					oddCLickableFlag = 1;
				} catch (ElementClickInterceptedException e) {
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

//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("deviceName", "Galaxy S23");
//		capabilities.setCapability("platformVersion", "13");
//		capabilities.setCapability("isRealMobile", true);
//		capabilities.setCapability("deviceOrientation", "PORTRAIT");
//		capabilities.setCapability("build", "CCL-MObile_Browser_Testing");
//		capabilities.setCapability("name", "Sample Test Java");
//		capabilities.setCapability("console", true);
//		capabilities.setCapability("network", false);
//		capabilities.setCapability("visual", true);
//		capabilities.setCapability("devicelog", true);
//		capabilities.setCapability("tunnel", true);
//		capabilities.setCapability("idleTimeout", 2);

		UiAutomator2Options options = new UiAutomator2Options();
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("w3c", true);
		ltOptions.put("platformName", "android");
		ltOptions.put("deviceName", "OnePlus 11");
		ltOptions.put("platformVersion", "13");
//		  ltOptions.put("app", "lt://APP1016033751686554138486586");
		ltOptions.put("build", "Java Sample");
		ltOptions.put("devicelog", true);
		ltOptions.put("visual", true);
		ltOptions.put("name", "CCL Web app");
		ltOptions.put("project", "com.stadiumapps");
		ltOptions.put("deviceOrientation", "portrait");
//		  ltOptions.put("geoLocation", "IN");
		HashMap<String, String> locationObj = new HashMap<String, String>();
		locationObj.put("lat", "17.65992");
		locationObj.put("long", "75.906387");
		ltOptions.put("location", locationObj);
//		  HashMap<String, String> option = new HashMap<String, String>();
//        option.put("user", "rushikesh.potadar");
//        option.put("key", "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1");
		ltOptions.put("language", "en");
		ltOptions.put("locale", "en");
		ltOptions.put("tunnel", true);
		ltOptions.put("autoGrantPermissions", true);
		ltOptions.put("autoAcceptAlerts", true);
		ltOptions.put("isRealMobile", true);
		options.setCapability("lt:options", ltOptions);

		driver = new AppiumDriver(
				new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"), options);
	}

	@Test
	public void login() throws InterruptedException, TimeoutException, IOException {

		// getting player info from PlayerData.xlsx
		Map<String, String> playerData = new LinkedHashMap<>();
		playerData = playerdata.getPlayerData();
		int playerNo = playerdata.getPlayerNoList().get(0);

		String folioNo = (String) playerData.keySet().toArray()[playerNo - 1];
		String CCLPin = playerData.get(folioNo);
		System.out.println();
		System.out.println("Player Info-  Folio no: " + folioNo + " Pin: " + CCLPin);

		String CCL_Url = "http://10.152.157.57:81/Web-CCL/?Token=" + folioNo + "-" + CCLPin + "";

		driver.get(CCL_Url);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		System.out.println("Loading home page...");
		System.out.println("--------------------------");

		try {
			try {
				wait.until(
						ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='bet-container']")));
				System.out.println("Home page loaded");

			} catch (NoSuchElementException e) {
				System.out.println("Failed to load home page");
				System.out.println();
			}

			// upadating the test result in TestCases file
			testResult.updateResult("Pass", 1);

		} catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
			try {
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='Login Required' or text()='Error']")));
				System.out.println("Failed to load home page");
				System.out.println("Error : " + driver.findElement(By.xpath(
						"//*[text()='Login Required' or text()='Error']/parent::div/following-sibling::div/child::div/child::p"))
						.getText());
				driver.findElement(By.xpath(
						"//*[text()='Login Required']/parent::div/following-sibling::div/child::div/descendant::button"))
						.click();
				System.out.println("-----------------------------");
				driver.quit();
			} catch (NoSuchElementException | org.openqa.selenium.TimeoutException e2) {
				System.out.println("unable to catch error");
				driver.quit();
			}

			// upadating the test result in TestCases file
			testResult.updateResult("Fail", 1);
			testResult.updateResult("Blocked", 2);
		}

	}

	@Test
	public void selectingOddsForStraightBet() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));

		// betting config...need to read from excel as test data
		addingOddsToBetslip(playerdata.getGameDetails().get(1));
		placingBet("Straight", 1);
	}

	@Test
	public void selectingOddsForParlayBet() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));

		String gameDetails[] = playerdata.getGameDetails().get(2);

		// betting config...need to read from excel as test data
		String NoOfTeams = gameDetails[1];

		for (int i = 2; i < 2
				+ Integer.parseInt(NoOfTeams); i++) {
			addingOddsToBetslip(playerdata.getGameDetails().get(i));
		}
		placingBet("Parlay", 2);
	}

	@Test
	public void selectingOddsForTeaserBet() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		String parlayNoOfrows = playerdata.getGameDetails().get(2)[1];
		int startingRowNo = Integer.parseInt(parlayNoOfrows)+2;
		String gameDetails[] = playerdata.getGameDetails().get(startingRowNo);

		// betting config...need to read from excel as test data
		String NoOfTeams = gameDetails[1];

		// betting config...need to read from excel as test data
			for (int i = startingRowNo; i < startingRowNo+Integer.parseInt(NoOfTeams); i++) {
				addingOddsToBetslip(playerdata.getGameDetails().get(i));
			}
			placingBet("Teaser", startingRowNo);
	}

	@Test
	public void addingOddsToBetslip(String gameDetails[]) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));

		// betting config...need to read from excel as test data
		String sports = gameDetails[0];
		String NoOfTeams = gameDetails[1];
		String BetType = gameDetails[2];
		String gameName = gameDetails[3];
		String lineTypeId = gameDetails[6];
		String bettingTeamId = gameDetails[7];
		String betAmount = gameDetails[8];
		String wagerSource = gameDetails[9];

		// adding bets to betslip
		String oddElementXpath = "//*[text()='" + sports
				+ "']/parent::a/parent::div/following-sibling::div/descendant::div[text()='" + gameName
				+ "']/following-sibling::div[" + bettingTeamId + "]/descendant::div[@class='odds'][" + lineTypeId
				+ "]/button";
		try {
			wait15.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(oddElementXpath)));
			try {
				driver.findElement(By.xpath(oddElementXpath));
			} catch (Exception e) {
				Actions action = new Actions(driver);
				action.scrollToElement(driver.findElement(By.xpath(oddElementXpath))).perform();
			}
			driver.findElement(By.xpath(oddElementXpath));
			CCLMobile.clickExceptionMethod(oddElementXpath);

		} catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
			// for scrolling the page where the odd is there on which we are placing bet
			System.out.println("entered catch");
			Actions action = new Actions(driver);
			action.scrollToElement(driver.findElement(By.xpath("//*[text()='ALL " + sports + " GAMES']"))).perform();
			action.scrollByAmount(0, 100).perform();
			driver.findElement(By.xpath("//*[text()='ALL " + sports + " GAMES']")).click();
			System.out.println("before wait");
			String oddeElementXpath1 = "//*[text()='" + gameName + "']/following-sibling::div[" + bettingTeamId
					+ "]/descendant::div[@class='odds'][" + lineTypeId + "]/button";
			try {
				wait15.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(oddeElementXpath1)));
				action.scrollToElement(driver.findElement(By.xpath("//*[text()='" + gameName + "']"))).perform();
				action.scrollByAmount(0, 250).perform();
				CCLMobile.clickExceptionMethod(oddeElementXpath1);
			} catch (Exception e2) {
				action.scrollToElement(driver.findElement(By.xpath("//*[text()='" + gameName + "']"))).perform();
				action.scrollByAmount(0, 250).perform();
				CCLMobile.clickExceptionMethod(oddeElementXpath1);
				System.out.println("enter catch1");
				System.out.println("done scroll");
			}
		}
	}

	@Test
	public void placingBet(String betType, int betTypeRowNo) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Map<String, String> playerData = new LinkedHashMap<>();
		playerData = playerdata.getPlayerData();
		int playerNo = playerdata.getPlayerNoList().get(0);

		String folioNo = (String) playerData.keySet().toArray()[playerNo - 1];
		String CCLPin = playerData.get(folioNo);

		String gameTypeId = playerdata.betTypeId();

		String gameDetails[] = playerdata.getGameDetails().get(betTypeRowNo);

		// betting config...need to read from excel as test data
		String betAmount = gameDetails[8];
		String wagerSource = gameDetails[9];

		String betslipElementXpath = "//*[text()='Betslip']";
		CCLMobile.clickExceptionMethod(betslipElementXpath);

		// giving wagering amount
		if(betType.equals("Teaser")) {
			String teaserButtonElementXpath = "//*[text()='Teaser']";
			CCLMobile.clickExceptionMethod(teaserButtonElementXpath);
		}
		
		String riskElementXpath = "//*[text()='Risk']/following-sibling::input";
		CCLMobile.clickExceptionMethod(riskElementXpath);

		for (int i = 0; i < betAmount.length(); i++) {
			char digit = betAmount.charAt(i);
			driver.findElement(By.xpath("//button[text()='" + digit + "']")).click();
		}

		// Selecting wager source
		driver.findElement(By.xpath("//*[text()='Fund Your Bet']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='WAGER SOURCE']")));

		boolean isPlayerBankOptionEnabled = driver
				.findElement(
						By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/preceding-sibling::label/input"))
				.isEnabled();
		boolean isroomChargeOptionEnabled = driver
				.findElement(By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/input")).isEnabled();

		boolean isPlayerBankOptionSelected = driver
				.findElement(
						By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/preceding-sibling::label/input"))
				.isSelected();
		boolean isroomChargeOptionSelected = driver
				.findElement(By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/input")).isSelected();

		String temp_PlayerBankBalance = driver
				.findElement(By.xpath(
						"//*[contains(text(), 'Room Charge')]/parent::label/preceding-sibling::label/span[2]/span"))
				.getText();
		String temp_1PlayerBankBalance = temp_PlayerBankBalance.substring(1);
		double PlayerBankBalance = Double.parseDouble(temp_1PlayerBankBalance);

		if (wagerSource.equals("Player Bank")) {
			if (isPlayerBankOptionEnabled && isPlayerBankOptionSelected) {
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Wager source - Player bank");
			} else if (isPlayerBankOptionEnabled && !isPlayerBankOptionSelected) {
				driver.findElement(
						By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/preceding-sibling::label/input"))
						.click();
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Wager source - Player bank");
			} else if (PlayerBankBalance < Integer.parseInt(betAmount)) {
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Player bank wager source disabled due to insufficient balance(" + PlayerBankBalance
						+ "), Selecting - Room Charge as wager source");
			} else {
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Player bank wager source disabled, Selecting - Room Charge as wager source");
			}
		}

		if (wagerSource.equals("Room Charge")) {
			if (isroomChargeOptionEnabled && isroomChargeOptionSelected) {
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Wager source - Room Charge");
			} else if (isroomChargeOptionEnabled && !isroomChargeOptionSelected) {
				driver.findElement(By.xpath("//*[contains(text(), 'Room Charge')]/parent::label/input")).click();
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Wager source - Room Charge");
			} else {
				driver.findElement(By.xpath("//*[text()='continue']")).click();
				System.out.println("Room charge wager source is disabled selecting - Player bank as a wager source");
			}
		}

		// giving Casino pin
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='CONFIRM CASINO PIN']")));
		driver.findElement(By.xpath("//*[text()=' Enter Carnival Casino PIN ']/parent::div/descendant::input")).click();
		for (int i = 0; i < CCLPin.length(); i++) {
			int digit = CCLPin.charAt(i) - '0';
			driver.findElement(By.xpath("//button[text()='" + digit + "']")).click();
		}
		driver.findElement(By.className("key-ok")).click();

		// confirming wager
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(text(), 'Bet Confirmation')]")));

		// wager details
		System.out.println("Wager details: ");
		String RiskAmount = driver.findElement(By.xpath("//*[text()='Risk']/following-sibling::p")).getText();
		String PotWinnings = driver.findElement(By.xpath("//*[text()='Pot. winnings']/following-sibling::p")).getText();

		System.out.println("Risk: " + RiskAmount + ",  Pot. winning: " + PotWinnings);
		System.out.println();

		driver.findElement(By.xpath("//*[text()='Confirm Wagers']")).click();
		try {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(text(), 'Bet Receipt')]")));
			System.out.println(driver
					.findElement(By.xpath(
							"//*[contains(text(), 'Bet Receipt')]/parent::div/parent::div/preceding-sibling::div/p"))
					.getText());
			String BetDetails = driver.findElement(By.className("betslip-id")).getText();
			System.out.println("Bet Details: " + BetDetails);
			System.out.println();

			// upadating the test result in TestCases file
			
			if(betType.equals("Straight")) {
				testResult.updateResult("Pass", 2);
			}
			if(betType.equals("Parlay")) {
				testResult.updateResult("Pass", 4);
			}
			if(betType.equals("Teaser")) {
				testResult.updateResult("Pass", 6);
			}

			String BetDate = BetDetails.substring(6, 18);
			String betTime = BetDetails.substring(20, 30);
			String BetSlipId = BetDetails.substring(44);
			String betmonth = BetDetails.substring(6, 9);

			int profileMenuclickableFlag = 0;
			while (profileMenuclickableFlag == 0) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.id("profile-menu-focus")).click();
					profileMenuclickableFlag = 1;
				} catch (ElementClickInterceptedException e) {
					profileMenuclickableFlag = 0;
				}
			}

			String transactionHistoryElementXpath = "//*[text()=' Transaction History ']";
			CCLMobile.clickExceptionMethod(transactionHistoryElementXpath);

			String transactionDropdownElementXpath = "//*[contains(text(), '" + betmonth + "')]";
			CCLMobile.clickExceptionMethod(transactionDropdownElementXpath);

			try {
				driver.findElement(By.xpath("//*[contains(text(), '" + BetSlipId + "')]"));
				System.out.println("Transaction recorded in history.");
				// upadating the test result in TestCases file
				if(betType.equals("Straight")) {
					testResult.updateResult("Pass", 3);
				}
				if(betType.equals("Parlay")) {
					testResult.updateResult("Pass", 5);
				}
				if(betType.equals("Teaser")) {
					testResult.updateResult("Pass", 7);
				}
			} catch (NoSuchElementException e) {
				System.out.println("Unable to find transaction");
			}

		} catch (NoSuchElementException e) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='Login Required' or text()='Error']")));
			System.out.println("Unable to place bet");
			System.out.println("Error : " + driver
					.findElement(By.xpath(
							"//*[text()='Login Required']/parent::div/following-sibling::div/child::div/child::p"))
					.getText());
			driver.findElement(By.xpath("//*[text()='Ok' or text()='OK']")).click();

			// upadating the test result in TestCases file
			testResult.updateResult("Fail", 2);
		}
	}

	@Test
	public void logout() throws InterruptedException {
		int profileMenuclickableFlag = 0;
		while (profileMenuclickableFlag == 0) {
			try {
				Thread.sleep(1000);
				driver.findElement(By.id("profile-menu-focus")).click();
				profileMenuclickableFlag = 1;
			} catch (ElementClickInterceptedException e) {
				profileMenuclickableFlag = 0;
			}
		}

		driver.findElement(By.xpath("//*[contains(text(), ' Log Out ')]")).click();
		System.out.println("Closing the session...");
		System.out.println("Logged out successfully.");
		System.out.println();
		System.out.println();
	}

	@AfterTest
	public void AfterTest() throws InterruptedException {

		driver.quit();
	}

}
