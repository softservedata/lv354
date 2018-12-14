package com.softserve.edu;

public final class UserRepo {
	
	private UserRepo() {
	}

    public static IUser defaultUser() {
        return getCustomer();
    }

	public static IUser getAdmin() {
		return User.get()
    			.setFirstname("firstname000")
    			.setLastname("lastname000")
    			.setEMail("eMail000")
    			.setTelephone("telephone")
    			.setAddress1("address1")
    			.setCity("city000")
    			.setPostCode("postCode000")
    			.setCountry("country000")
    			.setRegionState("regionState000")
    			.setPassword("qwerty")
    			.setFax("fax000")
    			.build();
	}

	public static IUser getCustomer() {
		return User.get()
    			.setFirstname("firstname000")
    			.setLastname("lastname000")
    			.setEMail("eMail000")
    			.setTelephone("telephone")
    			.setAddress1("address1")
    			.setCity("city000")
    			.setPostCode("postCode000")
    			.setCountry("country000")
    			.setRegionState("regionState000")
    			.setPassword("qwerty")
    			.setFax("fax000")
    	    	.setCompany("company000")
    	    	.setAddress2("address2000")
    	    	.setSubscribe(true)
    			.build();
	}

//    public static List<IUser> fromCsv(String filename) {
//    	return User.getByList(new CSVReader(filename).getAllCells());
//    }

}
