package com.wu.ecommerce.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;
import com.wu.ecommerce.exception.InvalidPriceException;
import com.wu.ecommerce.utils.DBUtils;
import com.wu.ecommerce.utils.IdCompator;

public class ProductRepositoryImpl implements ProductRepository {
	
	private ProductRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private List<Product> products = new ArrayList<Product>();
	private static int counter = -1;
	
	private static ProductRepository productRepository;
	
	public static ProductRepository getInstance() {
		
		if(productRepository == null) {
			//create object
			productRepository = new ProductRepositoryImpl();
		}
		return productRepository;
	}

	public Product addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		
		String insertQuery= "INSERT INTO Product(productId, productName, price, categoryName) VALUES (?,?,?,?)";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		
		preparedStatement.setString(1, product.getProductId());
		preparedStatement.setString(2, product.getProductName());
		preparedStatement.setFloat(3, product.getPrice());
		preparedStatement.setString(4, product.getCategoryName());
		
		int result = preparedStatement.executeUpdate();
		System.out.println("Data Added");
		if(result > 0) {
			return product;
		}
		else {
			System.out.println("Not added");
			return null;
		}
	}

	public Product getProductById(String id) throws IdNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM Product WHERE productId=?";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<Product> p1 = new ArrayList<Product>();
		
		while (resultSet.next()) {
			Product product = new Product();
			product.setProductId(resultSet.getString("productId"));
			product.setProductName(resultSet.getString("productName"));
			product.setPrice(resultSet.getFloat("price"));
			product.setCategoryName(resultSet.getString("categoryName"));
			p1.add(product);
		}
		
		return p1.
				stream().
				filter(e->e.getProductId().equals(id)).
				findFirst().
				orElseThrow(()-> new IdNotFoundException("Id not found"));
	}

	public List<Product> getProducts() throws DataNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		
		String query = "SELECT * FROM Product";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			Product product = new Product();
			product.setProductId(resultSet.getString("productId"));
			product.setProductName(resultSet.getString("productName"));
			product.setPrice(resultSet.getFloat("price"));
			product.setCategoryName(resultSet.getString("categoryName"));
			products.add(product);
			
		}
		return Optional.
				ofNullable(products.stream().collect(Collectors.toList())).
				filter(l-> !l.isEmpty()).
				orElseThrow(()-> new DataNotFoundException("Data is not present"));
	}

	public List<Product> getAllProductByCategory(String cat) throws DataNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM Product WHERE categoryName=?";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, cat);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<Product> p1 = new ArrayList<Product>();
		Product product = new Product();
		product.setProductId(resultSet.getString("productId"));
		product.setProductName(resultSet.getString("productName"));
		product.setPrice(resultSet.getFloat("price"));
		product.setCategoryName(resultSet.getString("categoryName"));
		p1.add(product);
		return Optional.ofNullable(p1).filter(l->!l.isEmpty()).orElseThrow(()-> new DataNotFoundException("Data is not present"));
	}

	public String removeProductByProductId(String id) throws IdNotFoundException, SQLException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
		
		String query = "DELETE FROM Product WHERE productId=?";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		int resultSet = preparedStatement.executeUpdate();
		if(resultSet > 0)
			return "Success";
		return "fail";
	}

	public Product updateProductByProductId(String id, Product product) throws SQLException, IdNotFoundException, InvalidIdException, InvalidPriceException {
		// TODO Auto-generated method stub
//		String str1 = removeProductByProductId(id);
//		if(str1=="Success")
//			product = addProduct(product);
//		return product;
		String insertQuery= "UPDATE Product SET productName=?, price=?, categoryName=? WHERE productId=?";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		
		preparedStatement.setString(1, product.getProductName());
		preparedStatement.setFloat(2, product.getPrice());
		preparedStatement.setString(3, product.getCategoryName());
		preparedStatement.setString(4, id);
		
		int result = preparedStatement.executeUpdate();
		System.out.println("Data Updated");
		if(result > 0) {
			return product;
		}
		else {
			System.out.println("Not Updated");
			return null;
		}
	}

}
