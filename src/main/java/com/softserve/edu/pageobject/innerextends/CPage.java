package com.softserve.edu.pageobject.innerextends;

public class CPage extends BPage {

	public class CPageAtomic extends BPageAtomic {

		public CPageAtomic() {
			System.out.println("\t\tConstructor CPageAtomic()");
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

	}

	private CPageAtomic cPageAtomic;

	public CPage() {
		System.out.println("\tConstructor CPage()");
		cPageAtomic = new CPageAtomic();
	}

	// Business Logic

	public CPageAtomic getCPageAtomic() {
		return cPageAtomic;
	}

}
