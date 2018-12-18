package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class MyAccountPage extends ALoggedRightMenuPage {

	private WebElement editAccountInformation;
	private WebElement changeYourPassword;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		editAccountInformation = driver
				.findElement(By.xpath("//ul[@class='list-unstyled']//a[contains(@href,'route=account/edit')]"));
		changeYourPassword = driver
				.findElement(By.xpath("//ul[@class='list-unstyled']//a[contains(@href,'route=account/password')]"));
	}

	// PageObject Atomic Operation

	// editAccountInformation;
	public WebElement getEditAccountInformation() {
		return editAccountInformation;
	}

	public String getEditAccountInformationText() {
		return getEditAccountInformation().getText();
	}

	public void clickEditAccountInformation() {
		getEditAccountInformation().click();
	}

	// changeYourPassword;
	public WebElement getChangeYourPassword() {
		return changeYourPassword;
	}

	public String getChangeYourPasswordText() {
		return getChangeYourPassword().getText();
	}

	public void clickChangeYourPassword() {
		getChangeYourPassword().click();
	}

	// Functional Operations

	// Business Logic

	@Step("goto_Account_Information STEP")
	public AccountInformationPage gotoAccountInformation() {
		clickEditAccountInformation();
		return new AccountInformationPage(driver);
	}

}
