package com.softserve.cartTests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//we add to Cart Iphone and MacBook, close the page, go to google, then return to Opencart and check if all items added

public class CartAddRemember extends TestRunnerCart {
	String addToCartTemplate="//a[text()='%s']/../../following-sibling::div/button[contains(@onclick,'cart')]";
	String oldTextinCart="";
	String newTextinCart="";
	List <String> actualStringList = new ArrayList <String> ();
	List <String> expectedStringList = new ArrayList <String> ();
		
	
	@BeforeMethod
	public void go () {
	driver.get("http://localhost:8888/opencart/upload/");	
	addToCard(Products.MACBOOK);// 
	System.out.println("Before method");
	}
		
	@Test
	public void addToCardAgain () throws Exception{
			
		addToCard(Products.IPHONE) ;
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("http://www.google.com.ua");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/opencart/upload/");
		compare();
		
		
				
	}
	void compare() {

//		 get a list of all products
//		 convert to string list
//		Assertion: compare to string list of products that we added.
		 		
		WebElement cart=driver.findElement(By.id("cart-total"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);
		 cart.click();
		 List <WebElement> actualWebList=driver.findElements(By.cssSelector((".text-left")));
		 actualStringList=createStringListfromWebElementList(actualWebList);	
		 Assert.assertTrue(actualStringList.containsAll(expectedStringList) && expectedStringList.containsAll(actualStringList));
	}
	
	
	void addToCard (Products product){
		
		expectedStringList.add(product.getName());	
		oldTextinCart=driver.findElement(By.id("cart-total")).getText();
		System.out.println("Old text = "+oldTextinCart);
		WebElement addToCart = driver.findElement(By.xpath(String.format(addToCartTemplate,product.getName() )));
		Actions action = new Actions(driver);	
		action.moveToElement(addToCart).perform();
		addToCart.click();
		System.out.println("Product added"+product.getName());
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				
		(new WebDriverWait(driver, 20)).until(
				ExpectedConditions.and(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-success"),
						"You have added")
				))
        		;
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);				
}
	
	List<String> createStringListfromWebElementList(List <WebElement> actualWebList) {
	 
		 for (WebElement x:actualWebList) {
			 String webElementString=x.getText();
			 actualStringList.add (webElementString);
		 }
		return actualStringList;
}
}
