package com.softserve.edu.opencart.db.service;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.opencart.data.user.IUser;
import com.softserve.edu.opencart.data.user.User;
import com.softserve.edu.opencart.data.user.UserColumns;
import com.softserve.edu.opencart.db.dao.CustomerLoginDao;
import com.softserve.edu.opencart.db.entity.CustomerLogin;

public class UserService {

	private CustomerLoginDao customerLoginDao;
	
	public UserService() {
		customerLoginDao = new CustomerLoginDao();
	}
	
	public List<IUser> getAllUsers() {
		List<IUser> result = new ArrayList<>();
		List<String> row;
		for (CustomerLogin customerLogin : customerLoginDao.getAllCustomerLogins()) {
			row = new ArrayList<>();
			for (int i = 0; i < UserColumns.values().length; i++) {
				row.add(new String());
			}
			row.set(UserColumns.EMAIL.getIndex(), customerLogin.getEmail());
			result.add(User.getByList(row));
		}
		return result;
	}

	public List<IUser> getLockedUsers() {
		List<IUser> result = new ArrayList<>();
		List<String> row;
		for (CustomerLogin customerLogin : customerLoginDao.getAllCustomerLogins()) {
			if (customerLogin.getTotal() > 4 ) {
				row = new ArrayList<>();
				for (int i = 0; i < UserColumns.values().length; i++) {
					row.add(new String());
				}
				row.set(UserColumns.EMAIL.getIndex(), customerLogin.getEmail());
				System.out.println("\t\trow = " + row);
				result.add(User.getByList(row));
			}
		}
		return result;
	}

	public boolean isLockedUser(IUser user) {
		System.out.println("\t\tisLockedUser(user): " + user.getEMail());
		boolean result = false;
		for (IUser current : getLockedUsers()) {
			System.out.println("\t\tisLockedUser(user), current: " + current.getEMail());
			if (current.getEMail().equals(user.getEMail())) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void unlockUser(IUser user) {
		CustomerLogin customerLogin = new CustomerLogin(0, "",
				user.getEMail(), 0, "", "");
		customerLoginDao.unlockCustomerLogin(customerLogin);
	}

	public void unlockAllUsers() {
		for (IUser currentUser : getLockedUsers()) {
			unlockUser(currentUser);
		}
	}

}
