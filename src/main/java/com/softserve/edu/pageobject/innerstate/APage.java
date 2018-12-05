package com.softserve.edu.pageobject.innerstate;

public class APage {

	public class APageAtomic {
		
		public APageAtomic() {
			System.out.println("\t\tConstructor APageAtomic()");
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

	}
	
	public final APageAtomic aPageAtomic;
	
	public APage() {
		System.out.println("\tConstructor APage()");
		aPageAtomic = new APageAtomic();
	}

	// Business Logic

	public BPage goto_BPage() {
		System.out.println("goto_BPage()");
		return new BPage();
	}

}
