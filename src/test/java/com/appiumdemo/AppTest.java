package com.appiumdemo;


import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
//import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class AppTest {
	
	
	
	@BeforeSuite
	public void BeforeSuit() {
		
		System.out.print("I'm in BeforeSuit");
	}
	
	public static String userName = "rushikeshpotadar_OrpBGc";
	public static String accessKey = "ZyyUN4dPf4pUPWwVmjpH";
	DesiredCapabilities caps = new DesiredCapabilities();
	@Test
	public void Test() throws MalformedURLException {
		
		
		
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", "Samsung Galaxy S22");
		caps.setCapability("browserstack.local", "true");
//		caps.bsLocalArgs.put("forcelocal", "true");
	    caps.setCapability("acceptSslCerts", "true");
	    caps.setCapability("os_version", "12.0");
	    caps.setCapability("project", "My First Project");
	    caps.setCapability("build", "My First Build");
	    caps.setCapability("name", "Bstack-[Java] Sample Test");  
	    caps.setCapability("app", "bs://3b5ba66ec88d61f7025b1d48d5413ed050332b98");
		
	    
	    AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
	    
//		
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	    
	    driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]").click();
//	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
//	    driver.findElement(By.name("LOGIN")).click();
	    
	    
	    
	    
	    
//	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	    
	    driver.quit();
	    
	    
	    
		
	}
	
	@AfterSuite
	public void AfterSuit() {
		
		System.out.print("I'm in AfterSuit");
	}
	
	
	
	
}