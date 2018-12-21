package com.softserve.edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StartTest extends TestRunner {
	private String currencyTemplate = "//button[contains(text(), '%s')]";
	private String productPrice = "//div[contains(@class, 'product-layout')]//a[contains(text(),'%s')]/../../p[@class='price']";

	@DataProvider//(parallel = true)
    public Object[][] currencyData() {
        // Read from ...
        return new Object[][] { 
            { Currencies.POUND_STERLING, "£" },
            //{ Currencies.EURO, "€" }, // Fail new RuntimeException
            //{ Currencies.US_DOLLAR, "$" },
            { Currencies.US_DOLLAR, "€" }, // Fail Assert
            };
    }

    //@Test(dataProvider = "currencyData")
    public void checkCurrency(Currencies currency, String expectedCurrencyText) {
        //
        // Precondition
    	driver.findElement(By.cssSelector(".form-control.input-lg")).click();
        //
        // Steps
    	driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle")).click();
    	delayExecution(2);
    	driver.findElement(By.xpath(String.format(currencyTemplate, currency.toString()))).click(); 
        delayExecution(2);
        //
        if (currency.toString().toLowerCase().contains("euro")) {
        	throw new RuntimeException("Ha-Ha-HA");
        }
        // Check
        Assert.assertEquals(driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle strong")).getText(), expectedCurrencyText);
        delayExecution(2);
        //
        // Return to previous state
        //isTestSuccess = true;
    }

	@DataProvider//(parallel = true)
    public Object[][] productCurrencyData() {
        // Read from ...
        return new Object[][] { 
//            { Currencies.POUND_STERLING, 487.62 },
//            { Currencies.EURO, 560.94 },
//            { Currencies.US_DOLLAR,602.00 },
            { Currencies.US_DOLLAR, 123.20 },
            //{ Currencies.US_DOLLAR, 110.00 },
            };
    }

    @Test(dataProvider = "productCurrencyData")
    public void checkProductCurrency(Currencies currency, String product, double expectedPrice) {
        //
        // Precondition
    	driver.findElement(By.cssSelector(".form-control.input-lg")).click();
        delayExecution(1);
        //
        // Steps
    	driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle")).click();
    	delayExecution(1);
    	driver.findElement(By.xpath(String.format(currencyTemplate, currency.toString()))).click(); 
        delayExecution(1);
        //
        // Functional
        WebElement productPriceElement = driver.findElement(By.xpath(String.format(productPrice, product)));
        // Move To Element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productPriceElement);
        delayExecution(1);
        //
        //String textPrices = driver.findElement(By.xpath(String.format(productPrice, product))).getText();
        String textPrices = productPriceElement.getText();
        Matcher matcher = Pattern.compile("\\d+(,\\d+)*\\.\\d{2}").matcher(textPrices);
        String firstPrice = "";
        if (matcher.find()) {
        	firstPrice = textPrices.substring(matcher.start(), matcher.end());
        }
        firstPrice = firstPrice.replaceAll(",", "");
        double actualPrice = Double.parseDouble(firstPrice);
        //
        // Check
        Assert.assertEquals(expectedPrice, actualPrice, PRECISION);
        delayExecution(2);
        //
        // Return to previous state
    }

}