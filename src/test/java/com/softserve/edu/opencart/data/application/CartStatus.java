package com.softserve.edu.opencart.data.application;

public class CartStatus {

	private boolean isEmpty;

	public CartStatus() {
		isEmpty = true;
	}
	
	public synchronized boolean isEmpty() {
		return isEmpty;
	}

	public synchronized void setCartStatusIsEmpty (boolean isEmpty) {
			this.isEmpty = isEmpty;	
		}		
	}	

