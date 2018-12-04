package com.softserve.edu.pageobject.simple;

public class Appl {
	public static void main(String[] args) {
		//
		//APage a = new APage();
		//CPage c = a.goto_BPage().goto_CPage();
		//c.clearAtomic_CPage();
		//
		// a= c.goto_APage(); // Business Logic only
		// a= ((CPage)c).goto_APage(); // Explicit, Bad Solution
		//
		APage a = new APage();
		BPage b = a.login_BPage();
		CPage c = b.goto_CPage();
		c.isLoggedAtomic_APage();
		a = c.logout_APage();
		a.isLoggedAtomic_APage();
	}
}
