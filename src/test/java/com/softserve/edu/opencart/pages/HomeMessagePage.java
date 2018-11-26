package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeMessagePage extends HomePage {
	
	private WebElement alertMessage;
	
	public HomeMessagePage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		alertMessage = driver.findElement(By.cssSelector(".alert"));
	}
	
	// PageObject Atomic Operation

	// alertMessage
	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public String getAlertMessageText() {
		return getAlertMessage().getText();
	}

	// Business Logic

	// TODO
}
