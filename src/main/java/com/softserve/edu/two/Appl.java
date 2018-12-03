package com.softserve.edu.two;

public class Appl {

	public static void main(String[] args) {
		WrapperBox box = new WrapperBox();
		// box.set(10);
		//
		// Code
		box.set("10");
		//
		//int k = (int) box.get(); // Compile Error
		//System.out.println("k = " + k);
		System.out.println("k = " + box.get());
	}
}
