package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.pages.HomeFactoryPage;

public class TestFactoryRunner {

	protected WebDriver driver;
	protected final double PRECISION = 0.001;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("@BeforeClass");
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		//
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("@AfterClass");
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod");
		driver.get("http://192.168.103.172/opencart/upload/");
	}

	@AfterMethod // (alwaysRun = true)
	public void afterMethod(ITestResult result) {
		System.out.println("@AfterMethod");
		if (result.isSuccess()) {
			System.out.println("+++test " + result.getName() + " done");
		} else {
			System.out.println("***test " + result.getName() + " failed");
		}
	}

	protected HomeFactoryPage loadApplication() {
		//return new HomeFactoryPage(driver); // 1st item
		return PageFactory.initElements(driver, HomeFactoryPage.class); // 2nd item
	}

	protected void delayExecution(int seconds) {
		try {
			Thread.sleep(seconds * 1000L); // Do not use
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
