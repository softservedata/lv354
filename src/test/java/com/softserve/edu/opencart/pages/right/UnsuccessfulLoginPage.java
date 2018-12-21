package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulLoginPage extends LoginPage {
	
	public final String EXPECTED_WARNING_LOGIN = "Warning: No match for E-Mail Address and/or Password.";

	private WebElement alertMessage;

	public UnsuccessfulLoginPage(WebDriver driver) {
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

	// PageObject Atomic Operation
    
	// Business Logic

}
