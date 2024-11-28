package com.appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class RealDeviceAutomation {

	@Test
	public void realDevice() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("deviceName", "One Plus 11");
		caps.setCapability("platformVersion", "13");
		caps.setCapability("platform", "Android");
		caps.setCapability("platformVersion", "13");
		caps.setCapability("udid", "13");
		
		caps.setCapability("appPackage", "13");
		caps.setCapability("appActivity", "13");
		
		URL url = new URL("@mobile-hub.lambdatest.com/wd/hub");
		
		AppiumDriver<MobileElement> driver = new AppiumDriver<MobileElement>(url, caps);
		
		
	}
}
