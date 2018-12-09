package com.softserve.edu.opencart.pages.admin;

import com.softserve.edu.opencart.data.user.IUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginAdminPage {

    protected WebDriver driver;

    protected final String TAG_ATTRIBUTE_VALUE = "value";

    private WebElement login;
    private WebElement password;
    private WebElement button;

    public LoginAdminPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements(){
        login = driver.findElement(By.id("input-username"));
        password = driver.findElement(By.id("input-password"));
        button = driver.findElement(By.cssSelector(".btn-primary"));
    }

    // PageObject Atomic Operation
    public WebElement getLoginField(){
        return login;
    }
    public String getLoginFieldText(){
        return getLoginField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setLoginField(String text) {
        getLoginField().sendKeys(text);
    }
    public void clearLoginField() {
        getLoginField().clear();
    }
    public void clickLoginField() {
        getLoginField().click();
    }

    public WebElement getPasswordField(){
        return password;
    }
    public String getPasswordFieldText(){
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

    public WebElement getButton(){
        return button;
    }
    public String getButtonText(){
        return getButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void clickButton(){
        getButton().click();
    }

    public void fillLoginForm(IUser admin) {
        //Type Login
        clickLoginField();
        clearLoginField();
        setLoginField(admin.getFirstname());
        //Type Password
        clickPasswordField();
        clearPasswordField();
        setPasswordField(admin.getPassword());
    }
    public void gotoLoggedAdminPage(IUser admin) {
        fillLoginForm(admin);
        clickButton();
    }
}
