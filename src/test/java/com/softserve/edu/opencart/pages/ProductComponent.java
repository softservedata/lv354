package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductComponent {

    private WebElement productLayout;
    //
    private WebElement name;
    private WebElement partialDescription;
    private WebElement price;
    private WebElement addToCartButton;
    private WebElement addToWishButton;
    private WebElement addToCompareButton;

    public ProductComponent(WebElement productLayout) {
        this.productLayout = productLayout;
        initProductComponents();
    }

    private void initProductComponents() {
        name = productLayout.findElement(By.cssSelector("h4 a"));
        price = productLayout.findElement(By.cssSelector(".price"));
        partialDescription = productLayout.findElement(By.cssSelector("h4 + p"));
        addToCartButton = productLayout.findElement(By.cssSelector(".fa.fa-shopping-cart"));
        addToWishButton = productLayout.findElement(By.cssSelector(".fa.fa-heart"));
        addToCompareButton = productLayout.findElement(By.cssSelector(".fa.fa-exchange"));
    }

    // PageObject Atomic Operation

    // productLayout
    public WebElement getProductLayout() {
        return productLayout;
    }

    // name;
    public WebElement getName() {
        return name;
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    // partialDescription
    public WebElement getPartialDescription() {
        return partialDescription;
    }

    public String getPartialDescriptionText() {
        return getPartialDescription().getText();
    }

    // price;
    public WebElement getPrice() {
        return price;
    }

    public String getPriceText() {
        return getPrice().getText();
    }

    // addToCartButton;
    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }

    // addToWishButton;
    public WebElement getAddToWishButton() {
        return addToWishButton;
    }

    public void clickAddToWishButton() {
        getAddToWishButton().click();
    }

    // addToCompareButton;
    public WebElement getAddToCompareButton() {
        return addToCompareButton;
    }

    public void clickAddToCompareButton() {
        getAddToCompareButton().click();
    }

}
