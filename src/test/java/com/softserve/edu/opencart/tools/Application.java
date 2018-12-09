package com.softserve.edu.opencart.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

import com.softserve.edu.opencart.data.application.ApplicationSourceRepository;
import com.softserve.edu.opencart.data.application.IApplicationSource;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.admin.CustomersPage;
import com.softserve.edu.opencart.pages.admin.LoginAdminPage;
import com.softserve.edu.opencart.pages.right.AccountLogoutPage;

public class Application {

    public static final double PRICE_PRECISION = 0.001;
    private final String Decimal_Format_2 = ".##";
    //
    // Use Singleton, Repository
    private static volatile Application instance;
    //
    // Fields
    //
    private IApplicationSource applicationSource;
    //
    private HashMap<Long, BrowserWrapper> browsers;
    //

    private Application(IApplicationSource applicationSource) {
        this.applicationSource = applicationSource;
        browsers = new HashMap<Long, BrowserWrapper>();
    }

    public static Application get() {
        return get(null);
    }

    public static Application get(IApplicationSource applicationSource) {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    if (applicationSource == null) {
                        applicationSource = ApplicationSourceRepository.defaultParameters();
                    }
                    instance = new Application(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {
        if (instance != null) {
            for (Long key : instance.browsers.keySet()) {
                BrowserWrapper browserWrapper = instance.browsers.get(key);
                if (browserWrapper != null) {
                    browserWrapper.quit();
                    instance.browsers.put(key, null);
                }

            }
            instance = null;
        }
    }

    // getters

    public DecimalFormat getDecimalFormat2() {
        return new DecimalFormat(Decimal_Format_2);
    }

    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }



    public BrowserWrapper getBrowser() {
        BrowserWrapper browserWrapper = browsers.get(Thread.currentThread().getId());
        if (browserWrapper == null) {
            browserWrapper = new BrowserWrapper(applicationSource);
            browsers.put(Thread.currentThread().getId(), browserWrapper);
        }
        return browserWrapper;
        // return browser;
    }

    // Initialization

    public double roundTo2digits(double number) {
        return Double.parseDouble(roundTo2digitsToString(number));
    }

    public String roundTo2digitsToString(double number) {
        return getDecimalFormat2().format(number + PRICE_PRECISION);
    }

    // Functional Operation

    public void setLoginStatus(boolean loginStatus) {
        getBrowser().getTestStatus().setLogged(loginStatus);
    }

    public boolean getLoginStatus() {
        return getBrowser().getTestStatus().isLogged();
    }

    // Pages

    public HomePage loadApplication() {
        getBrowser().openUrl(getApplicationSource().getBaseUrl());
        return new HomePage(getBrowser().getDriver());
    }

    public AccountLogoutPage logout() {
        getBrowser().openUrl(applicationSource.getUserLogoutUrl());
        return new AccountLogoutPage(getBrowser().getDriver());
    }

    public CustomersPage loadAdmin(){
        getBrowser().openUrl(applicationSource.getUserLogoutUrl());
        return new CustomersPage(getBrowser().getDriver());
    }

}
