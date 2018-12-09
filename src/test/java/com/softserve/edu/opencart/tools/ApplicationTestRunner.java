package com.softserve.edu.opencart.tools;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.softserve.edu.opencart.data.application.ApplicationSourceRepository;

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

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            prepareImageAttach();
        }
        Application.get().logout();
    }

    public void prepareImageAttach() {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        saveImageAttach("./" + currentTime + "_screenshot.png");
    }

    public byte[] saveImageAttach(String attachName) {
        byte[] result = null;
        WebDriver driver = Application.get().getBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            result = Files.readAllBytes(scrFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}