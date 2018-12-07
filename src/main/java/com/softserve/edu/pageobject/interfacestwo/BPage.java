package com.softserve.edu.pageobject.interfacestwo;

public class BPage extends APage implements IBPage {

	public BPage() {
		System.out.println("BPage()");
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

	public BPage getBPage() {
		return this;
	}

	public IBPage getIBPage() {
		return this;
	}
	
	public ICPage goto_CPage() {
		System.out.println("goto_CPage()");
		return new CPage();
	}

}
