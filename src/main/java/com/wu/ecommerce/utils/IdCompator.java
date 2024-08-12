package com.wu.ecommerce.utils;

import java.util.Comparator;

import com.wu.ecommerce.dto.Product;

public class IdCompator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return o1.getProductId().compareTo(o2.getProductId());
	}

}
