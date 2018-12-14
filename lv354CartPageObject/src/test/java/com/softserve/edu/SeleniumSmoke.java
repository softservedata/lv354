package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumSmoke {

	@Test
	public void testSeleniumSmoke() throws Exception {
		System.out.println("PATH: " 
				+ SeleniumSmoke.class.getResource("/chromedriver-windows-32bit.exe").getPath());
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
				//SeleniumSmoke.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		driver.get("https://www.google.com/");
		Thread.sleep(1000); // Do not use
		driver.findElement(By.id("lst-ib")).clear();
		Thread.sleep(1000); // Do not use
		//driver.findElement(By.id("lst-ib")).sendKeys("selenium" + Keys.ENTER);
		driver.findElement(By.id("lst-ib")).sendKeys("selenium");
		driver.findElement(By.id("lst-ib")).submit();
		//
		System.out.println("Google First TITLLE: " + driver.getTitle());
		//
		Thread.sleep(1000); // Do not use
		System.out.println("Google Second TITLLE: " + driver.getTitle());
		//
		//driver.findElement(By.id("mKlEF")).click();
		Thread.sleep(1000); // Do not use
		driver.findElement(By.xpath("//a/h3[text()='Selenium - Web Browser Automation']/..")).click();
		Thread.sleep(1000); // Do not use
		driver.findElement(By.linkText("Download")).click();
		System.out.println("Download TITLLE: " + driver.getTitle());
		Thread.sleep(1000); // Do not use
		//
		WebElement linkSelenIDE = driver.findElement(By.xpath("//a[contains(text(),'previous IDE versions here')]"));
		Actions action = new Actions(driver);		
		action.moveToElement(linkSelenIDE).perform();
		Thread.sleep(1000); // Do not use
		Assert.assertEquals(
				"Selenium IDE is a Chrome and Firefox plugin which records and plays back user interactions with the browser. Use this to either create simple scripts or assist in exploratory testing.",
				driver.findElement(By.cssSelector("a[name=\"selenium_ide\"] > p")).getText());
		Thread.sleep(3000);
		driver.quit();
	}

}
