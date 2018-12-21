package com.softserve.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestRunner {
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	// protected boolean isTestSuccess;

	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("@BeforeClass");
		System.out.println("PATH to Driver: " + this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
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
		// isTestSuccess = false;
		System.out.println("@BeforeMethod");
		driver.get("http://192.168.103.172/opencart/upload/");
	}

	@AfterMethod // (alwaysRun = true)
	public void afterMethod(ITestResult result) {
		// if (isTestSuccess) {
		if (result.isSuccess()) {
			System.out.println("+++test " + result.getName() + " done");
		} else {
			System.out.println("***test " + result.getName() + " failed" + "\n***\t" + result.getThrowable().toString());
			String fileNamePrefix = makeFileNamePrefix();
			takeScreenshot(fileNamePrefix);
			takePageSource(fileNamePrefix);
		}
		// logoutApplication();
		delayExecution(2);
		System.out.println("@AfterMethod");
	}

	// protected HomePage loadApplication() {
	// return new HomePage(driver);
	// }

	// protected HomePage logoutApplication() {
	// return new HeadComponent(driver).gotoHomeWithLogout();
	// return (new AHeadComponent(driver){}).gotoHomeWithLogout();
	// }

	protected void delayExecution(int seconds) {
		try {
			Thread.sleep(seconds * 1000L); // Do not use
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void takeScreenshot(String fileNamePrefix) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("./" + fileNamePrefix + "_screenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void takePageSource(String fileNamePrefix) {
		try {
			PrintWriter w = new PrintWriter(new File("./" + fileNamePrefix + "_pagesource.txt"));
			w.print(driver.getPageSource());
			w.flush();
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String makeFileNamePrefix() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss");
		return simpleDateFormat.format(new Date());
	}
}
