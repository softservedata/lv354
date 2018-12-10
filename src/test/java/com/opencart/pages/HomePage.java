package com.opencart.pages;

import com.opencart.components.ProductsListComponent;
import org.openqa.selenium.WebDriver;
import com.opencart.data.product.Currencies;

public class HomePage extends AHeadPage {

	private ProductsListComponent productsListComponent;

	public HomePage(WebDriver driver){
		super(driver);
		initElements();
	}

	private void initElements(){
		productsListComponent = new ProductsListComponent(driver);
	}

	public ProductsListComponent getProductsListComponent(){
		return productsListComponent;
	}

	public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new HomePage(driver); 
    }

}
