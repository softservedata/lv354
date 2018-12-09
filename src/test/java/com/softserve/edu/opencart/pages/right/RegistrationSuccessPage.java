package com.softserve.edu.opencart.pages.right;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationSuccessPage extends ALoggedRightMenuPage {

    private WebElement continueButton;

    public RegistrationSuccessPage(WebDriver driver){
        super(driver);
        initElements();
    }

    private void initElements(){
        continueButton = driver.findElement(By.cssSelector(".btn-primary"));
    }


    // PageObject Atomic Operation
    //Success menu button
    public WebElement getContinueButton() {
        return continueButton;
    }
    public String getContinueButtonText() {
        return getContinueButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void clickContinueButton() {
        getContinueButton().click();
    }


    // Business Logic

    public MyAccountPage gotoMyAccount(){
        clickContinueButton();
        return new MyAccountPage(driver);
    }
}
