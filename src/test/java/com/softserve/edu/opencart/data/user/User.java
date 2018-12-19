package com.softserve.edu.opencart.data.user;

import java.util.ArrayList;
import java.util.List;

enum UserColumns {
	FIRST_NAME(0),
	LAST_NAME(1),
	EMAIL(2),
	TELEPHONE(3),
	ADDRESS1(4),
	CITY(5),
	POST_CODE(6),
	COUNTRY(7),
	REGION_STATE(8),
	PASSWORD(9),
	FAX(10),
	COMPANY(11),
	ADDRESS2(12),
	SUBSCRIBE(13);
	//
	private int index;

	private UserColumns(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}

interface IFirstname {
	ILastname setFirstname(String firstname);
}

interface ILastname {
	IEMail setLastname(String lastname);
}

interface IEMail {
	ITelephone setEMail(String eMail);
}

interface ITelephone {
	IAddress1 setTelephone(String telephone);
}

interface IAddress1 {
	ICity setAddress1(String address1);
}

interface ICity {
	IPostCode setCity(String city);
}

interface IPostCode {
	ICountry setPostCode(String postCode);
}

interface ICountry {
	IRegionState setCountry(String country);
}

interface IRegionState {
	IPassword setRegionState(String regionState);
}

interface IPassword {
	IBuildUser setPassword(String password);
}

interface IBuildUser {
	IBuildUser setFax(String fax);
	IBuildUser setCompany(String company);
	IBuildUser setAddress2(String address2);
	IBuildUser setSubscribe(boolean subscribe);
	//
	//User build();
	// 6. Add Dependency Inversion
	IUser build();
}

public class User implements IFirstname, ILastname, IEMail,
		ITelephone, IAddress1, ICity, IPostCode, ICountry,
		IRegionState, IPassword, IBuildUser, IUser {
	private static final String EMAIL_SEPARATOR = "@";
	private static final String SUCCESS_SUBSCRIBE = "true";
	private final String EMPTY_STRING = new String();
	// Required fields
	private String firstname;
	private String lastname;
	private String eMail;
	private String telephone;
	private String address1;
	private String city;
	private String postCode;
	private String country;
	private String regionState;
	private String password;
	// Optional fields
	private String fax;
	private String company;
	private String address2;
	private boolean subscribe;

	// 1. Classic Constructor
//	public User(String firstname, String lastname, String eMail,
//			String telephone, String address1, String city,
//			String postCode, String country, String regionState,
//			String password, String fax, String company,
//			String address2, boolean subscribe) {
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.eMail = eMail;
//		this.telephone = telephone;
//		this.address1 = address1;
//		this.city = city;
//		this.postCode = postCode;
//		this.country = country;
//		this.regionState = regionState;
//		this.password = password;
//		this.fax = fax;
//		this.company = company;
//		this.address2 = address2;
//		this.subscribe = subscribe;
//	}

	// 2. Default Constructor. Use Setters
	// public User() {
	// }

	// 4. Add Static Factory. Private Constructor
	private User() {
	}

	// 4. Add Static Factory
	//public static User get() {
	// 5. Add Builder
	public static IFirstname get() {
		return new User();
	}

	// Setters

	// public void setFirstname(String firstname) {
	// 3. Add Fluent Interface
	//public User setFirstname(String firstname) {
	// 5. Add Builder
	public ILastname setFirstname(String firstname) {
		this.firstname = firstname != null ? firstname : EMPTY_STRING;
		return this;
	}

	public IEMail setLastname(String lastname) {
		this.lastname = lastname != null ? lastname : EMPTY_STRING;
		return this;
	}

	public ITelephone setEMail(String eMail) {
		this.eMail = eMail != null ? eMail : EMPTY_STRING;
		return this;
	}

	public IAddress1 setTelephone(String telephone) {
		this.telephone = telephone != null ? telephone : EMPTY_STRING;
		return this;
	}

	public ICity setAddress1(String address1) {
		this.address1 = address1 != null ? address1 : EMPTY_STRING;
		return this;
	}

	public IPostCode setCity(String city) {
		this.city = city != null ? city : EMPTY_STRING;
		return this;
	}

	public ICountry setPostCode(String postCode) {
		this.postCode = postCode != null ? postCode : EMPTY_STRING;
		return this;
	}

	public IRegionState setCountry(String country) {
		this.country = country != null ? country : EMPTY_STRING;
		return this;
	}

	public IPassword setRegionState(String regionState) {
		this.regionState = regionState != null ? regionState : EMPTY_STRING;
		return this;
	}

	public IBuildUser setPassword(String password) {
		this.password = password != null ? password : EMPTY_STRING;
		return this;
	}

	public IBuildUser setFax(String fax) {
		this.fax = fax != null ? fax : EMPTY_STRING;
		return this;
	}

	public IBuildUser setCompany(String company) {
		this.company = company != null ? company : EMPTY_STRING;
		return this;
	}

	public IBuildUser setAddress2(String address2) {
		this.address2 = address2 != null ? address2 : EMPTY_STRING;
		return this;
	}

	public IBuildUser setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
		return this;
	}

	// 5. Add Builder
	//public User build() {
	// 6. Add Dependency Inversion
	public IUser build() {
		return this;
	}

	// Getters

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEMail() {
		return eMail;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress1() {
		return address1;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCountry() {
		return country;
	}

	public String getRegionState() {
		return regionState;
	}

	public String getPassword() {
		return password;
	}

	public String getFax() {
		return fax;
	}

	public String getCompany() {
		return company;
	}

	public String getAddress2() {
		return address2;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	// Static Factories
	
	public static IUser getByList(List<String> row) {
		return User.get()
			.setFirstname(row.get(UserColumns.FIRST_NAME.getIndex()))
			.setLastname(row.get(UserColumns.LAST_NAME.getIndex()))
			.setEMail(row.get(UserColumns.EMAIL.getIndex()))
			.setTelephone(row.get(UserColumns.TELEPHONE.getIndex()))
			.setAddress1(row.get(UserColumns.ADDRESS1.getIndex()))
			.setCity(row.get(UserColumns.CITY.getIndex()))
			.setPostCode(row.get(UserColumns.POST_CODE.getIndex()))
			.setCountry(row.get(UserColumns.COUNTRY.getIndex()))
			.setRegionState(row.get(UserColumns.REGION_STATE.getIndex()))
			.setPassword(row.get(UserColumns.PASSWORD.getIndex()))
			.setFax(row.get(UserColumns.FAX.getIndex()))
	    	.setCompany(row.get(UserColumns.COMPANY.getIndex()))
	    	.setAddress2(row.get(UserColumns.ADDRESS2.getIndex()))
	    	.setSubscribe(row.get(UserColumns.SUBSCRIBE.getIndex())
	    			.toLowerCase().equals(SUCCESS_SUBSCRIBE))
			.build();
	}

	public static List<IUser> getByLists(List<List<String>> rows) {
		List<IUser> result = new ArrayList<>();
		// TODO Verify Test Data as Valid
		if (!rows.get(0).get(UserColumns.EMAIL.getIndex())
				.contains(EMAIL_SEPARATOR)) {
    		rows.remove(0);
    	}
		for (List<String> currentRow : rows) {
			result.add(getByList(currentRow));
		}
		return result;
	}

}
