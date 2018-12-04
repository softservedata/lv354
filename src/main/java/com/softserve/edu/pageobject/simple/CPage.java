package com.softserve.edu.pageobject.simple;

public class CPage extends BPage {

	public CPage() {
		System.out.println("\tConstructor CPage()");
	}

	// Page Object
	
	public void clickAtomic_CPage() {
		System.out.println("clickAtomic_CPage()");
	}

	public void clearAtomic_CPage() {
		System.out.println("clearAtomic_CPage()");
	}

	public void typeAtomic_CPage() {
		System.out.println("typeAtomic_CPage()");
	}

	// Business Logic

	public APage goto_APage() {
		System.out.println("goto_APage()");
		return new APage();
	}

	public BPage goto_BPage() {
		System.out.println("goto_BPage()");
		return new BPage();
	}

}
