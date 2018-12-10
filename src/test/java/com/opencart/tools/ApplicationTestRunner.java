package com.opencart.tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.opencart.data.application.ApplicationSourceRepository;

public class ApplicationTestRunner {
    @BeforeClass
    public void beforeClass(ITestContext context) {
        Application.get(ApplicationSourceRepository.localChrome());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Application.remove();
    }

    @BeforeMethod
    public void beforeMethod() {
        Application.get().loadApplication();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.isSuccess()) {
            System.out.println(result.getName() + " done");
        } else {
            System.out.println(result.getName() + " failed\n" + result.getThrowable().toString());
            String fileNamePrefix = makeFileNamePrefix();
            takeScreenshot(fileNamePrefix);
            takePageSource(fileNamePrefix);
        }
    }

    protected void delayExecution(long seconds) {
        try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public void moveToFooter() {
        Actions action = new Actions(Application.get().getBrowser().getDriver());
        action.moveToElement(Application.get().getBrowser().getDriver().
                findElement(By.xpath("//h5[contains(text(), 'Information')]"))).perform();
    }

    private void takeScreenshot(String fileNamePrefix) {
        File scrFile = ((TakesScreenshot) Application.get().getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + fileNamePrefix + "_screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void takePageSource(String fileNamePrefix) {
        try {
            PrintWriter w = new PrintWriter(new File("./" + fileNamePrefix + "_pagesource.txt"));
            w.print(Application.get().getBrowser().getDriver().getPageSource());
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