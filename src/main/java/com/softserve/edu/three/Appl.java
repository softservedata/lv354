package com.softserve.edu.three;

public class Appl {

	public static void main(String[] args) {
		Box<String> box = new Box<>();
		// box.set(10);
		//
		box.set("101");
		// Code
		//
		//int k = (int) box.get(); // Compile Error
		// System.out.println("k = " + k);
		System.out.println("k = " + box.get());
	}
}
