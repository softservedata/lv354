package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulRegistrationPage extends RegistrationPage {

    private WebElement alertMessage;
    private WebElement dangerMessage;

    public UnsuccessfulRegistrationPage(WebDriver driver){
        super(driver);
        initElements();
    }

    private void initElements() {
        alertMessage = driver.findElement(By.cssSelector(".alert"));
    }


    public WebElement getAlertMessage() {
        return alertMessage;
    }
    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

}
