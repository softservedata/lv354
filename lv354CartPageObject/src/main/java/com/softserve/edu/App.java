package com.softserve.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        //
    	// 1. Classic Constructor
//    	User user = new User("firstname0", "lastname0", "eMail0",
//    			"telephone0", "address10", "city0",
//    			"postCode0", "country0", "regionState0",
//    			"password0", "fax0", "company0",
//    			"address20", true);
//    	System.out.println("firstname = " + user.getFirstname());
    	//
    	// 2. Default Constructor. Use Setters
//    	User user = new User();
//    	user.setFirstname("firstname00");
//    	user.setLastname("lastname00");
//    	user.setEMail("eMail00");
//    	user.setTelephone("telephone00");
//    	user.setAddress1("address100");
//    	user.setCity("city00");
//    	user.setPostCode("postCode00");
//    	user.setCountry("country00");
//    	user.setRegionState("regionState00");
//    	user.setPassword("password00");
//    	user.setFax("fax00");
//    	user.setCompany("company00");
//    	user.setAddress2("address200");
//    	user.setSubscribe(true);
//    	System.out.println("firstname = " + user.getFirstname());
    	//
    	// 3. Add Fluent Interface
//    	User user = new User()
//	    	.setFirstname("firstname00")
//	    	.setLastname("lastname00")
//	    	.setEMail("eMail00")
//	    	.setTelephone("telephone00")
//	    	.setAddress1("address100")
//	    	.setCity("city00")
//	    	.setPostCode("postCode00")
//	    	.setCountry("country00")
//	    	.setRegionState("regionState00")
//	    	.setPassword("password00")
//	    	.setFax("fax00")
//	    	.setCompany("company00")
//	    	.setAddress2("address200")
//	    	.setSubscribe(true);
//    	System.out.println("firstname = " + user.getFirstname());
    	//
    	// 4. Add Static Factory    	
//    	User user = User.get()
//    	    	.setFirstname("firstname00")
//    	    	.setLastname("lastname00")
//    	    	.setEMail("eMail00")
//    	    	.setTelephone("telephone00")
//    	    	.setAddress1("address100")
//    	    	.setCity("city00")
//    	    	.setPostCode("postCode00")
//    	    	.setCountry("country00")
//    	    	.setRegionState("regionState00")
//    	    	.setPassword("password00")
//    	    	.setFax("fax00")
//    	    	.setCompany("company00")
//    	    	.setAddress2("address200")
//    	    	.setSubscribe(true);
//        	System.out.println("firstname = " + user.getFirstname());
        //
        // 5. Add Builder
//    	User user = User.get()
//    			.setFirstname("firstname000")
//    			.setLastname("lastname000")
//    			.setEMail("eMail000")
//    			.setTelephone("telephone")
//    			.setAddress1("address1")
//    			.setCity("city000")
//    			.setPostCode("postCode000")
//    			.setCountry("country000")
//    			.setRegionState("regionState000")
//    			.setPassword("qwerty")
//    			.setFax("fax000")
//    			.build();
//    	System.out.println("firstname = " + user.setFirstname("Hello")); // Defect
//    	// Code...
//    	System.out.println("firstname = " + user.getFirstname()); // Invalid Result
    	//
    	// 6. Add Dependency Inversion
//    	IUser user = User.get()
//    			.setFirstname("firstname000")
//    			.setLastname("lastname000")
//    			.setEMail("eMail000")
//    			.setTelephone("telephone")
//    			.setAddress1("address1")
//    			.setCity("city000")
//    			.setPostCode("postCode000")
//    			.setCountry("country000")
//    			.setRegionState("regionState000")
//    			.setPassword("qwerty")
//    			.setFax("fax000")
//    			.build();
    	//System.out.println("firstname = " + user.setFirstname("Hello")); // Compile Error
    	//System.out.println("firstname = " + ((User) user).setFirstname("Hello")); // Code Smell 
    	// Code...
//    	System.out.println("firstname = " + user.getFirstname());
    	//
    	// 7. Add Repository
    	//IUser user = UserRepo.getAdmin();
    	//System.out.println("firstname = " + user.getFirstname());
    	//
    	// 8. Add Singleton
    	IUser user = UserRepository.get().defaultUser();
    	System.out.println("firstname = " + user.getFirstname());
    }
}
