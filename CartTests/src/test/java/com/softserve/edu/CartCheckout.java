package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class CartCheckout extends TestRunnerCart {
	String addToCartTemplate="//a[text()='%s']/../../following-sibling::div/button[contains(@onclick,'cart')]";
	String expectedText = "//div[@id=content and (text(),'Your order has been placed!')]";
	Pattern pSuccess =Pattern.compile("successfully|processed");
	String expectedTextMessage = "successfully processed";
	
	@BeforeMethod
	public void before () {
		
	driver.get("http://localhost:8888/opencart/upload/index.php?route=account/login");	
	WebElement email =driver.findElement(By.id("input-email"));
	email.clear();
	email.sendKeys("testTaras@gmail.com");
	WebElement passw =driver.findElement(By.id("input-password"));
	passw.clear();
	passw.sendKeys("testTaras",Keys.ENTER);
	driver.get("http://localhost:8888/opencart/upload/index.php?route=common/home");
	emptyCart();
	
	System.out.println("Before method");
	}
	
	@Test
	public void checkout () throws Exception{
		addToCard(Products.IPHONE);	
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-shopping-cart']/../../a[@href]")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']/../../a[@href]")).click();
		
		// handle coupon 
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.elementToBeClickable(By.xpath(("//a[contains(@href,'collapse-coupon')]"))));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement couponLink=driver.findElement(By.xpath(("//a[contains(@href,'collapse-coupon')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponLink);
		couponLink.click();
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.elementToBeClickable((By.id("input-coupon"))));
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement coupon =driver.findElement(By.id("input-coupon"));
		coupon.clear();
		coupon.sendKeys("test");
		driver.findElement(By.id("button-coupon")).click();
	
		// handle ship 
				
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(("//a[contains(@href,'shipping')]"))));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement shipLink=driver.findElement(By.xpath(("//a[contains(@href,'shipping')]")));
	
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		shipLink.click();
		
		Select country= new Select (driver.findElement(By.id("input-country")));
		country.selectByVisibleText("Andorra");
		Select region= new Select (driver.findElement(By.id("input-zone")));
		region.selectByVisibleText("Canillo");
		WebElement postCode=driver.findElement(By.id("input-postcode"));
		postCode.clear();
		postCode.sendKeys("test");
		driver.findElement(By.id("button-quote")).click();
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.visibilityOfElementLocated(By.id("modal-shipping")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement  radioBtn = driver.findElement (By.cssSelector("input[type='radio']"));
		if (radioBtn.isDisplayed()&&!radioBtn.isSelected()) {
			radioBtn.click();
			driver.findElement(By.id("button-shipping")).click();
		}
		
		//handle gift
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(("//a[@class='accordion-toggle'][contains(@href,'collapse-voucher')]"))));		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);	
		
		WebElement giftLink=driver.findElement(By.xpath(("//a[@class='accordion-toggle'][contains(@href,'collapse-voucher')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", giftLink);
		giftLink.click();
		WebElement gift = driver.findElement(By.id("input-voucher"));
		gift.clear();
		gift.sendKeys("test");
		driver.findElement(By.id("button-voucher")).click();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert.alert-danger")));
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pull-right a.btn")));	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement checkOut = driver.findElement(By.cssSelector("div.pull-right a.btn"));
		Actions actions = new Actions(driver);
		actions.moveToElement(checkOut).click().perform();
		
		//use a loop until checkout gets attached to DOM
		boolean breakIt = true;
        while (true) {
        breakIt = true;
        try {
        	checkOut.click();
        } catch (Exception e) {
            if (e.getMessage().contains("element is not attached")) {breakIt = false;}
        }
        if (breakIt) {break;}
        }
			
		//handle payment address
        Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 20)).until(
		ExpectedConditions.and((ExpectedConditions.elementToBeClickable(By.id("button-payment-address"))),
		ExpectedConditions.presenceOfElementLocated(By.id("button-payment-address"))));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		driver.findElement(By.id("button-payment-address")).click();
		
		//handle shipping
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.presenceOfElementLocated(By.id("button-shipping-address")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.id("button-shipping-address")).click();
		
		//handle delivery method
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.presenceOfElementLocated(By.id("button-shipping-method")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.id("button-shipping-method")).click();
		
		//handle payment method
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.presenceOfElementLocated(By.id("button-payment-method")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		driver.findElement(By.id("button-payment-method")).click();
		
		//handle confirm
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.presenceOfElementLocated(By.id("button-confirm")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.id("button-confirm")).click();
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(driver, 10)).until(
		ExpectedConditions.invisibilityOfElementLocated(By.id("button-confirm")));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		String actualResult=driver.findElement(By.id("content")).getText();
		System.out.println("Actual text = " +actualResult);
		String match="";                       //extract a word from message (wrong, invalid, error, mistake)
		Matcher m = pSuccess.matcher(actualResult);// and check if expected string contains such word
		int actuaIndex=-1;                     // then we expect number different from -1
		 if (m.find()) {
			 System.out.println("Match found : true");
			 match = actualResult.substring(m.start(), m.end());
			 actuaIndex = expectedTextMessage.indexOf(match);
		 }
		 Assert.assertFalse(actuaIndex!=-1);
	}
	
	
	
	void addToCard (Products product){
		driver.get("http://localhost:8888/opencart/upload/index.php?route=common/home");
		
		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(addToCartTemplate,product.getName() ))));
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		WebElement addToCart = driver.findElement(By.xpath(String.format(addToCartTemplate,product.getName() )));
		Actions action = new Actions(driver);	
		action.moveToElement(addToCart).perform();
		addToCart.click();
		System.out.println("Product added"+product.getName());
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);			
		(new WebDriverWait(driver, 20)).until(
				ExpectedConditions.and(
				ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-success"),
						"You have added")
				))
        		;
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);				
}
	
}
