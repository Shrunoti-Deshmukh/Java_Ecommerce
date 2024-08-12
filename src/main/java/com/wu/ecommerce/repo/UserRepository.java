package com.wu.ecommerce.repo;

import java.sql.SQLException;
import java.util.Optional;

import com.wu.ecommerce.dto.User;
import com.wu.ecommerce.exception.IdNotFoundException;

public interface UserRepository {
	public User addUser(User user) throws SQLException;
	public User getUserById(String id) throws IdNotFoundException;
	
	public Optional<User[]> getUsers();
	public Optional<User[]> getAllUserByName(String cat);
	
	public String removeUserByUserId(String id);
}
