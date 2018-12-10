package com.opencart.tests;
import com.opencart.tools.Application;
import com.opencart.tools.ApplicationTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.data.user.IUser;
import com.opencart.data.user.UserRepository;
import com.opencart.pages.HomePage;
import com.opencart.pages.right.AccountInformationPage;
import com.opencart.pages.right.AccountLogoutPage;

public class LoginTest extends ApplicationTestRunner {

	@DataProvider//(parallel = true)
    public Object[][] validUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get().customer() },
        	//{ "hahaha@gmail.com", "qwerty", "hahaha" },
            };
    }

    @Test(dataProvider = "validUsers")
    public void checkLogin(IUser validUser) {
    //public void checkLogin(String email, String password, String firstname) {
        //
        // Precondition
        // Steps
    	AccountInformationPage accountInformationPage = Application.get().loadApplication()
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
    }
}
