package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AHeadPage {

    private List<WebElement> productsList;
    private List<ProductComponent> productComponents;

    public HomePage(WebDriver driver) {
        super(driver);
        initProductsComponents();
    }

    private void initProductsComponents() {
        productComponents = new ArrayList<>();
        List<WebElement> productsList = driver.findElements(By.cssSelector(".product-layout"));
        for (WebElement current : productsList) {
            productComponents.add(new ProductComponent(current));
        }
    }

    // PageObject Atomic Operation

    // productsList
    public List<WebElement> getProductsList() {
        return productsList;
    }

    // productComponents
    public List<ProductComponent> getProductComponents() {
        return productComponents;
    }

    public List<String> getProductsNameList() {
        List<String> result = new ArrayList<>();
        for (ProductComponent current : getProductComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }

    // Business Logic

    public ProductComponent getProductComponentByPartialName(String partialProductName) {
        ProductComponent result = null;
        for (ProductComponent current : getProductComponents()) {
            if (current.getNameText().toLowerCase()
                    .contains(partialProductName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }


}
