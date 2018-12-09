package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnsuccessfulRegistrationPage extends RegistrationPage {

    public final String EXPECTED_WARNING_LOGIN = "Warning: No match for E-Mail Address and/or Password.";

    private WebElement alertMessage;
    private WebElement dangerMessage;

    public UnsuccessfulRegistrationPage(WebDriver driver){
        super(driver);
        initElements();
    }

    private void initElements() {
        alertMessage = driver.findElement(By.cssSelector(".alert"));
//        dangerMessage = driver.findElement(By.cssSelector("div.text-danger"));
    }


    public WebElement getAlertMessage() {
        return alertMessage;
    }
    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

//    public WebElement getDangerMessage(){
//        return dangerMessage;
//    }
//    public String getDangerMessageText(){
//        return getDangerMessage().getText();
//    }
}
