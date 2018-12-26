package com.softserve.edu.opencart.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.data.application.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.application.IApplicationSource;
import com.softserve.edu.opencart.data.application.ApplicationSource;

import io.qameta.allure.Attachment;

//import io.qameta.allure.Attachment;

public class ApplicationTestRunner {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@BeforeClass
	public void beforeClass(ITestContext context) {
		log.info("@BeforeClass start");
		// Application.get(ApplicationSourceRepository.EpizyChrome());
		//Application.get(ApplicationSourceRepository.localChrome());
		IApplicationSource currentApplicationSource = ApplicationSourceRepository.localChrome();
		if (!System.getProperty("continuous.integration").equals("false")) {
			((ApplicationSource) currentApplicationSource)
				.setBrowserName(BrowserWrapper.Browsers.CHROME_WITHOUTUI.toString());
		}
		Application.get(currentApplicationSource);
		// Application.get(ApplicationSourceRepository.localChromeWithoutUI());
		log.info("@BeforeClass done");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("@AfterClass start");
		Application.remove();
		log.info("@AfterClass done");
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("@BeforeMethod start, ThreadId = " + Thread.currentThread().getId());
		Application.get().loadApplication();
		log.info("@BeforeMethod done");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult testResult) {
		log.info("method: " + testResult.getName() + "  Success: " + testResult.isSuccess());
		// log.info("@AfterMethod start, ThreadId = " + Thread.currentThread().getId());
		Reporter.setCurrentTestResult(testResult);
		log.info("@AfterMethod done" + TestResultUtils.testResultMessage(testResult));
		//
		// for Allure
		if (testResult.getStatus() == ITestResult.FAILURE) {
			prepareImageAttach();
		}
		Application.get().logout();
	}

	// TODO Remove method. For Demo Only
	protected void delayExecution(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepareImageAttach() {
		String currentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		saveImageAttach("./" + currentTime + "_screenshot.png");
	}

	@Attachment(value = "{0}", type = "image/png")
	public byte[] saveImageAttach(String attachName) {
		byte[] result = null;
		WebDriver driver = Application.get().getBrowser().getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			result = Files.readAllBytes(scrFile.toPath());
		} catch (IOException e) {
			// TODO Create Custom Exception 
			e.printStackTrace();
		}
		return result;
	}
}