package com.opencart.components;

import com.opencart.pages.HomeMessagePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ProductsListComponent {

    private WebDriver driver;

    private List<ProductItemComponent> productItemComponents;

    public ProductsListComponent(WebDriver driver) {
        this.driver = driver;
        initProductsComponents();
    }

    private void initProductsComponents() {
        productItemComponents = new ArrayList<>();
        List<WebElement> productsList = driver.findElements(By.cssSelector(".product-layout"));
        for (WebElement current : productsList) {
            productItemComponents.add(new ProductItemComponent(current));
        }
    }

    // PageObject Atomic Operation

    // productItemComponents
    public List<ProductItemComponent> getProductItemComponents() {
        return productItemComponents;
    }

    public List<String> getProductsNameList() {
        List<String> result = new ArrayList<>();
        for (ProductItemComponent current : getProductItemComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }

    // Business Logic
    public ProductItemComponent getProductComponentByPartialName(String partialProductName) {
        ProductItemComponent result = null;
        for (ProductItemComponent current : getProductItemComponents()) {
            if (current.getNameText().toLowerCase()
                    .contains(partialProductName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    public ProductItemComponent getProductItemByName(String productName) {
        ProductItemComponent result = null;
        for (ProductItemComponent current : getProductItemComponents()) {
            if (current.getNameText().equalsIgnoreCase(productName)) {
                result = current;
                break;
            }
        }
        return result;
    }

    public HomeMessagePage addToCartProductByPartialName(String partialProductName) {
        getProductComponentByPartialName(partialProductName)
                .clickAddToCartButton();
        return new HomeMessagePage(driver);
    }

    public HomeMessagePage addToWishProductByPartialName(String partialProductName) {
        getProductComponentByPartialName(partialProductName)
                .clickAddToWishButton();
        return new HomeMessagePage(driver);
    }

    public HomeMessagePage addToCompareProductByPartialName(String partialProductName) {
        getProductComponentByPartialName(partialProductName)
                .clickAddToCompareButton();
        return new HomeMessagePage(driver);
    }

}
