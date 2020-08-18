package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dao.DAOUtility.*;

import com.model.User;

public class UserDaoImpl implements UserDao {

	private DAOFactory daoFactory;
	
	private static final String SQL_SELECT_BY_EMAIL = "SELECT id, name, profileType, email, secret_code FROM user WHERE email = ?";
	
	public UserDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		

	}

	@Override
	public User find(String email) throws DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			connection = this.daoFactory.getConnection();
			
			preparedStatement = initializePreparedStatement(connection, SQL_SELECT_BY_EMAIL, false, email);
			//System.out.println("parcours GenKey: " + preparedStatement.getGeneratedKeys());
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				user = map(resultSet);
			}
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
		finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);			
		}
		
		return user;
	}

	private static User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setProfileType(resultSet.getString("profileType"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("secret_code"));
		
		return user;
	}
}
