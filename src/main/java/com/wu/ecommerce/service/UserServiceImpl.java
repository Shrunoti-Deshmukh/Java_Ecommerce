package com.wu.ecommerce.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.wu.ecommerce.dto.User;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.repo.ProductRepository;
import com.wu.ecommerce.repo.ProductRepositoryImpl;
import com.wu.ecommerce.repo.UserRepository;
import com.wu.ecommerce.repo.UserRepositoryImpl;
import com.wu.ecommerce.utils.DBUtils;

public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository = UserRepositoryImpl.getInstance();
	
	private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static UserService UserService;
	
	public static UserService getInstance() {
		if(UserService == null) {
			UserService = new UserServiceImpl();
		}
		return UserService;
	}
	
	@Override
	public User addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userRepository.addUser(user);
	}

	@Override
	public User getUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.getUserById(id);
	}

	@Override
	public Optional<User[]> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User[]> getAllUserByName(String cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeUserByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserByUserId(String id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
