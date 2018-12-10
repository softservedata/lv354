package com.opencart.tools;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencart.data.application.IApplicationSource;
import com.opencart.data.application.TestStatus;

public class BrowserWrapper {

	private interface IBrowser {
		WebDriver getBrowser(IApplicationSource applicationSource);
	}

	private static class Firefox5xTemporary implements IBrowser {
		public WebDriver getBrowser(IApplicationSource applicationSource) {
			System.setProperty("webdriver.gecko.driver", applicationSource.getDriverPath());
			return new FirefoxDriver();
		}
	}

	private static class ChromeTemporary implements IBrowser {
		public WebDriver getBrowser(IApplicationSource applicationSource) {
			System.setProperty("webdriver.chrome.driver", applicationSource.getDriverPath());
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		}
	}

	private static class ChromeProfile implements IBrowser {
		public WebDriver getBrowser(IApplicationSource applicationSource) {
			System.setProperty("webdriver.chrome.driver", applicationSource.getDriverPath());
			String userProfile = System.getenv("HOMEPATH") + "\\AppData\\Local\\Google\\Chrome\\User Data";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir=" + userProfile);
			return new ChromeDriver(options);
		}
	}

	private static class ChromeWithoutUI implements IBrowser {
		public WebDriver getBrowser(IApplicationSource applicationSource) {
			System.setProperty("webdriver.chrome.driver", applicationSource.getDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-proxy-server");
			options.addArguments("--ignore-certificate-errors");
			WebDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			return driver;
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	public static enum Browsers {
		DEFAULT_TEMPORARY("ChromeTemporary", new ChromeTemporary()),
		FIREFOX5X_TEMPORARY("FireFox5xTemporary", new Firefox5xTemporary()),
		CHROME_TEMPORARY("ChromeTemporary", new ChromeTemporary()),
		CHROME_PROFILE("ChromeProfile", new ChromeProfile()),
		CHROME_WITHOUTUI("ChromeWithoutUI", new ChromeWithoutUI());

		private String browserName;
		private IBrowser browser;

		private Browsers(String browserName, IBrowser browser) {
			this.browserName = browserName;
			this.browser = browser;
		}

		public WebDriver runBrowser(IApplicationSource applicationSource) {
			return browser.getBrowser(applicationSource);
		}

		@Override
		public String toString() {
			return browserName;
		}
	}
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private WebDriver driver;
	private TestStatus testStatus;

	public BrowserWrapper(IApplicationSource applicationSource) {
		initWebDriver(applicationSource);
		initElements();
	}

	private void initWebDriver(IApplicationSource applicationSource) {
		Browsers currentBrowser = Browsers.DEFAULT_TEMPORARY;
		for (Browsers browser : Browsers.values()) {
			if (browser.toString().toLowerCase()
					.contains(applicationSource.getBrowserName().toLowerCase())) {
				currentBrowser = browser;
				break;
			}
		}
		driver = currentBrowser.runBrowser(applicationSource);
		driver.manage().timeouts().implicitlyWait(applicationSource.getImplicitWaitTimeOut(), TimeUnit.SECONDS);
	}

	private void initElements() {
		testStatus = new TestStatus();
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public TestStatus getTestStatus() {
		return testStatus;
	}

	public void openUrl(String url) {
		getDriver().get(url);
	}

	public void navigateForward() {
		getDriver().navigate().forward();
	}

	public void navigateBack() {
		getDriver().navigate().back();
	}

	public void refreshPage() {
		getDriver().navigate().refresh();
	}

	public void quit() {
		if (getDriver() != null) {
			getDriver().quit();
			driver = null;
		}
	}

}