package com.softserve.edu.opencart.test;

import com.softserve.edu.opencart.data.application.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.user.IUser;
import com.softserve.edu.opencart.data.user.UserRepository;
import com.softserve.edu.opencart.pages.right.AccountInformationPage;
import com.softserve.edu.opencart.pages.right.UnsuccessfulRegistrationPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends ApplicationTestRunner {

    @DataProvider
    public Object[][] validValues() {
        return new Object[][]{
                {UserRepository.get().validRegistration()},
                {UserRepository.get().equivalencePartitioning()},
                {UserRepository.get().onlyMustHaveValue()},
                {UserRepository.get().boundaryValueMax()},
                {UserRepository.get().boundaryValueMin()}
        };
    }

    @DataProvider
    public Object[][] validValue(){
        return new Object[][] {
                {UserRepository.get().validRegistration()}
        };
    }

    @DataProvider
    public Object[][] duplicate(){
        return new Object[][]{
                {UserRepository.get().duplicateEmail()}
        };
    }
    @AfterTest(alwaysRun = true)
    public void afterAllTests() {
        IUser admin = UserRepository.get().admin();
        Application.get(ApplicationSourceRepository.adminChromeWithoutUI())
                .loadAdmin()
                .gotoLoggedAdminPage(admin)
                .gotoCustomerPage()
                .deleteCustomers();
        Application.remove();
    }

    @Test(dataProvider = "validValues")
    public void checkRegistration(IUser user){
        //Step
        AccountInformationPage accountInformationPage = Application.get()
                .loadApplication()
                .gotoRegistration()
                .successRegistration(user)
                .gotoMyAccount()
                .gotoAccountInformation();
        //Check
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText()
                , user.getFirstname());
    }

    @Test(dataProvider = "validValue")
    public void withoutAcceptRegistration(IUser user){
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .withoutAccept(user);
        Assert.assertEquals(unsuccessful.getAlertMessageText()
                , "Warning: You must agree to the Privacy Policy!");
    }

    @Test(dataProvider = "duplicate")
    public void duplicate(IUser user){
        AccountInformationPage accountInformationPage = Application.get().loadApplication()
                .gotoRegistration()
                .successRegistration(user)
                .gotoMyAccount()
                .gotoAccountInformation();
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText()
                , user.getFirstname());
        Application.get().logout();
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .unsuccessfulRegistrationPage(user);
        Assert.assertEquals(unsuccessful.getAlertMessageText()
                , "Warning: E-Mail Address is already registered!");

    }
    //@Test
    public void voidInput(){
        IUser user = UserRepository.get().voidRegistration();
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .unsuccessfulRegistrationPage(user);
    }

    //@Test
    public void boundaryValueOverMaxTest(){
        IUser user = UserRepository.get().boundaryValueOverMax();
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .unsuccessfulRegistrationPage(user);
    }
}
