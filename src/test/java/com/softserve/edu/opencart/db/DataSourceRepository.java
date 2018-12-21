package com.softserve.edu.opencart.db;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
	private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";

	private DataSourceRepository() {
	}

	public static DataSource getDefault() {
		return getMySqlOpenCart();
	}

	public static DataSource getMySqlOpenCart() {
		Driver sqlDriver;
		try {
			sqlDriver = new com.mysql.jdbc.Driver();
			//sqlDriver = new com.mysql.cj.jdbc.Driver();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(FAILED_JDBC_DRIVER);
		}
		return new DataSource(sqlDriver, "jdbc:mysql://192.168.103.172:3306/opencart?useSSL=false", "lv3X4", "Lv3X4_TAQC");
	}

	public static DataSource getSybaseLocalHost() {
		return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
				"jdbc:jtds:sqlserver://CLASS02/lv326;instance=SQLEXPRESS;", "db326", "db326");
	}

}