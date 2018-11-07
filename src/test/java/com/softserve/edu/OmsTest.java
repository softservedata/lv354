package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OmsTest {

	@Test
	public void searchIva() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		driver.get("http://ssu-oms.training.local:8180/OMS/login.htm");
		Thread.sleep(1000); // Do not use
		driver.findElement(By.name("j_username")).click();
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("iva");
		driver.findElement(By.name("j_password")).click();
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("qwerty");
		Thread.sleep(1000); // Do not use
		driver.findElement(By.name("submit")).submit();
		Thread.sleep(1000); // Do not use
		driver.findElement(By.xpath("//a[contains(@href, '/users.htm')]")).click();
		Thread.sleep(1000); // Do not use
		//
		Select field = new Select(driver.findElement(By.id("field")));
		field.selectByVisibleText("Login Name");
		Select condition = new Select(driver.findElement(By.id("condition")));
		condition.selectByVisibleText("starts with"); 
		Thread.sleep(1000); // Do not use
		//
		driver.findElement(By.id("searchField")).click();
		driver.findElement(By.id("searchField")).clear();
		driver.findElement(By.id("searchField")).sendKeys("iva" + Keys.ARROW_RIGHT);
		Thread.sleep(2000); // Do not use
		//
		driver.findElement(By.id("next")).click();
		//Thread.sleep(2000); // Do not use
		//
		WebElement ivan = driver.findElement(By.xpath("//td[text()='ivan']/following-sibling::td[1]")); 
		//String actual = driver.findElement(By.xpath("//td[text()='ivan']/following-sibling::td[2]")).getText();
		String actual = ivan.getText(); 
		//System.out.println("actual0 = " + driver.findElement(By.xpath("//td[text()='ivan']/following-sibling::td[1]")).getText());
		System.out.println("actual = " + actual);
		Thread.sleep(8000);
		driver.quit();
	}

}
