package com.softserve.edu.three;

public class Box<T> {
	private T data;

	public T get() {
		return data;
	}

	public void set(T data) {
		this.data = data;
	}

}