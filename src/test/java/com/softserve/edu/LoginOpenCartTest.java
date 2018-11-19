package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class LoginOpenCartTest extends TestRunner {
    private String email = "test.login.open.cart@gmail.com";


    @DataProvider(name = "loginCredentialsValid")
    public Object[][] loginSuccessful() {
        String password = "qwerty123=";
        return new Object[][]{
                {email, password},
                {email.toUpperCase(), password},
        };
    }

    @Test(dataProvider = "loginCredentialsValid")
    public void loginWithValidCredentialsTest(String login, String password) {
        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]")).click();
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertTrue((!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty()), "User is not logged in");
    }


    @DataProvider(name = "loginCredentialsInvalid")
    public Object[][] loginUnsuccessful() {
        return new Object[][]{
                {"test.login.open.cartgmail.com", "qwerty123="},
                {"test.login.open.cart", "qwerty123="},
                {"", "qwerty123="},
                {"test.login.open.cart@gmail.com", ""},
                {"test.login.open.cart@gmail.com", "werty123="},
                {"", ""},
                {"test.login.open.cart@gmail.com", "Qwerty123="},
        };
    }
    @Test(dataProvider = "loginCredentialsInvalid")
    public void loginWithInvalidCredentialsTest(String login, String password) {
        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]")).click();
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.cssSelector(".alert.alert-danger"));
        Assert.assertEquals(driver.findElements(By.xpath("//input[@type='submit']")).size(), 1, "User is logged in with invalid credentials");
    }


    @Test
    public void forgetPasswordTest() {
        //click on Search field to focusing
        driver.findElement(By.cssSelector(".form-control.input-lg")).click();
        driver.findElement(By.cssSelector("#top-links a.dropdown-toggle")).click();
        driver.findElement(By.xpath("//div[@id='top-links']//a[contains(@href,'route=account/login')]")).click();
        driver.findElement(By.cssSelector(".form-group a")).click();

        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(By.className("btn-primary")).click();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Success alert is not displayed");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://mail.google.com','gmailTab');");
        driver.switchTo().window("gmailTab");
        downgradeImplicitWait();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("initialView"))));
        upgradeImplicitWait();

        WebElement gmailLoginField = driver.findElement(By.id("identifierId"));
        gmailLoginField.click();
        gmailLoginField.clear();
        gmailLoginField.sendKeys(email);
        driver.findElement(By.id("identifierNext")).click();

        downgradeImplicitWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@type='password']"))));
        upgradeImplicitWait();
        WebElement gmailPasswordField = driver.findElement((By.xpath("//input[@type='password']")));
        gmailPasswordField.click();
        gmailPasswordField.clear();
        gmailPasswordField.sendKeys("qwerty123=");
        driver.findElement(By.id("passwordNext")).click();
        driver.findElement(By.cssSelector(".zA.zE:first-of-type")).click();
        delayExecution(2);
        List listOfResetLinks = driver.findElements(By.xpath("//a[contains(@href, 'reset')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", listOfResetLinks.get(listOfResetLinks.size() - 1));
        delayExecution(2);

        driver.findElements(By.xpath("//a[contains(@href, 'reset')]")).get(0).click();
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(list.get(list.size() - 1));
        downgradeImplicitWait();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("input-password"))));
        upgradeImplicitWait();

        WebElement setPasswordField = driver.findElement(By.id("input-password"));
        setPasswordField.click();
        setPasswordField.clear();
        setPasswordField.sendKeys("qwerty123=");
        WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));
        confirmPasswordField.click();
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys("qwerty123=");
        driver.findElement(By.className("btn-primary")).click();
        softAssert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Element alert-success is not displayed");

        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("qwerty123=");
        driver.findElement(By.xpath("//input[@type =\"submit\"]")).click();
        softAssert.assertTrue((!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty()), "User is not logged in");
        softAssert.assertAll();
    }


    @DataProvider
    public Object[][] invalidEmails() {
        return new Object[][]{
                {"unregisteredEmail@gmail.com"},
                {"test.login.open.cart@gmail..com"},
                {"test.login.open..cart@gmail.com"},
                {"test.login.open.cartgmail.com"},
                {"test.login.open..cart@gmail..com"},
                {" test.login.open.cart@gmail.com "},
                {"\"test.login.open.cart@gmail.com\""},
        };
    }
    @Test(dataProvider = "invalidEmails")
    public void forgetPasswordWithInvalidEmailTest(String email) {
        //click on Search field to focusing
        driver.findElement(By.cssSelector(".form-control.input-lg")).click();
        driver.findElement(By.cssSelector("#top-links a.dropdown-toggle")).click();
        driver.findElement(By.xpath("//div[@id='top-links']//a[contains(@href,'route=account/login')]")).click();
        driver.findElement(By.cssSelector(".form-group a")).click();
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(By.className("btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.className("alert-danger")).isDisplayed(), "Warning alert is not displayed");
    }


    @Test
    public void changePasswordTest() {
        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]")).click();
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("test.login.open.cart2@gmail.com");
        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("qwerty123=");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!driver.findElements(By.xpath("//a[contains (@href,\"password\")]")).isEmpty(), "User is not logged in");

        driver.findElement(By.xpath("//a[contains (@href,\"password\")]")).click();
        passwordField = driver.findElement(By.id("input-password"));
        String passwordToBeChanged = "qwerty1234";
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(passwordToBeChanged);
        WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));
        confirmPasswordField.click();
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(passwordToBeChanged);
        driver.findElement(By.className("btn-primary")).click();
        softAssert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Success alert is not displayed");

        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]")).click();
        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("test.login.open.cart2@gmail.com");

        passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(passwordToBeChanged);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        softAssert.assertTrue(!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty(), "User is not logged in");
        softAssert.assertAll();
    }
}


