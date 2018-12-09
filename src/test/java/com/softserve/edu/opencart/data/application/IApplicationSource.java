package com.softserve.edu.opencart.data.application;

public interface IApplicationSource {

    String getBrowserName();

    String getDriverPath();

    long getImplicitWaitTimeOut();

    boolean getConsoleOutput();

    String getBaseUrl();

    String getUserLogoutUrl();

}
