package com.softserve.edu.opencart.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedAdminPage extends LoginAdminPage {

    private WebElement totalCustomers;

    public LoggedAdminPage (WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        totalCustomers = driver.findElement(By.xpath("//div[text()='Total Customers ']/../div[@class='tile-footer']/a"));
   }


   //Atomic

    public WebElement getTotalCustomers(){
        return totalCustomers;
    }
    public String getTotalCustomersText(){
        return getTotalCustomers().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void clickTotalCustomers() {
        getTotalCustomers().click();
    }

    public CustomersPage gotoCustomerPage(){
        clickTotalCustomers();
        return new CustomersPage(driver);
    }
}
