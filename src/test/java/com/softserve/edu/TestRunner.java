package com.softserve.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class TestRunner {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void getUrl() {
        driver.get("http://192.168.29.130/opencart/upload/");
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshotAndSource(ITestResult result) {
        if (!(result.isSuccess())) {
            System.out.println("Test " + result.getName() + " failed" + "\n\t" + result.getThrowable().toString());
            String fileNamePrefix = makeFileNamePrefix();
            takeScreenshot(fileNamePrefix);
            takePageSource(fileNamePrefix);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        driver.navigate().to("http://192.168.29.130/opencart/upload/");
        if ((driver.findElements(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Login')]"))).isEmpty()) {
            driver.findElement(By.cssSelector(".fa-user + span")).click();
            driver.findElement(By.xpath("//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[contains(text(),'Logout')]")).click();
        }
    }

    void downgradeImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    void upgradeImplicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    void delayExecution(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // Do not use
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenshot(String fileNamePrefix) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + fileNamePrefix + "_screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void takePageSource(String fileNamePrefix) {
        try {
            PrintWriter w = new PrintWriter(new File("./" + fileNamePrefix + "_pagesource.txt"));
            w.print(driver.getPageSource());
            w.flush();
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String makeFileNamePrefix() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss");
        return simpleDateFormat.format(new Date());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        driver.quit();
    }
}
