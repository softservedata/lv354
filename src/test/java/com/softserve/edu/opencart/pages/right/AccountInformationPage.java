package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class AccountInformationPage extends ALoggedRightMenuPage {

	private WebElement firstnameField;

	public AccountInformationPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		firstnameField = driver.findElement(By.id("input-firstname"));
	}

	// PageObject Atomic Operation

	// firstnameField
	public WebElement getFirstnameField() {
		return firstnameField;
	}

	@Step("get_Firstname_Field_Text STEP")
	public String getFirstnameFieldText() {
		return getFirstnameField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setFirstnameField(String text) {
		getFirstnameField().sendKeys(text);
	}

	public void clearFirstnameField() {
		getFirstnameField().clear();
	}

	public void clickFirstnameField() {
		getFirstnameField().click();
	}

	// Functional Operations

	// Business Logic

}
