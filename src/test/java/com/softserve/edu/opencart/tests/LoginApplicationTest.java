package com.softserve.edu.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.user.IUser;
import com.softserve.edu.opencart.data.user.UserRepository;
import com.softserve.edu.opencart.db.service.UserService;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.right.AccountInformationPage;
import com.softserve.edu.opencart.pages.right.AccountLogoutPage;
import com.softserve.edu.opencart.pages.right.UnsuccessfulLoginPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import com.softserve.edu.opencart.tools.ListUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners({ TestListener.class })
@Epic("Allure_Test EPIC")
@Feature("Login_Application_Test FEATURE")
public class LoginApplicationTest extends ApplicationTestRunner {

	@DataProvider(parallel = true)
    public Object[][] validUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get().customerHahaha() },
            //{ UserRepository.get().customerHahaha() },
        	//{ "hahaha@gmail.com", "qwerty", "hahaha" },
            };
    }

	@DataProvider//(parallel = true)
    public Object[][] validCSVUsers() {
        return ListUtils.toMultiArray(UserRepository.fromCsv());
    }

	@DataProvider//(parallel = true)
    public Object[][] validExcelUsers() {
        return ListUtils.toMultiArray(UserRepository.fromExcel());
    }

	@Description("Test Description: class LoginApplicationTest; checkLogin().")
	@Severity(SeverityLevel.NORMAL)
	@Story("check_Product_Currency STORY")
    //@Test(dataProvider = "validUsers")
	//@Test(dataProvider = "validCSVUsers")
	@Test(dataProvider = "validExcelUsers")
    public void checkLogin(IUser validUser) {
    //public void checkLogin(String email, String password, String firstname) {
    	log.info("checkLogin() start");
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
        // TODO homePage
        log.info("checkLogin() done");
    }

	@DataProvider(parallel = true)
    public Object[][] invalidUsers() {
        // Read from ...
        return new Object[][] { 
            { UserRepository.get().invalidUser() },
            };
    }

    //@Test(dataProvider = "invalidUsers")
    public void checkLockedUser(IUser invalidUser) {
        // Precondition
        // Steps
    	UnsuccessfulLoginPage unsuccessfulLoginPage = Application.get().loadApplication()
        		.gotoLogin()
        		.unsuccessfullLogin(invalidUser);
        delayExecution(1);
        //
        for (int i = 0; i < 5; i++) {
            // Check UI
        	System.out.println("\tCheckUI as LOGIN, i= " + i + "  Message: " + unsuccessfulLoginPage.getAlertMessageText());
            Assert.assertTrue(unsuccessfulLoginPage.getAlertMessageText()
            		.contains(UnsuccessfulLoginPage.EXPECTED_WARNING_LOGIN));
            delayExecution(1);
            // Steps
        	unsuccessfulLoginPage = unsuccessfulLoginPage
        			.unsuccessfullLogin(invalidUser); 	
            delayExecution(1);
        }
        //
        // Check UI
        System.out.println("\tCheckUI as LOCK");
        Assert.assertTrue(unsuccessfulLoginPage.getAlertMessageText()
        		.contains(UnsuccessfulLoginPage.EXPECTED_WARNING_LOCK));
        delayExecution(1);
        //
        // Check DB
        UserService userService = new UserService(); 
        System.out.println("\tCheckDB as LOCK");
        Assert.assertTrue(userService.isLockedUser(invalidUser));
        delayExecution(1);
        //
        // Steps
        System.out.println("\tunlockUser()");
        userService.unlockUser(invalidUser);
        unsuccessfulLoginPage = unsuccessfulLoginPage
    			.unsuccessfullLogin(invalidUser);
        delayExecution(1);
        //
        // Check UI
        System.out.println("\tCheckUI as UNLOCK");
        Assert.assertTrue(unsuccessfulLoginPage.getAlertMessageText()
        		.contains(UnsuccessfulLoginPage.EXPECTED_WARNING_LOGIN));
        // Check DB
        System.out.println("\tCheckDB as UNLOCK");
        Assert.assertFalse(userService.isLockedUser(invalidUser));
        delayExecution(1);
        //
        // Return to previous state
        userService.unlockUser(invalidUser);
        log.info("checkLockedUser() done");
    }
    
}
