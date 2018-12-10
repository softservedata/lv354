package com.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.data.user.IUser;
import com.opencart.tools.Application;

public class LoginPage extends AUnloggedRightMenuPage {

	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		emailField = driver.findElement(By.id("input-email"));
		passwordField = driver.findElement(By.id("input-password"));
		loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));

	}

	// PageObject Atomic Operation

	// emailField
	public WebElement getEmailField() {
		return emailField;
	}

	public String getEmailFieldText() {
		return getEmailField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setEmailField(String text) {
		getEmailField().sendKeys(text);
	}

	public void clearEmailField() {
		getEmailField().clear();
	}

	public void clickEmailField() {
		getEmailField().click();
	}

	// passwordField
	public WebElement getPasswordField() {
		return passwordField;
	}

	public String getPasswordFieldText() {
		return getPasswordField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setPasswordField(String text) {
		getPasswordField().sendKeys(text);
	}

	public void clearPasswordField() {
		getPasswordField().clear();
	}

	public void clickPasswordField() {
		getPasswordField().click();
	}

	// loginButton
	public WebElement getLoginButton() {
		return loginButton;
	}

	public String getLoginButtonText() {
		return getLoginButton().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void clickLoginButton() {
		getLoginButton().click();
	}

	// Functional Operations

	private void fillLoginForm(IUser user) {
	//private void fillLoginForm(String email, String password) {
        clickEmailField();
        clearEmailField();
        setEmailField(user.getEMail());
        //setEmailField(email);
        clickPasswordField();
        clearPasswordField();
        setPasswordField(user.getPassword());
        //setPasswordField(password);
        clickLoginButton();
    }
	
	// Business Logic

	public MyAccountPage successLogin(IUser user) {
	//public MyAccountPage successLogin(String email, String password) {
        fillLoginForm(user);
        Application.get().getBrowser().getTestStatus().setLogged(true);
		//fillLoginForm(email, password);
        return new MyAccountPage(driver);
    }

	public UnsuccessfulLoginPage unsuccessfullLogin(IUser invalidUser) {
	//public UnsuccessfulLoginPage unsuccessfullLogin(String email, String password) {
        fillLoginForm(invalidUser);
		//fillLoginForm(email, password);
        return new UnsuccessfulLoginPage(driver);
    }
	
}
