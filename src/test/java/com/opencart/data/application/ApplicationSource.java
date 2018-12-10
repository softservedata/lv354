package com.opencart.data.application;

public class ApplicationSource implements IApplicationSource {

	// Browser Data
	private String browserName;
	private String driverPath;

	// Implicit and Explicit Waits
	private long implicitWaitTimeOut;

	private boolean consoleOutput;
	// URLs
	private String baseUrl;
	// private String userLoginUrl;
	private String userLogoutUrl;

	// Database Connection
	private String databaseUrl;
	private String databaseLogin;
	private String databasePassword;

	// TODO Develop Builder
	public ApplicationSource(String browserName, String driverPath, long implicitWaitTimeOut, boolean consoleOutput,
			String baseUrl, String userLogoutUrl,
			String databaseUrl, String databaseLogin, String databasePassword) {
		this.browserName = browserName;
		this.driverPath = driverPath;
		this.implicitWaitTimeOut = implicitWaitTimeOut;
		this.consoleOutput = consoleOutput;
		this.baseUrl = baseUrl;
		this.userLogoutUrl = userLogoutUrl;
		this.databaseUrl = databaseUrl;
		this.databaseLogin = databaseLogin;
		this.databasePassword = databasePassword;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}

	public void setImplicitWaitTimeOut(long implicitWaitTimeOut) {
		this.implicitWaitTimeOut = implicitWaitTimeOut;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setUserLogoutUrl(String userLogoutUrl) {
		this.userLogoutUrl = userLogoutUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public void setDatabaseLogin(String databaseLogin) {
		this.databaseLogin = databaseLogin;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}


	public String getBrowserName() {
		return browserName;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public long getImplicitWaitTimeOut() {
		return implicitWaitTimeOut;
	}

	public boolean getConsoleOutput() {
		return consoleOutput;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getUserLogoutUrl() {
		return userLogoutUrl;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseLogin() {
		return databaseLogin;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}
}