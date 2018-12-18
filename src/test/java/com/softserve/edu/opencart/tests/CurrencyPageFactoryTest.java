package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.data.product.Product;
import com.softserve.edu.opencart.data.product.ProductRepository;
import com.softserve.edu.opencart.pages.HomeFactoryPage;
import com.softserve.edu.opencart.tools.TestFactoryRunner;

public class CurrencyPageFactoryTest extends TestFactoryRunner {

	@DataProvider // (parallel = true)
	public Object[][] currencyData() {
		// Read from ...
		return new Object[][] {
			{ Currencies.POUND_STERLING, "£" },
			//{ Currencies.EURO, "€" },
			//{ Currencies.US_DOLLAR, "$" },
		};
	}

	@Test(dataProvider = "currencyData")
    public void checkCurrency(Currencies currency, String expectedCurrencyText) {
        //
        // Precondition
    	HomeFactoryPage homeFactoryPage = loadApplication();
        delayExecution(1);
        //
        // Steps
        homeFactoryPage = homeFactoryPage.chooseCurrency(currency);
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(homeFactoryPage.getCurrencyText(), expectedCurrencyText);
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

	//@Test(dataProvider = "productCurrencyData")
	public void checkProductCurrency(Currencies currency, Product product) {
		//
		// Precondition
		HomeFactoryPage homeFactoryPage = loadApplication();
		delayExecution(1);
		//
		// Steps
		homeFactoryPage = homeFactoryPage.chooseCurrency(currency);
		delayExecution(1);
		//
		// Check
		Assert.assertEquals(homeFactoryPage
				.getProductPriceByPartialName(product.getName()),
				product.getPrice(currency), PRECISION);
		delayExecution(2);
		//
		// Return to previous state
	}

}
