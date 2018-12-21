package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeMessagePage extends HomePage {

	public final String EXPECTED_MESSAGE_CART = "Success: You have added %s to your shopping cart!";
	public final String EXPECTED_MESSAGE_WISH_UNLOGGED = "You must login or create an account to save %s to your wish list!";
	public final String EXPECTED_MESSAGE_WISH = "Success: You have added %s to your wish list!";

	private WebElement alertMessage;
	private WebElement alertMessageCloseButton;
	
	public HomeMessagePage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		alertMessage = driver.findElement(By.cssSelector(".alert"));
		alertMessageCloseButton = driver.findElement(By.cssSelector(".close"));
	}
	
	// PageObject Atomic Operation

	// alertMessage
	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public String getAlertMessageText() {
		//return getAlertMessage().getText();
		//
		String textMessage = getAlertMessage().getText();
    	// Remove x Symbol from Message
        return textMessage.substring(0, textMessage.length() - 2);
	}

	// alertMessageCloseButton
	public WebElement getAlertMessageCloseButton() {
		return alertMessageCloseButton;
	}

	public void clickAlertMessageCloseButton() {
		getAlertMessageCloseButton().click();
	}

	// Business Logic

	public HomePage closeAlertMessage() {
		clickAlertMessageCloseButton();
    	return new HomePage(driver);
    }
	
	// TODO
}
