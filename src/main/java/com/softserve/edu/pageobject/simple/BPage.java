package com.softserve.edu.pageobject.simple;

public class BPage extends APage {

	public BPage() {
		System.out.println("\tConstructor BPage()");
	}

	// Page Object

	public void clickAtomic_BPage() {
		System.out.println("clickAtomic_BPage()");
	}

	public void clearAtomic_BPage() {
		System.out.println("clearAtomic_BPage()");
	}

	public void typeAtomic_BPage() {
		System.out.println("typeAtomic_BPage()");
	}

	// Business Logic

	public APage goto_APage() {
		System.out.println("goto_APage()");
		return new APage();
	}

	public CPage goto_CPage() {
		System.out.println("goto_CPage()");
		return new CPage();
	}

	public APage logout_APage() {
		System.out.println("start logout_APage(), current: isLogged = " + isLogged);
		isLogged = false;
		System.out.println("done logout_APage(), current: isLogged = " + isLogged);
		return new APage();
	}

}
