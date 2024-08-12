package com.wu.ecommerce.exception;

public class InvalidPriceException extends Exception {
	public InvalidPriceException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
