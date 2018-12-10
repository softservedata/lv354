package com.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.pages.HomePage;

public class AccountLogoutPage extends AUnloggedRightMenuPage {

	public final String EXPECTED_TEXT_LOGOUT = "Account Logout";
	
	private WebElement accountLogoutLabel;
	private WebElement continueButton;

	public AccountLogoutPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		accountLogoutLabel = driver.findElement(By.cssSelector("#content h1"));
		continueButton = driver.findElement(By.cssSelector(".btn-primary"));
	}

	// PageObject Atomic Operation
	
	// accountLogoutLabel
	public WebElement getAccountLogoutLabel() {
        return accountLogoutLabel;
    }

	public String getAccountLogoutLabelText() {
        return getAccountLogoutLabel().getText();
    }
	
	//continueButton
	public WebElement getContinueButton() {
        return continueButton;
    }

	public String getContinueButtonText() {
        return getContinueButton().getText();
    }

	public void clickContinueButton() {
        getContinueButton().click();
    }

	// Functional Operations
	
	// Business Logic

	public HomePage chooseContinue() {
		clickContinueButton();
        return new HomePage(driver); 
    }

}
