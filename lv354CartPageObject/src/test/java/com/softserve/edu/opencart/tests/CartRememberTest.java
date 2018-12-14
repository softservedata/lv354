package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.data.product.Product;
import com.softserve.edu.opencart.data.product.ProductRepository;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.cart.PopupShoppingCartComponent;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import com.softserve.edu.opencart.tools.BrowserWrapper;
import com.softserve.edu.opencart.tools.BrowserWrapper.Browsers;
import com.softserve.edu.opencart.tools.RegexUtils;
import com.softserve.edu.opencart.tools.TestRunner;

public class CartRememberTest extends ApplicationTestRunner{
	
	@DataProvider
	public Object[][] productsToCart() {
		// Read from ...
		return new Object[][] {
				{ ProductRepository.iPhone(), ProductRepository.macBook(), Currencies.EURO, "http://google.com.ua" }, };
	}

	@Test(dataProvider = "productsToCart")
	public void сartRemember(Product product1, Product product2, Currencies currency, String url) {

		// Precondition
		HomeMessagePage homeMessagePage = homeP
				.emptyCart()

		// Steps
		// choose currency
				.chooseCurrency(currency)

		// add to Cart Iphone and Macbook
				.addToCartProductByPartialName(product1.getName())
				.addToCartProductByPartialName(product2.getName());

		//navigateTo(url);
		Application.get().getBrowser().openUrl(url);

		// navigate back
		Application.get().getBrowser().navigateBack();
		homeP =Application.get().loadApplication();
		
		// Check
		// validate items
		Assert.assertEquals(homeP.getCartButtonItemsCount(), RegexUtils.getProductsItems(product1, product2),PRECISION);
		// validate total
		Assert.assertEquals(homeP.extractTotalCart(), RegexUtils.getTotalProducts(currency, product1, product2),PRECISION);

		// Return to previous state
		delayExecution(3);
		homeP.emptyCart();
		System.out.println("Is cart empty = "+Application.get().getCartStatusIsEmpty());
	}

}
