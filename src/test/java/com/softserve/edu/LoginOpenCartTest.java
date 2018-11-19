package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginOpenCartTest extends TestRunner {
    private String email = "test.login.open.cart@gmail.com";
    private String password = "qwerty123=";
    private WebElement emailField;
    private WebElement passwordField;

    @DataProvider(name = "loginCredentialsValid")
    public Object[][] loginSuccessful() {
        return new Object[][]{
                {email, password},
                {email.toUpperCase(), password},
        };
    }

    @Test(dataProvider = "loginCredentialsValid")
    public void loginWithValidCredentialsTest(String login, String password) {
        driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]")).click();
        emailField = driver.findElement(By.id("input-email"));
        passwordField = driver.findElement(By.id("input-password"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(login);

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertTrue((!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty()), "User is not logged in");


        //SoftAssert softAssert = new SoftAssert();
        //softAssert.assertTrue(!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty(), "User is not logged in");
        //driver.findElement(By.cssSelector(".fa-user + span")).click();
        //driver.findElement(By.xpath("//li/a[contains (@href, \"logout\")]")).click();
        //Assert.assertTrue(!driver.findElements(By.xpath("//li/a[contains (@href,\"login\")]")).isEmpty(), "User is not logged out");
        //softAssert.assertAll();
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
        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(login);


        passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.cssSelector(".alert.alert-danger"));
        Assert.assertTrue(driver.findElements(By.xpath("//input[@type='submit']")).size() == 1, "User is logged in with invalid credentials");

    }

    @Test
    public void forgetPasswordTest() throws InterruptedException {
        //click on Search field to focusing
        driver.findElement(By.cssSelector(".form-control.input-lg")).click();
        driver.findElement(By.cssSelector("#top-links a.dropdown-toggle")).click();
        driver.findElement(By.xpath("//div[@id='top-links']//a[contains(@href,'route=account/login')]")).click();
        driver.findElement(By.cssSelector(".form-group a")).click();

        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(By.className("btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Success alert is not displayed");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://mail.google.com','gmailTab');");
        driver.switchTo().window("gmailTab");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("initialView"))));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement gmailLoginField = driver.findElement(By.id("identifierId"));
        gmailLoginField.click();
        gmailLoginField.clear();
        gmailLoginField.sendKeys(email);
        driver.findElement(By.id("identifierNext")).click();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@type='password']"))));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement((By.xpath("//input[@type='password']")));
        driver.findElement((By.xpath("//input[@type='password']"))).click();
        driver.findElement((By.xpath("//input[@type='password']"))).clear();
        driver.findElement((By.xpath("//input[@type='password']"))).sendKeys("qwerty123=");
        driver.findElement(By.id("passwordNext")).click();
        driver.findElement(By.cssSelector(".zA.zE:first-of-type")).click();
        Thread.sleep(2000);
        List listOfResetLinks = driver.findElements(By.xpath("//a[contains(@href, 'reset')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", listOfResetLinks.get(listOfResetLinks.size() - 1));
        Thread.sleep(2000);

        driver.findElements(By.xpath("//a[contains(@href, 'reset')]")).get(0).click();
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(list.get(list.size() - 1));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("input-password"))));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement setPasswordField = driver.findElement(By.id("input-password"));
        setPasswordField.click();
        setPasswordField.clear();
        setPasswordField.sendKeys("qwerty123=");
        WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));
        confirmPasswordField.click();
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys("qwerty123=");
        driver.findElement(By.className("btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Element alert-success is not displayed");

        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("qwerty123=");
        driver.findElement(By.xpath("//input[@type =\"submit\"]")).click();
        Assert.assertTrue((!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty()), "User is not logged in");


       /* driver.findElement(By.cssSelector(".fa-user + span")).click();
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Logout')]")).click();
        WebElement continueButton = driver.findElement(By.className("btn-primary"));
        continueButton.click();
        wait.until(ExpectedConditions.invisibilityOf(continueButton));

        Assert.assertTrue(driver.findElement(By.className("owl-carousel")).isDisplayed(), "Logout is failed");
*/
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
        passwordField = driver.findElement(By.id("input-password"));
        emailField = driver.findElement(By.id("input-email"));
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
        emailField = driver.findElement(By.id("input-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("test.login.open.cart2@gmail.com");
        passwordField = driver.findElement(By.id("input-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("qwerty123=");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertTrue(!driver.findElements(By.xpath("//a[contains (@href,\"password\")]")).isEmpty(), "User is not logged in");

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
        Assert.assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(), "Success alert is not displayed");

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
        Assert.assertTrue(!driver.findElements(By.xpath("//a[contains (@href,\"edit\")]")).isEmpty(), "User is not logged in");
    }


}


