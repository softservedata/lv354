package com.opencart.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulSearchPage extends ASearchPage {

	public final String NO_PRODUCT_MESSAGE = "There is no product that matches the search criteria.";

	private WebElement noProductMessage;

	public UnsuccessfulSearchPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements(){
		noProductMessage = driver.findElement(By.xpath("//p[contains(text(), 'no product' )]"));
	}

	// PageObject Atomic Operation
	public WebElement getNoProductMessage() {
		return noProductMessage;
	}
	public String getNoProductMessageText(){
		return getNoProductMessage().getText();
	}
}
