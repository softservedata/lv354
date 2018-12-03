package com.softserve.edu.one;

public class Appl {

	public static void main(String[] args) {
		Box box = new Box();
		box.set(10);
		//
		// Code
		box.set("10");
		//
		int k = (int) box.get(); // Runtime Error // Code Smell
		System.out.println("k = " + k);
	}
}
