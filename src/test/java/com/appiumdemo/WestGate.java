package com.appiumdemo;

import static org.testng.Assert.assertEquals;

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
import org.testng.annotations.*;
import org.testng.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class WestGate {

	public static String userName = "rushikesh.potadar";
	public static String accessKey = "ImLmORDUcmxd6Q2U7dRJfsqO8ldfE6prSY2JzaplMIw1dstAK1";

	static AppiumDriver driver;

	ExcelReadingData TestData = new ExcelReadingData();
	ExcleWriting testResult = new ExcleWriting();

	@BeforeTest
	public void beforeTest() throws IOException {

	}

	public static void clickExceptionMethod(String webelement) throws InterruptedException {
		int oddCLickableFlag = 0;
		int timeLimit = 0;
		while (oddCLickableFlag == 0 && timeLimit < 10) {
			if (timeLimit < 25) {
				try {
					Thread.sleep(200);
					driver.findElement(By.xpath(webelement)).click();
					oddCLickableFlag = 1;
				} catch (ElementClickInterceptedException | NoSuchElementException e) {
					oddCLickableFlag = 0;
					timeLimit++;
				}
			}

		}
	}

	@Test
	public void launchingApp() throws InterruptedException, IOException {

		try {

			UiAutomator2Options options = new UiAutomator2Options();
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("w3c", true);
			ltOptions.put("platformName", "android");
			ltOptions.put("deviceName", "OnePlus 11");
			ltOptions.put("platformVersion", "13");
			ltOptions.put("app", "lt://APP1016025291730879373476348");
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
					new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"), options);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			System.out.println("Launching the app");
			System.out.println("----------------------------");

			try {
				driver.findElement(By.className("android.widget.Button")).click();
			} catch (NoSuchElementException e) {

			}

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));

				assertEquals(driver.findElement(By.xpath("//*[@text='LOG IN']")).isDisplayed(), true);
				System.out.println("App Launched Successfully");
				System.out.println("--------------------------------");

				// upadating the test result in TestCases file
				testResult.updateResult("Pass", 1);
			} catch (Exception e) {
				System.out.println("Failed to Launch the App");
				System.out.println("------------------------------");
				// upadating the test result in TestCases file
				testResult.updateResult("Fail", 1);
			}
		} catch (AssertionError a) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			a.printStackTrace();
		}
	}

	// Page 1 - Home Page ... But1 - Login But 2 - Proceed , But3 Registration
	// button ... Values .. Text or

	@Test
	public void playerLogin() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Map<String, String> playerData = new LinkedHashMap<>();
		Map<Integer, String[]> testData = new LinkedHashMap<>();
		playerData = TestData.getPlayerData();
		testData = TestData.getTestData();

		int playerNo = Integer.parseInt(testData.get(1)[2]);

		String EmailAddress = (String) playerData.keySet().toArray()[playerNo - 1];
		String Pass = playerData.get(EmailAddress);

		System.out.println("PLayer info: Email-" + EmailAddress + "  Pass-" + Pass);

		WebElement InnputEmail_or_AccountNo = driver.findElement(By.xpath("//*[@text='Account number']"));

		InnputEmail_or_AccountNo.sendKeys(EmailAddress);

		WebElement Innput_Pass = driver.findElement(By.xpath("//*[@text='Password']"));

		Innput_Pass.sendKeys(Pass);

		driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Home']")));

			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.TextView[2][@index='3']")));

			System.out.println(
					"Welcome " + driver.findElement(By.xpath("//android.widget.TextView[2][@index='3']")).getText());
			System.out.println("------------------------------");

			// upadating the test result in TestCases file
			testResult.updateResult("Pass", 2);

		} catch (NoSuchElementException e) {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Login Failed']"));
			System.out.println("Invalid Accout no. or Password");

			// upadating the test result in TestCases file
			testResult.updateResult("Fail", 2);
		}

	}

	@Test
	public void placingBet(String BetType, int noOfSelections, String betAmount) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		System.out.println("Placing bet " + BetType + " ...");
		System.out.println("----------------------------------");

		try {

			Thread.sleep(3000);

			driver.findElement(By.xpath(
					"//*[@class='android.widget.FrameLayout' and ./parent::*[@resource-id='com.stadium.westgate:id/navigationlayout_content']]/*"
							+ "[@class='android.widget.FrameLayout']/*[@class='android.view.ViewGroup']/*[@class='android.view.ViewGroup']/*[1]/*"
							+ "[@class='android.view.ViewGroup']/*[2]/*[1]/*[@class='android.view.View']"))
					.click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='Bet Slip']")));

			switch (BetType) {
			case "PARLAY":
				driver.findElement(By.xpath("//*[@text=\"PARLAY\"]")).click();
				break;
			case "TEASER":
				driver.findElement(By.xpath("//*[@text=\"TEASER\"]")).click();
				break;
			case "ROUND ROBIN":
				driver.findElement(By.xpath("//*[@text=\"PARLAY\"]")).click();
				driver.findElement(By.xpath("//*[@text=\"1 Parlay of 3 Teams\"]")).click();
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text=\"3 Parlays of 2 Teams\"]")));
				driver.findElement(By.xpath("//*[@text=\"3 Parlays of 2 Teams\"]")).click();
				break;

			default:
				break;
			}

			if (BetType.equals("PARLAY") || BetType.equals("ROUND ROBIN")) {
				driver.findElement(By.xpath("//*[@class=\"android.widget.EditText\"]")).sendKeys(betAmount);
			} else {
				driver.findElement(By.xpath(
						"//*[@class=\"android.widget.EditText\" and ./following-sibling::*[@text=\"Risk amount\"]]"))
						.sendKeys(betAmount);
			}

			if (driver.findElement(By.xpath("//*[@text=\"PLACE BET\"]")).isEnabled()) {
				try {
					driver.findElement(By.xpath("//*[@text=\"PLACE BET\"]")).click();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					Assert.fail(e.getMessage());
				}
			} else {
				try {
					String Msg = driver.findElement(By.xpath("//*[contains(@text, 'Max') or contains(@text, 'Min')]"))
							.getText();
					System.out.println("Could not place bet.");
					System.out.println(Msg);
					driver.findElement(By.xpath("//*[@text=\"CLEAR\"]")).click();
					driver.navigate().back();
					driver.navigate().back();
					
					switch (BetType) {
					case "STRAIGHT BET":
						// upadating the test result in TestCases file
						testResult.updateResult("Fail", 3);
						break;
					case "PARLAY":
						// upadating the test result in TestCases file
						testResult.updateResult("Fail", 4);
						break;
					case "ROUND ROBIN":
						// upadating the test result in TestCases file
						testResult.updateResult("Fail", 6);
						break;
					case "TEASER":
						// upadating the test result in TestCases file
						testResult.updateResult("Fail", 5);
						break;

					default:
						break;
					}
					
					Assert.fail(Msg);
				} catch (Exception e) {
					Assert.fail("Failed");
				}
			}

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text=\"Confirm Bets\"]")));

			driver.findElement(By.xpath("//*[@text=\"PLACE WAGERS\"]")).click();

			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text=\"Place Bet Results\"]")));

			String TicketNo = "";
			if (BetType.equals("ROUND ROBIN")) {
				TicketNo = driver
						.findElement(
								By.xpath("//*[@class='android.view.ViewGroup' and ./*[@text='" + BetType + "']]/*[5]"))
						.getText();
			} else {
				TicketNo = driver
						.findElement(
								By.xpath("//*[@class='android.view.ViewGroup' and ./*[@text='" + BetType + "']]/*[4]"))
						.getText();
			}

			System.out.println(BetType + " :" + TicketNo);

			driver.navigate().back();
			driver.findElement(By.xpath("//*[@text=\"CLEAR\"]")).click();
			driver.navigate().back();
			driver.navigate().back();

			switch (BetType) {
			case "STRAIGHT BET":
				// upadating the test result in TestCases file
				testResult.updateResult("Pass", 3);
				break;
			case "PARLAY":
				// upadating the test result in TestCases file
				testResult.updateResult("Pass", 4);
				break;
			case "ROUND ROBIN":
				// upadating the test result in TestCases file
				testResult.updateResult("Pass", 6);
				break;
			case "TEASER":
				// upadating the test result in TestCases file
				testResult.updateResult("Pass", 5);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Test
	public void StraightBetConfig() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		String gameDetails[] = TestData.getGameDetails().get(1);

		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='SPORTS PREGAME']")));

			driver.findElement(By.xpath("//*[@text='SPORTS PREGAME']")).click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + sports + "']")));

			String SportsNameXpath = "//*[@text='" + sports + "']";

			WestGate.clickExceptionMethod(SportsNameXpath);

			String LeagueNameXpath = "//*[@text='" + leaugueName
					+ "' and ./preceding-sibling::*[@class='android.view.View']]";

			WestGate.clickExceptionMethod(LeagueNameXpath);

			// betting config...need to read from excel as test data
			addingOddsToBetslip(TestData.getGameDetails().get(1));

			String betAmount = TestData.getGameDetails().get(1)[9];

			placingBet("STRAIGHT BET", 1, betAmount);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void ParlayBetConfig() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));

		String gameDetails[] = TestData.getGameDetails().get(2);

		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='SPORTS PREGAME']")));

			driver.findElement(By.xpath("//*[@text='SPORTS PREGAME']")).click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + sports + "']")));

			String SportsNameXpath = "//*[@text='" + sports + "']";

			WestGate.clickExceptionMethod(SportsNameXpath);

			String LeagueNameXpath = "//*[@text='" + leaugueName
					+ "' and ./preceding-sibling::*[@class='android.view.View']]";

			WestGate.clickExceptionMethod(LeagueNameXpath);

			// betting config...need to read from excel as test data
			String NoOfTeams = gameDetails[2];

			for (int i = 2; i < 2 + Integer.parseInt(NoOfTeams); i++) {
				addingOddsToBetslip(TestData.getGameDetails().get(i));
			}

			String betAmount = TestData.getGameDetails().get(2)[9];

			placingBet("PARLAY", 2, betAmount);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void Round_robinBetConfig() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		String parlayNoOfrows = TestData.getGameDetails().get(2)[2];
		int startingRowNoofTeaser = Integer.parseInt(parlayNoOfrows) + 2;
		String teaserNoOfRows = TestData.getGameDetails().get(startingRowNoofTeaser)[2];
		int startingRowNoOfRound_robin = Integer.parseInt(teaserNoOfRows) + Integer.parseInt(parlayNoOfrows) + 2;
		String gameDetails[] = TestData.getGameDetails().get(startingRowNoOfRound_robin);

		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='SPORTS PREGAME']")));

			driver.findElement(By.xpath("//*[@text='SPORTS PREGAME']")).click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + sports + "']")));

			String SportsNameXpath = "//*[@text='" + sports + "']";

			WestGate.clickExceptionMethod(SportsNameXpath);

			String LeagueNameXpath = "//*[@text='" + leaugueName
					+ "' and ./preceding-sibling::*[@class='android.view.View']]";

			WestGate.clickExceptionMethod(LeagueNameXpath);

			// betting config...need to read from excel as test data
			String NoOfTeams = gameDetails[2];

			for (int i = startingRowNoOfRound_robin; i < startingRowNoOfRound_robin
					+ Integer.parseInt(NoOfTeams); i++) {
				addingOddsToBetslip(TestData.getGameDetails().get(i));
			}

			String betAmount = TestData.getGameDetails().get(2)[9];

			placingBet("ROUND ROBIN", 2, betAmount);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void TeaserBetConfig() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		String parlayNoOfrows = TestData.getGameDetails().get(2)[2];
		int startingRowNo = Integer.parseInt(parlayNoOfrows) + 2;
		String gameDetails[] = TestData.getGameDetails().get(startingRowNo);

		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='SPORTS PREGAME']")));

			driver.findElement(By.xpath("//*[@text='SPORTS PREGAME']")).click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + sports + "']")));

			String SportsNameXpath = "//*[@text='" + sports + "']";

			WestGate.clickExceptionMethod(SportsNameXpath);

			String LeagueNameXpath = "//*[@text='" + leaugueName
					+ "' and ./preceding-sibling::*[@class='android.view.View']]";

			WestGate.clickExceptionMethod(LeagueNameXpath);

			// betting config...need to read from excel as test data
			String NoOfTeams = gameDetails[2];

			// betting config...need to read from excel as test data
			for (int i = startingRowNo; i < startingRowNo + Integer.parseInt(NoOfTeams); i++) {
				addingOddsToBetslip(TestData.getGameDetails().get(i));
			}

			String betAmount = TestData.getGameDetails().get(startingRowNo)[9];

			placingBet("TEASER", startingRowNo, betAmount);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void TeaserBetWithWagerLimitOver() throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		String parlayNoOfrows = TestData.getGameDetails().get(2)[2];
		int startingRowNo = Integer.parseInt(parlayNoOfrows) + 2;
		String gameDetails[] = TestData.getGameDetails().get(startingRowNo);

		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='SPORTS PREGAME']")));

		driver.findElement(By.xpath("//*[@text='SPORTS PREGAME']")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + sports + "']")));

		String SportsNameXpath = "//*[@text='" + sports + "']";

		try {
			WestGate.clickExceptionMethod(SportsNameXpath);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String LeagueNameXpath = "//*[@text='" + leaugueName
				+ "' and ./preceding-sibling::*[@class='android.view.View']]";

		try {
			WestGate.clickExceptionMethod(LeagueNameXpath);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// betting config...need to read from excel as test data
		String NoOfTeams = gameDetails[2];

		// betting config...need to read from excel as test data
		for (int i = startingRowNo; i < startingRowNo + Integer.parseInt(NoOfTeams); i++) {
			try {
				addingOddsToBetslip(TestData.getGameDetails().get(i));
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String betAmount = TestData.getGameDetails().get(startingRowNo)[9];

		String BetType = "TEASER";

		System.out.println("Placing bet " + BetType + " ...");
		System.out.println("----------------------------------");

		try {

			Thread.sleep(3000);

			driver.findElement(By.xpath(
					"//*[@class='android.widget.FrameLayout' and ./parent::*[@resource-id='com.stadium.westgate:id/navigationlayout_content']]/*"
							+ "[@class='android.widget.FrameLayout']/*[@class='android.view.ViewGroup']/*[@class='android.view.ViewGroup']/*[1]/*"
							+ "[@class='android.view.ViewGroup']/*[2]/*[1]/*[@class='android.view.View']"))
					.click();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='Bet Slip']")));

			switch (BetType) {
			case "PARLAY":
				driver.findElement(By.xpath("//*[@text=\"PARLAY\"]")).click();
				break;
			case "TEASER":
				driver.findElement(By.xpath("//*[@text=\"TEASER\"]")).click();
				break;
			case "ROUND ROBIN":
				driver.findElement(By.xpath("//*[@text=\"PARLAY\"]")).click();
				driver.findElement(By.xpath("//*[@text=\"1 Parlay of 3 Teams\"]")).click();
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text=\"3 Parlays of 2 Teams\"]")));
				driver.findElement(By.xpath("//*[@text=\"3 Parlays of 2 Teams\"]")).click();
				break;

			default:
				break;
			}

			if (BetType.equals("PARLAY") || BetType.equals("ROUND ROBIN")) {
				driver.findElement(By.xpath("//*[@class=\"android.widget.EditText\"]")).sendKeys(betAmount);
			} else {
				driver.findElement(By.xpath(
						"//*[@class=\"android.widget.EditText\" and ./following-sibling::*[@text=\"Risk amount\"]]"))
						.sendKeys(betAmount);
			}

			if (driver.findElement(By.xpath("//*[@text=\"PLACE BET\"]")).isEnabled()) {
				System.out.println("Test failed Place bet button is enabled with more than wager limit bet amount");
				// upadating the test result in TestCases file
				testResult.updateResult("Fail", 8);
				Assert.fail("Failed");
			} else {
				try {
					String Msg = driver.findElement(By.xpath("//*[contains(@text, 'Max') or contains(@text, 'Min')]"))
							.getText();
					System.out.println(Msg);
					driver.findElement(By.xpath("//*[@text=\"CLEAR\"]")).click();
					driver.navigate().back();
					driver.navigate().back();
					// upadating the test result in TestCases file
					testResult.updateResult("Pass", 8);

				} catch (Exception e) {
					Assert.fail("Failed");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void addingOddsToBetslip(String gameDetails[]) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		// betting config...need to read from excel as test data
		String sports = gameDetails[0];
		String leaugueName = gameDetails[1];
		String NoOfTeams = gameDetails[2];
		String BetType = gameDetails[3];
		String gameName = gameDetails[4];
		String lineTypeId = gameDetails[7];
		String bettingTeamId = gameDetails[8];

		// adding bets to betslip
		String oddElementXpath = "//*[@class='android.view.ViewGroup' and ./preceding-sibling::*[@class='android.view.ViewGroup' and ./child::*[@class='android.view.ViewGroup' and ./"
				+ "*[@text='" + gameName + "']]]]/*[" + lineTypeId + "]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oddElementXpath)));
		try {
			driver.findElement(By.xpath(oddElementXpath));

		} catch (Exception e) {
			try {
				String targetText = gameName;
				driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + targetText + "\"));"));

			} catch (Exception e2) {
				System.out.println("Erros:  " + e2.getMessage());
			}
		}
		WestGate.clickExceptionMethod(oddElementXpath);
	}

	@Test
	public void logout() throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		try {
			driver.findElement(By.xpath("//*[@content-desc=\"Open navigation drawer\"]")).click();

			String SignOutButtonXpath = "//*[@text=\"Sign Out\"]";

			WestGate.clickExceptionMethod(SignOutButtonXpath);

			// upadating the test result in TestCases file
			testResult.updateResult("Pass", 7);

			driver.quit();

		} catch (Exception e) {
			// upadating the test result in TestCases file
			testResult.updateResult("Fail", 7);
			driver.quit();
		}

	}

}
