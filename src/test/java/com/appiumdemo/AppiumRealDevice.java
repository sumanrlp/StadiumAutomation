package com.appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumRealDevice {
	
	static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException {
		
		
		StartApp();

	}
	
	public static void StartApp() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("deviceName", "Samsung M21");
		caps.setCapability("udid", "RZ8N61K8ZVD");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "12");
		caps.setCapability("appPackage", "com.miui.calculator");
		caps.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver<>(url, caps);
		
	}

}
