package com.softserve.edu.pageobject.innerextends;

public class BPage extends APage {

	public class BPageAtomic extends APageAtomic {

		public BPageAtomic() {
			System.out.println("\t\tConstructor BPageAtomic()");
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

	}

	private BPageAtomic bPageAtomic;

	public BPage() {
		System.out.println("\tConstructor BPage()");
		bPageAtomic = new BPageAtomic();
	}

	// Business Logic

	public BPageAtomic getBPageAtomic() {
		return bPageAtomic;
	}

	public APage goto_APage() {
		System.out.println("goto_APage()");
		return new APage();
	}

	public CPage goto_CPage() {
		System.out.println("goto_CPage()");
		return new CPage();
	}

}
