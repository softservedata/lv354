package com.softserve.edu;

public final class UserRepository {
	private static volatile UserRepository instance = null;

    private UserRepository() {
    }
    
    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser defaultUser() {
        return customer();
    }

	public IUser customer() {
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

}
