package com.softserve.edu.pageobject.innerstate;

public class Appl {
	public static void main(String[] args) {
		APage a = new APage();
		CPage c = a.goto_BPage().goto_CPage();
		//c.getAPageAtomic().clearAtomic_APage(); // Running Atomic Operation
		c.cPageAtomic.clearAtomic_CPage(); // APage.class Atomic Operation not Visible
		System.out.println("done");
	}
}