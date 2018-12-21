package com.softserve.edu.opencart.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import com.softserve.edu.opencart.db.ConnectionManager;
import com.softserve.edu.opencart.db.entity.CustomerLogin;

public class CustomerLoginDao {
	private final String QUERY_NOT_FOUND = "Query not found %s";
	private final String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	private final String DATABASE_READING_ERROR = "Database Reading Error";
	private final String DATABASE_INPUT_ERROR = "Database Input Error";

	// Read
	public List<CustomerLogin> getAllCustomerLogins() {
		String query = CustomerLogin.READ_LOGINS;
		List<CustomerLogin> all = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;
		String[] queryResult;
		if (query == null) {
			// TODO Develop Custom Exception
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				for (int i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(new CustomerLogin(
						Long.parseLong(queryResult[0]),   // long customer_login_id
						queryResult[1], 				  // String ip
						queryResult[2], 				  // String email
						Integer.parseInt(queryResult[3]), // int total
						queryResult[4], 				  // String date_added
						queryResult[5]  			      // String date_modified
						));
			}
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		if (all.isEmpty()) {
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	// Update
	public boolean unlockCustomerLogin(CustomerLogin customerLogin) {
		String query = CustomerLogin.UNLOCK_LOGIN;
		boolean result = false;
		Statement statement = null;
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			// TODO CHECK!
			query = String.format(query, customerLogin.getEmail());
			result = statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_INPUT_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}

}
