package com.softserve.edu.opencart.db.entity;

public class CustomerLogin {
	public static final String READ_LOGINS = "select * from oc_customer_login;";
	public static final String UNLOCK_LOGIN = "UPDATE oc_customer_login SET total='%s' WHERE email LIKE '%s%%';";
	//
	private long customer_login_id;
	private String ip;
	private String email;
	private int total;
	private String date_added;
	private String date_modified;
	
	// TODO Develop Builder
	public CustomerLogin(long customer_login_id, String ip, String email,
			int total, String date_added, String date_modified) {
		this.customer_login_id = customer_login_id;
		this.ip = ip;
		this.email = email;
		this.total = total;
		this.date_added = date_added;
		this.date_modified = date_modified;
	}

	// getters

	public long getCustomer_login_id() {
		return customer_login_id;
	}

	public String getIp() {
		return ip;
	}

	public String getEmail() {
		return email;
	}

	public int getTotal() {
		return total;
	}

	public String getDate_added() {
		return date_added;
	}

	public String getDate_modified() {
		return date_modified;
	}

	// setters
	
	public void setCustomer_login_id(long customer_login_id) {
		this.customer_login_id = customer_login_id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}
	
}
