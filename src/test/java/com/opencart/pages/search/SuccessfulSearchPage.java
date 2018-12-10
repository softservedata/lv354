package com.opencart.pages.search;

import com.opencart.components.ProductsBlockComponent;
import com.opencart.pages.ProductPage;
import org.openqa.selenium.WebDriver;

public class SuccessfulSearchPage extends ASearchPage {

	private ProductsBlockComponent productsBlockComponent;

	public SuccessfulSearchPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements(){
		productsBlockComponent = new ProductsBlockComponent(driver);
	}

	public ProductsBlockComponent getProductsBlockComponent() {
		if (productsBlockComponent == null) {
			throw new RuntimeException(OPTION_NULL_MESSAGE);
		}
		return productsBlockComponent;
	}

	// Business Logic
	public ProductPage openProductPageByName(String productName){
		getProductsBlockComponent().getProductsListComponent().getProductItemByName(productName).clickName();
		return new ProductPage(driver);
	}
}
