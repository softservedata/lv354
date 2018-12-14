package com.softserve.edu;

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
		this.firstname = firstname;
		return this;
	}

	public IEMail setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ITelephone setEMail(String eMail) {
		this.eMail = eMail;
		return this;
	}

	public IAddress1 setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public ICity setAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public IPostCode setCity(String city) {
		this.city = city;
		return this;
	}

	public ICountry setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}

	public IRegionState setCountry(String country) {
		this.country = country;
		return this;
	}

	public IPassword setRegionState(String regionState) {
		this.regionState = regionState;
		return this;
	}

	public IBuildUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public IBuildUser setFax(String fax) {
		this.fax = fax;
		return this;
	}

	public IBuildUser setCompany(String company) {
		this.company = company;
		return this;
	}

	public IBuildUser setAddress2(String address2) {
		this.address2 = address2;
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

}
