package com.softserve.cartTests;

public enum Products {
	IPHONE("iPhone",123.20,1),
	CINEMA("Apple Cinema 30\"", 110.00,1),
	MACBOOK("MacBook",602.00,1),
	CANON("Canon EOS 5D",98.00,1);
	
	
	private String name;
	private double productPrice;
	private int amount;

	private Products(String name, double productPrice, int amount) {
		this.name = name;
		this.productPrice = productPrice;
		this.amount = amount;
	}


	public String getName() {
		return name;
	}

	public int getItem() {
		return amount;
	}
	public double getPrice() {
		return productPrice;
	}

}

