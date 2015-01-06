package com.unknow.cpt.product;

public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String s) {
		super(s);
	}
}
