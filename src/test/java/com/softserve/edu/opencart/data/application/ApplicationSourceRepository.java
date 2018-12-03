package com.softserve.edu.opencart.data.application;

import java.sql.Driver;
import java.sql.SQLException;

public final class ApplicationSourceRepository {
	public final static String DB_CONNECTION_ERROR = "DB Connection Error, %s";

	private ApplicationSourceRepository() {
	}

	public static IApplicationSource defaultParameters() {
		return localChrome();
	}

	public static IApplicationSource epizyChrome() {
		// TODO Develop JdbcDriverWrapper (ConnectionManager) class
		Driver jdbcDriver;
		try {
			jdbcDriver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
		}
		// System.out.println("***PASS: " +
		// SystemPropertyUtils.getExistingProperty("database-password",
		// "DATABASE_PASSWORD"));
		//
		return new ApplicationSource("ChromeTemporary", // "ChromeTemporary", // "ChromeProfile",
				ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
				// "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
				30, true,
				// "http://192.168.103.210/opencart/upload",
				// "http://atqc-shop.epizy.com",
				// "http://192.168.103.191/opencart/upload/",
				"http://taqc-opencart.epizy.com/",
				// "http://nazaronoc.000webhostapp.com/"
				"jdbc:mysql://192.168.103.210:3306/opencart", "lv304", "Lv344_TAQC",
				// SystemPropertyUtils.getExistingProperty("database-password",
				// "DATABASE_PASSWORD"),
				jdbcDriver);
	}

	public static IApplicationSource localChrome() {
		// TODO Develop JdbcDriverWrapper (ConnectionManager) class
		Driver jdbcDriver;
		try {
			jdbcDriver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
		}
		// System.out.println("***PASS: " +
		// SystemPropertyUtils.getExistingProperty("database-password",
		// "DATABASE_PASSWORD"));
		//
		return new ApplicationSource("ChromeTemporary", // "ChromeTemporary", // "ChromeProfile",
				ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
				// "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
				10, true,
				// "http://192.168.103.210/opencart/upload",
				// "http://atqc-shop.epizy.com",
				"http://192.168.103.172/opencart/upload/",
				// "http://taqc-opencart.epizy.com/",
				// "http://nazaronoc.000webhostapp.com/"
				"jdbc:mysql://192.168.103.172:3306/opencart", "lv354", "Lv354_TAQC",
				// SystemPropertyUtils.getExistingProperty("database-password",
				// "DATABASE_PASSWORD"),
				jdbcDriver);
	}

	public static IApplicationSource localChromeWithoutUI() {
		// TODO Develop JdbcDriverWrapper (ConnectionManager) class
		Driver jdbcDriver;
		try {
			jdbcDriver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(String.format(DB_CONNECTION_ERROR, e));
		}
		// System.out.println("***PASS: " +
		// SystemPropertyUtils.getExistingProperty("database-password",
		// "DATABASE_PASSWORD"));
		//
		return new ApplicationSource("ChromeWithoutUI", // "ChromeTemporary", // "ChromeProfile",
				ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
				// "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
				10, true,
				// "http://192.168.103.210/opencart/upload",
				// "http://atqc-shop.epizy.com",
				"http://192.168.103.172/opencart/upload/",
				// "http://taqc-opencart.epizy.com/",
				// "http://nazaronoc.000webhostapp.com/"
				"jdbc:mysql://192.168.103.172:3306/opencart", "lv354", "Lv354_TAQC",
				// SystemPropertyUtils.getExistingProperty("database-password",
				// "DATABASE_PASSWORD"),
				jdbcDriver);
	}

}
