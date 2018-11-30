package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.data.product.Product;
import com.softserve.edu.opencart.data.product.ProductRepository;
import com.softserve.edu.opencart.pages.HomeMessagePage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tools.TestRunner;

public class CurrencyTest extends TestRunner {

	@DataProvider // (parallel = true)
	public Object[][] currencyData() {
		// Read from ...
		return new Object[][] {
			{ Currencies.POUND_STERLING, "£" },
			{ Currencies.EURO, "€" },
			{ Currencies.US_DOLLAR, "$" },
		};
	}

	//@Test(dataProvider = "currencyData")
    public void checkCurrency(Currencies currency, String expectedCurrencyText) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1);
        //
        // Steps
        homePage = homePage.chooseCurrency(currency);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homePage.getCurrencyText(), expectedCurrencyText);
        delayExecution(2);
        //
        // Return to previous state
    }

	@DataProvider // (parallel = true)
	public Object[][] productCurrencyData() {
		// Read from ...
		return new Object[][] {
			{ Currencies.POUND_STERLING, ProductRepository.macBook() },
			{ Currencies.EURO, ProductRepository.iPhone() }
//            { Currencies.POUND_STERLING, "MacBook", 368.73 },
//            { Currencies.EURO, "MacBook", 472.33 },
//          { Currencies.US_DOLLAR, "MacBook", 602.00 },
            //{ Currencies.US_DOLLAR, "iPhone", 123.20 },
            //{ Currencies.US_DOLLAR, "Apple Cinema 30", 110.00 },
	  		//{ Currencies.US_DOLLAR, "Apple Cinema 30", 122.00 },
			};
	}

	@Test(dataProvider = "productCurrencyData")
	//public void checkProductCurrency(Currencies currency, String product, double price) {
	public void checkProductCurrency(Currencies currency, Product product) {
		//
		// Precondition
		HomePage homePage = loadApplication();
		delayExecution(1);
		//
		// Steps
		homePage = homePage.chooseCurrency(currency);
		delayExecution(1);
		//
		// Check
		Assert.assertEquals(homePage
				//.getProductComponentByPartialName(product).getPriceAmount(),
				.getProductComponentByPartialName(product.getName()).getPriceAmount(),
				//price, PRECISION);
				product.getPrice(currency), PRECISION);
		delayExecution(2);
		//
		// Return to previous state
	}

	@DataProvider//(parallel = true)
    public Object[][] productNames() {
        // Read from ...
        return new Object[][] { 
            { "MacBook" },
            { "iPhone" },
            };
    }

    //@Test(dataProvider = "productNames")
    public void checkProductToCart(String partialProductName) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.addToCartProductByPartialName(partialProductName);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_CART,
        				homePage.getProductComponentByPartialName(partialProductName).getNameText()));
        delayExecution(1);
        //
        System.out.println("homeMessagePage.getCartButtonItemsCount() = " + homeMessagePage.getCartButtonItemsCount());
        Assert.assertTrue(homeMessagePage.getCartButtonItemsCount() > 0);
        delayExecution(1);
        //
        // Return to previous state
        homePage = homeMessagePage.closeAlertMessage();
        delayExecution(2);
        //
        // TODO Clear Cart
    }

    //@Test(dataProvider = "productNames")
    public void checkProductToWishUnlogged(String partialProductName) {
        //
        // Precondition
        HomePage homePage = loadApplication();
        delayExecution(1);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.addToWishProductByPartialName(partialProductName);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_WISH_UNLOGGED,
        				homePage.getProductComponentByPartialName(partialProductName).getNameText()));
        delayExecution(1);
        //
        System.out.println("homeMessagePage.getWishListItemsCount() = " + homeMessagePage.getWishListItemsCount());
        Assert.assertTrue(homeMessagePage.getWishListItemsCount() > 0);
        delayExecution(1);
        //
        // Return to previous state
        homePage = homeMessagePage.closeAlertMessage();
        delayExecution(2);
        //
        // TODO Clear Wish List
    }
}
