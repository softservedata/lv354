package com.softserve.edu.opencart.test;

import com.softserve.edu.opencart.data.application.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.user.IUser;
import com.softserve.edu.opencart.data.user.UserRepository;
import com.softserve.edu.opencart.pages.admin.CustomersPage;
import com.softserve.edu.opencart.pages.right.AccountInformationPage;
import com.softserve.edu.opencart.pages.right.UnsuccessfulRegistrationPage;
import com.softserve.edu.opencart.tools.Application;
import com.softserve.edu.opencart.tools.ApplicationTestRunner;
import org.testng.Assert;
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


    //@AfterTest
    public void afterTest() throws InterruptedException {
        IUser admin = UserRepository.get().admin();
        CustomersPage loggedAdminPage = Application.get(ApplicationSourceRepository.adminLocalChrome())
                .loadAdmin()
                .deleteCustomers(admin);
    }
    @Test(dataProvider = "validValues")
    public void checkRegistration(IUser user) {
        //Step
        AccountInformationPage accountInformationPage = Application.get().loadApplication()
                .gotoRegistration()
                .successRegistration(user)
                .gotoMyAccount()
                .gotoAccountInformation();
        //Check
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText()
                ,user.getFirstname());
    }
    @Test
    public void withoutAcceptRegistration(){
        IUser user = UserRepository.get().validRegistration();
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .withoutAccept(user);
        Assert.assertEquals(unsuccessful.getAlertMessage()
                ,unsuccessful.getAlertMessage());
    }

    @Test
    public void createDuplicate() {
        IUser user = UserRepository.get().duplicateEmail();
        AccountInformationPage accountInformationPage = Application.get().loadApplication()
                .gotoRegistration()
                .successRegistration(user)
                .gotoMyAccount()
                .gotoAccountInformation();
        Assert.assertEquals(accountInformationPage.getFirstnameFieldText()
                ,user.getFirstname());
    }
    @Test
    public void duplicateEmail(){
        IUser user = UserRepository.get().duplicateEmail();
        UnsuccessfulRegistrationPage unsuccessful = Application.get().loadApplication()
                .gotoRegistration()
                .unsuccessfulRegistrationPage(user);
        Assert.assertEquals(unsuccessful.getAlertMessage()
                ,unsuccessful.getAlertMessage());
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
