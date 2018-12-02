package com.softserve.edu;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartCalculateTest extends TestRunnerCart  {
	
	private String productButtonAddToCartTemplate = "//a[text()='%s']/../../following-sibling::div/button[contains(@onclick,'cart')]";
	private String subTotalString ="//table[@class='table table-bordered']/tbody/child::tr[1]/td[2]";
	private String ecoTaxString ="//table[@class='table table-bordered']/tbody/child::tr[2]/td[2]";
	private String vatString ="//table[@class='table table-bordered']/tbody/child::tr[3]/td[2]";
	private String totalString ="//table[@class='table table-bordered']/tbody/child::tr[4]/td[2]";

	Products product=Products.MACBOOK;
	double expectedSubTotal =500.00;
	double expectedEcoTax=2.00;
	double expectedVAT=100.00;
	double expectedTotal=602.00;
		
	@BeforeMethod
	public void before () {emptyCart();}
	public void calculateAddToCard () {
	
	driver.findElement(By.cssSelector(".form-control.input-lg")).click();	
	
	WebElement addToCart = driver.findElement(By.xpath(String.format(productButtonAddToCartTemplate, product.getName())));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
	 addToCart.click();
	
	 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);	 
	 (new WebDriverWait(driver, 20)).until(
     		ExpectedConditions.presenceOfElementLocated(
     				By.cssSelector(".fa.fa-times"))); 
	 driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS); 
	
	WebElement cart=driver.findElement(By.id("cart"));
	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);
	 cart.click();
	 
	 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);	 
	 (new WebDriverWait(driver, 20)).until(
     		ExpectedConditions.textToBePresentInElementLocated(By.xpath(subTotalString),"$"
     				)); 
	 driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	 
	WebElement actualSubTotal = driver.findElement(By.xpath(subTotalString));
	System.out.println("actualSubTotal ="+actualSubTotal.getText());
	WebElement actualEcoTax = driver.findElement(By.xpath(ecoTaxString));
	WebElement actualVat = driver.findElement(By.xpath(vatString));
	WebElement actualTotal = driver.findElement(By.xpath(totalString));
	System.out.println("actualTotal = "+actualTotal.getText());
	double dActualSubTotal = ParseStringNumberToDouble (actualSubTotal);
	double dactualEcoTax = ParseStringNumberToDouble (actualEcoTax);
	double dactualVat = ParseStringNumberToDouble (actualVat);
	double dactualTotal = ParseStringNumberToDouble (actualTotal);
	
	 Assert.assertEquals(dActualSubTotal,expectedSubTotal, PRECISION);
	 Assert.assertEquals(dactualEcoTax, expectedEcoTax, PRECISION);
	 Assert.assertEquals(dactualVat,expectedVAT,PRECISION);
	 Assert.assertEquals(dactualTotal,expectedTotal,PRECISION);
	}
	
	protected double ParseStringNumberToDouble (WebElement webelement)
	{
		String textWebelement = webelement.getText();
		Matcher matcher = Pattern.compile("\\d+(,\\d+)*\\.\\d{2}").matcher(textWebelement);
		String numberString=null;
		double number=0;

		if (matcher.find()) {
				numberString = textWebelement.substring(matcher.start(), matcher.end());	
				numberString = numberString.replaceAll(",", "");
				number = Double.parseDouble(numberString);
		   }
		return number;
		
	}
}
