package com.softserve.cartTests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//add the product to Cart and check the amount and price of item added
public class CartAddToCartTest extends TestRunnerCart {
	int olditemExpected=0;
	double oldPriceExpected=0;
	int itemExpected=0;
	double PriceExpected=0;
	
	public String addToCartTemplate="//a[text()='%s']/../../following-sibling::div/button[contains(@onclick,'cart')]";
	
	
	@DataProvider//(parallel = true)
    public Object[][] productData2() {
     
        return new Object[][] { 
            {Products.MACBOOK,1,Products.MACBOOK.getPrice()},
            {Products.IPHONE,1,Products.IPHONE.getPrice()} 
            };
    }
	
	@Test(dataProvider="productData2")
	public void addToCard (Products product, int newitemExpected, double newPriceExpected){
		itemExpected = olditemExpected+newitemExpected;
		olditemExpected = itemExpected;
		PriceExpected=oldPriceExpected+newPriceExpected;
		oldPriceExpected=PriceExpected;	

	WebElement addToCart = driver.findElement(By.xpath(String.format(addToCartTemplate,product.getName() )));
	Actions action = new Actions(driver);	
	action.moveToElement(addToCart).perform();
	
	addToCart.click();
	 (new WebDriverWait(driver, 20)).until(
	     		ExpectedConditions.elementToBeClickable(
	     				By.id("cart-total"))); 
	 
	WebElement newCart =driver.findElement(By.id("cart-total"));
	
	int itemActual=0;
	double priceActual=0;
	
	String newCartString=newCart.getText();
	Pattern pItem = Pattern.compile("^ *\\d");
	Matcher mItem = pItem.matcher(newCartString);
		
	while (mItem.find()) {
		itemActual=Integer.parseInt(mItem.group());
	 System.out.println("Item actual is"+itemActual);
	}
	
	CartCalculateTest prctual=new CartCalculateTest();
	priceActual = prctual.ParseStringNumberToDouble(newCart);
	
	Assert.assertEquals(itemActual,itemExpected, PRECISION);
	Assert.assertEquals(priceActual, PriceExpected, PRECISION);
	
	}

}
