package com.softserve.edu.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.tools.RegexUtils;

public class HomeFactoryPage extends AHeadFactoryPage {

	@FindBy(css = ".product-layout")
	private List<WebElement> productsList;

	public HomeFactoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); // 1st item
		// initProductsComponents(); // Not Use
	}

	// PageObject Atomic Operation

	// productsList
	public List<WebElement> getProductsList() {
		return productsList;
	}

	// Business Logic

	public double getProductPriceByPartialName(String partialProductName) {
        double result = -1;
        for (WebElement current : getProductsList()) {
            if (current.getText().toLowerCase()
            		.contains(partialProductName.toLowerCase())) {
            	result = RegexUtils.extractFirstDouble(current.getText());
                break;
            }
        }
        return result;
    }

	public HomeFactoryPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new HomeFactoryPage(driver); 
    }

}
