package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends AHeadPage{

    private WebElement productDescription;

    public ProductPage(WebDriver driver){
        super(driver);
        initElements();
    }
    private void initElements(){
        productDescription = driver.findElement(By.id("tab-description"));
    }

    public String getDescriptionText(){
        return productDescription.getText();
    }
}
