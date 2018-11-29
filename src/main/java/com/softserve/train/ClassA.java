package com.softserve.train;

public class ClassA extends ABase {
	private int num;

	public ClassA() {
		num = 0;
		System.out.println("Constructor ClassA()");
	}

	public ClassA(int num) {
		this();
		this.num = num;
		//this(); // Error
		System.out.println("Constructor ClassA(int num)");
	}
	
	static void m0() {
		System.out.println("ClassA: static void m0()");
	}
	
	void method0() {
		System.out.println("ClassA: static void method0()");
	}
	
	public void method1() {
		int k;
		//System.out.println("k=" + k);
		System.out.println("num = " + num);
		//m0();
		//super.m0();
		//ABase.m0();
	}

}
