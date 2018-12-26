package com.softserve.edu.opencart.pages.admin;

import com.softserve.edu.opencart.data.user.IUser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomersPage {

    private WebElement selectAll;
    private WebElement deleteButton;

    protected final String TAG_ATTRIBUTE_VALUE = "value";

    protected WebDriver driver;

    public CustomersPage (WebDriver driver){
        this.driver=driver;
        initElemets();
    }

    private void initElemets(){
        selectAll = driver.findElement(By.xpath("//div[@class='table-responsive']//td[@style='width: 1px;']"));
        deleteButton = driver.findElement(By.xpath("//div[@class='pull-right']//button[@class='btn btn-danger']"));
    }

    public WebElement getSelectAll(){
        return selectAll;
    }
    public void clickSelectAll(){
        getSelectAll().click();
    }

    public WebElement getDeleteButton(){
        return deleteButton;
    }
    public String getDeleteButtonText(){
        return getDeleteButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void clickDeleteButton(){
        getDeleteButton().click();
    }


    public CustomersPage deleteCustomers() {

        clickSelectAll();
        clickDeleteButton();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        return new CustomersPage(driver);
    }
}
