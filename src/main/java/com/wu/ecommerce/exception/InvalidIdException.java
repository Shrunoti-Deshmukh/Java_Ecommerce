package com.wu.ecommerce.exception;

public class InvalidIdException extends Exception {
	public InvalidIdException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
