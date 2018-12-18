package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.HomePage;

import io.qameta.allure.Step;

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

	@Step("get_Account_Logout_Label_Text STEP")
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

	@Step("choose_Continue_Home_Page STEP")
	public HomePage chooseContinue() {
		clickContinueButton();
        return new HomePage(driver); 
    }

}
