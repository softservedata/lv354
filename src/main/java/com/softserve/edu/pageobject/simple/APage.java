package com.softserve.edu.pageobject.simple;

public class APage {

	// Solution
	// 1. Use Constructor with parameter isLogged;
	// 2. Use static field;
	// 3. Use DTO state object in Container with test parameters.
	//
	//protected boolean isLogged; // Invalid Solution.
	protected static boolean isLogged;
	
	public APage() {
		System.out.println("\tConstructor APage()");
		//isLogged = false;
	}

	// Page Object

	public void clickAtomic_APage() {
		System.out.println("clickAtomic_APage()");
	}

	public void clearAtomic_APage() {
		System.out.println("clearAtomic_APage()");
	}

	public void typeAtomic_APage() {
		System.out.println("typeAtomic_APage()");
	}

	public void isLoggedAtomic_APage() {
		System.out.println("isLoggedAtomic_APage, current: isLogged = " + isLogged);
	}

	// Business Logic

	public BPage goto_BPage() {
		System.out.println("goto_BPage()");
		return new BPage();
	}

	public BPage login_BPage() {
		System.out.println("start login_BPage(), current: isLogged = " + isLogged);
		isLogged = true;
		System.out.println("done login_BPage(), current: isLogged = " + isLogged);
		return new BPage();
	}

}
