package com.softserve.edu.opencart.data.application;

import java.sql.Driver;

public class ApplicationSource implements IApplicationSource {

    // Browser Data
    private String browserName;
    private String driverPath;

    // Implicit and Explicit Waits
    private long implicitWaitTimeOut;
    // Reporter Console Output
    private boolean consoleOutput;
    //
    // URLs
    private String baseUrl;
    // private String userLoginUrl;
    private String userLogoutUrl;
    public ApplicationSource(String browserName, String driverPath, long implicitWaitTimeOut, boolean consoleOutput,
                             String baseUrl, String userLogoutUrl) {
        this.browserName = browserName;
        this.driverPath = driverPath;
        this.implicitWaitTimeOut = implicitWaitTimeOut;
        this.consoleOutput = consoleOutput;
        this.baseUrl = baseUrl;
        this.userLogoutUrl = userLogoutUrl;
        //
    }

    // setters

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setImplicitWaitTimeOut(long implicitWaitTimeOut) {
        this.implicitWaitTimeOut = implicitWaitTimeOut;
    }

    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setUserLogoutUrl(String userLogoutUrl) {
        this.userLogoutUrl = userLogoutUrl;
    }


    // getters

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


}