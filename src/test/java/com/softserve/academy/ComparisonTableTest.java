package com.softserve.academy;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComparisonTableTest extends TestRunner {

    @Test
    public void testAddingToComparison() {
        driver.findElement(By.xpath("//a[text()='MacBook']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'product/compare')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//a/strong[text()='MacBook']")).getText().contains("MacBook"));
    }

    @Test(groups = "Laptops")
    public void testAddingTwoElements() {
        driver.findElement(By.xpath("//a[text()='HP LP3065']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.id("compare-total")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='HP LP3065']")).getText(), "HP LP3065");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='MacBook Air']")).getText(), "MacBook Air");
    }

    @Test(groups = "Laptops")
    public void testAddingThreeElements() {
        driver.findElement(By.xpath("//a[text()='HP LP3065']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='Sony VAIO']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.id("compare-total")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='HP LP3065']")).getText(), "HP LP3065");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='MacBook Air']")).getText(), "MacBook Air");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='Sony VAIO']")).getText(), "Sony VAIO");
    }

    @Test(groups = "Laptops")
    public void testAddingFourElements() {
        driver.findElement(By.xpath("//a[text()='HP LP3065']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='Xiaomi Mi Notebook Pro']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='Sony VAIO']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.id("compare-total")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='HP LP3065']")).getText(), "HP LP3065");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='MacBook Air']")).getText(), "MacBook Air");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='Xiaomi Mi Notebook Pro']")).getText(), "Xiaomi Mi Notebook Pro");
        Assert.assertEquals(driver.findElement(By.xpath("//a/strong[text()='Sony VAIO']")).getText(), "Sony VAIO");
    }

    @Test(groups = "Laptops")
    public void testAddingToCart() {
        driver.findElement(By.xpath("//a[text()='HP LP3065']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.id("compare-total")).click();
        driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.id("cart-total")).click();
        driver.findElement(By.linkText("View Cart")).click();
        Assert.assertEquals(driver.findElement(By.linkText("HP LP3065")).getText(), "HP LP3065");
    }

    @Test(groups = "Laptops")
    public void testRemovingElements() {
        driver.findElement(By.xpath("//a[text()='HP LP3065']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/button[@data-original-title='Compare this Product']")).click();
        driver.findElement(By.id("compare-total")).click();
        driver.findElement(By.linkText("Remove")).click();
        Assert.assertTrue(driver.findElements(By.linkText("HP LP3065")).isEmpty());
    }
}