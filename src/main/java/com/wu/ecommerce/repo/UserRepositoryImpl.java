package com.wu.ecommerce.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.wu.ecommerce.dto.User;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.utils.DBUtils;

public class UserRepositoryImpl implements UserRepository {
	
	private UserRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static UserRepository userRepository;
	
	public static UserRepository getInstance() {
		
		if (userRepository == null) {
			userRepository = new UserRepositoryImpl();
		}
		return userRepository;
		
	}
	
	private ArrayList<User> users = new ArrayList<User>();

	@Override
	public User addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		users.add(user);
		System.out.println("Data Added");
		
		String insertQuery= "INSERT INTO user_table(userid, firstname, lastname, contact) VALUES (?,?,?,?)";
		
		Connection connection = DBUtils.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		
		preparedStatement.setString(1, user.getUserid());
		preparedStatement.setString(2, user.getFirstname());
		preparedStatement.setString(3, user.getLastname());
		preparedStatement.setString(4, user.getContact());
		
		int result = preparedStatement.executeUpdate();
		if(result > 0) {
			return user;
		}
		else {
			System.out.println("Not added");
			return null;
		}
	}

	@Override
	public User getUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return users.stream().filter(u->u.getUserid().
				equals(id)).
				findFirst().
				orElseThrow(()-> new IdNotFoundException("Id not found"));
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
}
