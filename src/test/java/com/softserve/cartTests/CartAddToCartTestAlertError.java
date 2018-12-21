package com.softserve.cartTests;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartAddToCartTestAlertError extends TestRunnerCart {
	
	String productTemplate="//a//img[@alt='%s']";
	String alertTextTemplate= "//div[@class='alert alert-success']//a[text()='%s']";
	String actualTextTemplate = "//div[@class='alert alert-success'and contains(text(),'have')]";
	String expectedTextTemplate ="Success: You have added %s to your shopping cart!";
	Pattern pError =Pattern.compile("error|mistake|invalid|wrong");
	String expectedTextMessage = "error mistake invalid wrong";

	@BeforeMethod
	public void before () {emptyCart();}
	
	
	@DataProvider//(parallel = true)
    public Object[][] productData3() {
      
        return new Object[][] { 
            {Products.IPHONE}
            };
    }
	
	@Test(dataProvider="productData3")
	public void addToCard2 (Products product) {
			
	WebElement addToCart2 = driver.findElement(By.xpath(String.format(productTemplate,product.getName() )));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart2);
	addToCart2.click();
	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	
	(new WebDriverWait(driver, 20)).until(
	     		ExpectedConditions.elementToBeClickable(
	     				By.id("input-quantity"))); 
	
	driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	driver.findElement(By.id("input-quantity")).clear();
	driver.findElement(By.id("input-quantity")).sendKeys("test");
	driver.findElement(By.id("button-cart")).click();
	
	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	
	(new WebDriverWait(driver, 30)).until(
			 ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(alertTextTemplate,product.getName())))); 
	
	driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	
	String actualText =driver.findElement(By.xpath(actualTextTemplate)).getText();
	actualText =actualText.substring(0, actualText.length()-2);//remove 2 last symbols for correct message
	
	String match="";                       //extract a word from message (wrong, invalid, error, mistake)
	Matcher m = pError.matcher(actualText);// and check if expected string contains such word
	int actuaIndex=-1;                     // then we expect number different from -1
	 if (m.find()) {
		 System.out.println("Match found : true");
		 match = actualText.substring(m.start(), m.end());
		 actuaIndex = expectedTextMessage.indexOf(match);
	 }	 
	 Assert.assertTrue(actuaIndex!=-1);
	}

}
