package com.softserve.edu.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.user.IUser;
import com.softserve.edu.opencart.data.user.UserRepository;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.right.AccountInformationPage;
import com.softserve.edu.opencart.pages.right.AccountLogoutPage;
import com.softserve.edu.opencart.tools.TestRunner;

public class LoginTest extends TestRunner {

	@DataProvider//(parallel = true)
    public Object[][] validUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get().customerHahaha() },
        	//{ "hahaha@gmail.com", "qwerty", "hahaha" },
            };
    }

    @Test(dataProvider = "validUsers")
    public void checkLogin(IUser validUser) {
    //public void checkLogin(String email, String password, String firstname) {
        //
        // Precondition
        // Steps
    	AccountInformationPage accountInformationPage = loadApplication()
        		.gotoLogin()
        		.successLogin(validUser)
        		//.successLogin(email, password)
        		.gotoAccountInformation();
        delayExecution(1);
        //
        // Check
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText(),
        		//firstname);
        		validUser.getFirstname());
        delayExecution(1);
        //
        // Return to previous state
        // Steps
        AccountLogoutPage accountLogoutPage = accountInformationPage.gotoLogout();
        //
        // Check
        Assert.assertEquals(accountLogoutPage.getAccountLogoutLabelText(),
        		accountLogoutPage.EXPECTED_TEXT_LOGOUT);
        delayExecution(1);
        //
        // Return to previous state
        HomePage homePage = accountLogoutPage.chooseContinue();
        delayExecution(2);
        // TODO homePage
    }

//    @Test(dataProvider = "validUsers")
//    public void checkLoginLogout(IUser validUser) {
//        // Precondition
//        // Steps
//    	AccountInformationPage accountInformationPage = loadApplication()
//        		.gotoLogin()
//        		.successLogin(validUser)
//        		.gotoAccountInformation();
//        delayExecution(1000);
//        //
//        // Check
//        Assert.assertEquals(accountInformationPage.getFirstnameFieldText(),
//        		validUser.getFirstName());
//        delayExecution(1000);
//        //
//        accountInformationPage.clickWishList();
//        delayExecution(2000);
//        // Return to previous state
//    }
    
}
