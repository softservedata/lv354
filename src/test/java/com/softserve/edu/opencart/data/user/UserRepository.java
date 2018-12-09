package com.softserve.edu.opencart.data.user;

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

    public IUser defaultValues() {
        return validRegistration();
    }

    public IUser validRegistration() {
        return User.get()
                .setFirstname("FirstTest")
                .setLastname("LastTest")
                .setEMail("test@correct.yep")
                .setTelephone("8800555")
                .setAddress1("AddressTest")
                .setCity("Kyiv")
                .setPostCode("8800")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("aezakmi")
                .setAddress2("Kyiv")
                .setCompany("TestTry")
                .setFax("8800555")
                .build();
    }

    public IUser voidRegistration() {
        return User.get()
                .setFirstname("")
                .setLastname("")
                .setEMail("")
                .setTelephone("")
                .setAddress1("")
                .setCity("")
                .setPostCode("")
                .setCountry("")
                .setRegionState("")
                .setPassword("")
                .setAddress2("")
                .setCompany("")
                .setFax("")
                .build();
    }

    public IUser admin() {
        return User.get()
                .setFirstname("altair")
                .setLastname("ahad")
                .setEMail("msergsi@gmail.com")
                .setTelephone("0630234560")
                .setAddress1("Lviv")
                .setCity("Lviv")
                .setPostCode("80800")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("aezakmi")
                .setFax("")
                .setAddress2("")
                .setCompany("")
                .build();
    }

    public IUser duplicateEmail() {
        return User.get()
                .setFirstname("Duplicate")
                .setLastname("Duplicate")
                .setEMail("Duplicate@Duplicate.Duplicate")
                .setTelephone("8800555")
                .setAddress1("Duplicate")
                .setCity("Kyiv")
                .setPostCode("8800")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("Duplicate")
                .setFax("Duplicate")
                .setAddress2("Duplicate")
                .setCompany("Duplicate")
                .build();
    }

    public IUser onlyMustHaveValue() {
        return User.get()
                .setFirstname("Testing")
                .setLastname("Testing")
                .setEMail("Testing@Testing.yep")
                .setTelephone("8800555")
                .setAddress1("AddressTest")
                .setCity("Kyiv")
                .setPostCode("8800")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("aezakmi")
                .setFax("")
                .setAddress2("")
                .setCompany("")
                .build();
    }

    public IUser equivalencePartitioning() {
        return User.get()
                .setFirstname("TeSt_1")
                .setLastname("RanDom")
                .setEMail("TeStIng|3@test.test")
                .setTelephone("TeSt{1")
                .setAddress1("TeS^1")
                .setCity("TeSt!!1")
                .setPostCode("te12stA*")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("aezakmi")
                .setAddress2("TeSt@1")
                .setCompany("TeSt12$")
                .setFax("TeSt(1")
                .build();
    }

    public IUser boundaryValueMin() {
        return User.get()
                .setFirstname("a")
                .setLastname("b")
                .setEMail("test@test.test")
                .setTelephone("123")
                .setAddress1("Twe")
                .setCity("ab")
                .setPostCode("56")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("1234")
                .setAddress2("c")
                .setCompany("t")
                .setFax("q")
                .build();
    }

    public IUser boundaryValueMax() {
        return User.get()
                .setFirstname("Lorem ipsum dolor sit amet, cons")
                .setLastname("Lorem ipsum dolor sit amet, cons")
                .setEMail("testing@test.test")
                .setTelephone("Lorem ipsum dolor sit amet, cons")
                .setAddress1("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setCity("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setPostCode("Lorem ipsu")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("Lorem ipsum dolor si")
                .setAddress2("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setCompany("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setFax("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .build();
    }

    public IUser boundaryValueOverMax() {
        return User.get()
                .setFirstname("Lorem ipsum dolor sit amet, cons123123")
                .setLastname("Lorem ipsum dolor sit amet, cons123123")
                .setEMail("testing@test.test")
                .setTelephone("Lorem ipsum dolor sit amet, cons123123")
                .setAddress1("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen123123")
                .setCity("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen123123")
                .setPostCode("Lorem ipsu123123")
                .setCountry("Ukraine")
                .setRegionState("Kyiv")
                .setPassword("Lorem ipsum dolor si123123")
                .setAddress2("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setCompany("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .setFax("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen")
                .build();
    }
}
