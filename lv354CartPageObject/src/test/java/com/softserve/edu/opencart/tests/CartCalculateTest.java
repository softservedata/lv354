package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.data.product.Product;
import com.softserve.edu.opencart.data.product.ProductRepository;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.cart.PopupShoppingCartComponent;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import com.softserve.edu.opencart.tools.RegexUtils;

//public class CartCalculateTest extends TestRunner {
public class CartCalculateTest extends ApplicationTestRunner {
	
	@DataProvider 
	public Object[][] productsToCart() {
		// Read from ...
		return new Object[][] {
			{  ProductRepository.iPhone(),ProductRepository.macBook(),Currencies.US_DOLLAR},};
	}
	
	@Test(dataProvider = "productsToCart")
		public void checkProductsCalculate(Product product1,Product product2, Currencies currency) {
		
		// Precondition
		PopupShoppingCartComponent cart =homeP
				.emptyCart()
			
		// Steps
		
		//chooseCurrency
				.chooseCurrency(currency)
		
		//add to Cart Iphone and Macbook
				.addToCartProductByPartialName(product1.getName())
				.addToCartProductByPartialName(product2.getName())
				.createDropDownCart();
			
	    // Check
		//validate subtotal
		Assert.assertEquals(cart.extractSubTotal(), RegexUtils.getSubTotalProducts(currency, product1,product2),PRECISION);
		//validate total
		Assert.assertEquals(cart.extractTotal(), RegexUtils.getTotalProducts(currency,product1,product2),PRECISION);
		//validate EcoTax
		Assert.assertEquals(cart.extractEcoTax(), RegexUtils.getEcoTaxProducts(currency, product1,product2),PRECISION);
		//validate VAT
		Assert.assertEquals(cart.extractVat(), RegexUtils.getVatProducts(currency, product1,product2),PRECISION);		
		
	    // Return to previous state
		HomePage hom=cart.closePopUpShoppingCartComponent();
		delayExecution(3);
		hom.emptyCart();
		System.out.println("Is cart empty = "+Application.get().getCartStatusIsEmpty());
	
	}
			
	
}
