package com.wu.ecommerce.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;
import com.wu.ecommerce.exception.InvalidPriceException;
import com.wu.ecommerce.repo.ProductRepository;
import com.wu.ecommerce.repo.ProductRepositoryImpl;
import com.wu.ecommerce.utils.DBUtils;

public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository = ProductRepositoryImpl.getInstance();
	
	private ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static ProductService productService;
	
	public static ProductService getInstance() {
		
		if(productService == null) {
			productService = new ProductServiceImpl();
		}
		return productService;
	}

	public Product addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return productRepository.addProduct(product);
	}

	public Product getProductById(String id) throws IdNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		return productRepository.getProductById(id);
	}

	public List<Product> getProducts() throws DataNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		return productRepository.getProducts();
	}

	public List<Product> getAllProductByCategory(String cat) throws DataNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		return productRepository.getAllProductByCategory(cat);
	}

	public String removeProductByProductId(String id) throws IdNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		return productRepository.removeProductByProductId(id);
	}

	public Product updateProductByProductId(String id, Product product) throws SQLException, IdNotFoundException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		return productRepository.updateProductByProductId(id, product);
	}

}
