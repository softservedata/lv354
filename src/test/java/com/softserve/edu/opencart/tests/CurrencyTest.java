package com.softserve.edu.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.product.Currencies;
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

	@Test(dataProvider = "currencyData")
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
//            { Currencies.POUND_STERLING, "MacBook", 487.62 },
//            { Currencies.EURO, "MacBook", 560.94 },
//            { Currencies.US_DOLLAR, "MacBook", 602.00 },
				{ Currencies.US_DOLLAR, "iPhone", 123.20 },
				{ Currencies.US_DOLLAR, "Apple Cinema 30", 122.00 },
			};
	}

}
