package com.softserve.academy;

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
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestRunner {
    protected WebDriver driver;

    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        //this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://taqc-opencart.epizy.com/");
    }

    @BeforeMethod(groups = "Laptops")
    public void beforeMethodLaptop() {
        driver.findElement(By.linkText("Laptops & Notebooks")).click();
        driver.findElement(By.xpath("//a[text()='Show All Laptops & Notebooks']")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        driver.get("http://taqc-opencart.epizy.com/index.php?route=product/compare");
        while (!driver.findElements(By.linkText("Remove")).isEmpty()) {
            driver.findElement(By.linkText("Remove")).click();
        }
        if (result.isSuccess()) {
            System.out.println("+++test " + result.getName() + " done");
        } else {
            System.out.println("***test " + result.getName() + " failed" + "\n***\t" + result.getThrowable().toString());
            String fileNamePrefix = makeFileNamePrefix();
            takeScreenshot(fileNamePrefix);
            takePageSource(fileNamePrefix);
        }
        delayExecution(2);
    }

    protected void delayExecution(int seconds) {
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
}
