package com.softserve.edu;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

class MyExpectedCondition implements ExpectedCondition<Boolean> {
	public Boolean apply(WebDriver driver) {
		return driver.getTitle().toLowerCase().startsWith("selenium - yahoo");
	}
}

public class OpenCartTest {

	// @Test
	public void testOpenCartSmoke() throws Exception {
		System.out.println("PATH: " + SeleniumSmoke.class.getResource("/chromedriver-windows-32bit.exe").getPath());
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		// driver.get("http://taqc-opencart.epizy.com/");
		driver.navigate().to("http://taqc-opencart.epizy.com/");
		//
		System.out.println("Opencart First TITLLE: " + driver.getTitle());
		Thread.sleep(5000); // Do not use
		System.out.println("Opencart Second TITLLE: " + driver.getTitle());
		//
		driver.findElement(By.cssSelector(".form-control.input-lg")).click();
		driver.findElement(By.cssSelector("#top-links a.dropdown-toggle")).click();
		driver.findElement(By.xpath("//div[@id='top-links']//a[contains(@href,'route=account/register')]")).click();
		//
		System.out.println("Opencart First TITLLE: " + driver.getTitle());
		Thread.sleep(5000); // Do not use
		System.out.println("Opencart Second TITLLE: " + driver.getTitle());
		//
		Thread.sleep(4000);
		driver.quit();
	}

	// @Test
	public void testGoogleSmoke() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		driver.get("https://www.google.com/");
		Thread.sleep(1000); // Do not use
		driver.findElement(By.id("lst-ib")).clear();
		Thread.sleep(1000); // Do not use
		// driver.findElement(By.id("lst-ib")).sendKeys("selenium" + Keys.ENTER);
		driver.findElement(By.id("lst-ib")).sendKeys("selenium" + Keys.ENTER);
		// driver.findElement(By.id("lst-ib")).submit();
		System.out.println("Google First TITLLE: " + driver.getTitle());
		//
		Thread.sleep(4000);
		driver.quit();
	}

	// @Test
	public void testYahooSmoke() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		driver.get("https://www.yahoo.com/");
		// Thread.sleep(1000); // Do not use
		driver.findElement(By.id("uh-search-box")).clear();
		driver.findElement(By.id("uh-search-box")).sendKeys("selenium" + Keys.ENTER);
		//
		// System.out.println("Yahoo First TITLLE: " + driver.getTitle());
		// Thread.sleep(1000); // Do not use
		// System.out.println("Yahoo Second TITLLE: " + driver.getTitle());
		//
//		WebDriverWait myWait = new WebDriverWait(driver, 30);
//		MyExpectedCondition myExpectedCondition = new MyExpectedCondition();
//		myWait.until(myExpectedCondition);
		//
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().toLowerCase().startsWith("selenium - yahoo");
			}
		});
		//
		System.out.println("Yahoo TITLLE: " + driver.getTitle());
		Thread.sleep(4000);
		driver.quit();
	}

	@Test // Verify Web Elements
	public void searchWebElements() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		//
		//System.setProperty("webdriver.gecko.driver",
		//		"C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		//
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		//driver.get("http://regres.herokuapp.com/login");
		driver.get("https://www.google.com");
		Thread.sleep(1000); // DO NOT USE
		//
		// Search WebElements
		//WebElement login = driver.findElement(By.id("login")); // By implicit
		//WebElement login = (new WebDriverWait(driver, 10)).until(
		//		ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		//WebElement login = (new WebDriverWait(driver, 10)).until(
		//		ExpectedConditions.presenceOfElementLocated(By.id("login")));
		//
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement login = (WebElement) js.executeScript("return document.getElementsByName('login')[0];");
		//
		//login.click();
		//login.clear();
		//login.sendKeys("Hello");
		//
		// Run Alert, Switch to Alert.
		//js.executeScript("alert('Hello!');");
		//Thread.sleep(4000); // DO NOT USE
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		//
		// Resize Window (not working chrome)
		//js.executeScript("window.resizeTo(512, 384);");
		//
		// Move to element
		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys("selenium ide download" + Keys.ENTER);
		Thread.sleep(2000); // DO NOT USE
		// Use Selenium
		//WebElement next = driver.findElement(By.xpath("//span[contains(text(),'Next')]")); // For
		// Localization en.
		WebElement next = driver.findElement(By.xpath("//a[contains(text(),'10')]"));
//		Actions action = new Actions(driver);
//		action.moveToElement(next).perform();
		//
		// Use JavaScript Injection
		//js.executeScript("arguments[0].scrollIntoView(true);", next);
		//
		//Thread.sleep(4000); // DO NOT USE
		//
		// Take Screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./screenshot.png"));
		//
		// Save html code.
		PrintWriter w = new PrintWriter(new File("./screenshot.txt"));
		w.print(driver.getPageSource());
		w.flush();
		w.close();
		//
		Thread.sleep(4000); // DO NOT USE
		driver.quit();
	}

}
