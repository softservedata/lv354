package com.opencart.data.application;

import java.sql.Driver;

public interface IApplicationSource {

	String getBrowserName();

	String getDriverPath();

	long getImplicitWaitTimeOut();

	String getBaseUrl();

	String getUserLogoutUrl();
}
