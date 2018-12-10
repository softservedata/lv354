package com.opencart.tests;

import com.opencart.tools.Application;
import com.opencart.tools.ApplicationTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.data.product.Currencies;
import com.opencart.data.product.Product;
import com.opencart.data.product.ProductRepository;
import com.opencart.pages.HomeMessagePage;
import com.opencart.pages.HomePage;

public class CurrencyTest extends ApplicationTestRunner {

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
        HomePage homePage = Application.get().loadApplication();
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
		HomePage homePage = Application.get().loadApplication();
		delayExecution(1);
		//
		// Steps
		homePage = homePage.chooseCurrency(currency);
		delayExecution(1);

		//
		// Check
		Assert.assertEquals(homePage.getProductsListComponent()
                        .getProductComponentByPartialName(product.getName()).getPriceAmount(),
				product.getPrice(currency));
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
        HomePage homePage = Application.get().loadApplication();
        delayExecution(1);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.getProductsListComponent()
                .addToCartProductByPartialName(partialProductName);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_CART,
        				homePage.getProductsListComponent()
                                .getProductComponentByPartialName(partialProductName).getNameText()));
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
        HomePage homePage = Application.get().loadApplication();
        delayExecution(1);
        //
        // Steps
        HomeMessagePage homeMessagePage = homePage.getProductsListComponent()
                .addToWishProductByPartialName(partialProductName);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homeMessagePage.getAlertMessageText(),
        		String.format(homeMessagePage.EXPECTED_MESSAGE_WISH_UNLOGGED,
        				homePage.getProductsListComponent()
                                .getProductComponentByPartialName(partialProductName).getNameText()));
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
