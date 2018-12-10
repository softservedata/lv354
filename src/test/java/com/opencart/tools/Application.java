package com.opencart.tools;

import java.util.HashMap;
import com.opencart.data.application.ApplicationSourceRepository;
import com.opencart.data.application.IApplicationSource;
import com.opencart.pages.HomePage;
import com.opencart.pages.right.AccountLogoutPage;

public class Application {
	private static volatile Application instance;

	// Fields
	private IApplicationSource applicationSource;

	private HashMap<Long, BrowserWrapper> browsers;

	private Application(IApplicationSource applicationSource) {
		this.applicationSource = applicationSource;
		browsers = new HashMap<>();
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

}
