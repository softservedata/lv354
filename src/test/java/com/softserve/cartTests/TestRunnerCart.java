package com.softserve.cartTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestRunnerCart {
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("@AfterClass");
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod");
		driver.get("http://localhost:8888/opencart/upload/");	
		emptyCart();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		  if (result.isSuccess()) {
			System.out.println("+++TEST " + result.getName() + " SUCCESS!");
		} else {
			System.out.println("***TEST " + result.getName() + " FAILED" + "\n***\t" + result.getThrowable().toString());
			String fileNamePrefix = makeFileNamePrefix();
			takeScreenshot(fileNamePrefix);
			takePageSource(fileNamePrefix);
		}
		delayExecution(2);
		System.out.println("@AfterMethod");
	}
	protected void delayExecution(int seconds) {
		try {
			Thread.sleep(seconds * 1000L); // Do not use
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void takeScreenshot(String fileNamePrefix) {
		//zooming out
		WebElement wb=driver.findElement(By.tagName("html"));
		wb.sendKeys(Keys.chord(Keys.CONTROL,"-"));
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//zooming in
		wb.sendKeys(Keys.chord(Keys.CONTROL,"+"));
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
	

	protected void emptyCart () {
		//do while cart is not empty
		int size=0;
		do {	
			
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated
					(By.id("cart-total")));
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			driver.findElement(By.id("cart-total")).click();
			size=driver.findElements(By.cssSelector(".btn.btn-danger.btn-xs")).size();
			System.out.println("Size = "+size);
		if (size != 0) {
			driver.findElement(By.cssSelector(".btn.btn-danger.btn-xs")).click();
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.
					invisibilityOfElementLocated(By.cssSelector(".btn.btn-danger.btn-xs")));
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			size--;
			}			}
		while (size != 0);					
	}
		
}
