package com.softserve.edu.opencart.db.service;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.opencart.data.user.User;
import com.softserve.edu.opencart.data.user.UserColumns;
import com.softserve.edu.opencart.db.dao.CustomerLoginDao;
import com.softserve.edu.opencart.db.entity.CustomerLogin;

public class UserService {

	private CustomerLoginDao customerLoginDao;
	
	public UserService() {
		customerLoginDao = new CustomerLoginDao();
	}
	
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<>();
		for (CustomerLogin customerLogin : customerLoginDao.getAllCustomerLogins()) {
			for (int i = 0; i < UserColumns.values().length; i++) {
				//++++
			}
		}
		// TODO
		return result;
	}

	public List<User> getLockedUsers() {
		// TODO
		return null;
	}

	public void unlockUser(User user) {
		// TODO
	}

	public void unlockAllUsers() {
		// TODO
	}

}
