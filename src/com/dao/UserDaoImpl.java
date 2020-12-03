package com.dao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dao.DAOUtility.*;

import com.model.User;

public class UserDaoImpl implements UserDao {

	private DAOFactory daoFactory;
	
	private static final String SQL_SELECT_BY_EMAIL = "SELECT id, name, profileType, email, secret_code FROM user WHERE email = ?";
	
	private static final String SQL_INSERT_USER = "INSERT INTO user (name, profileType, email, secret_code) VALUES(?, ?, ?, ?)";
	
	public UserDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet autoGenValues = null;		
		
		try {
			connection = this.daoFactory.getConnection();
			preparedStatement = initializePreparedStatement(connection, SQL_INSERT_USER, true, user.getName(), user.getProfileType(), user.getEmail(), user.getPassword());System.out.println("SQL: "+SQL_INSERT_USER);
			int respSqlState = preparedStatement.executeUpdate();
			
			//respSqlState will return 0 in case of INSERT query error else 1 for its success
			if(respSqlState == 0) {
				throw new DAOException("Failed to register new user. No new member added in database.");
			}
			
			//Keep the new user's ID in autoGenValues after executeUpdate()
			autoGenValues = preparedStatement.getGeneratedKeys();
			if(autoGenValues.next()) {
				user.setId(autoGenValues.getInt(1));
			}
			else {
				throw new DAOException("Failure of user creation in database, no AUTO-Generated-ID returned.");
			}
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		finally {
			closeAllDbRelativeRessources(autoGenValues, preparedStatement, connection);
		}
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
	
	/**
	 * Check <code>String</code> value with hashed code value.<br />
	 * @param plaincode
	 * @param hashedcode
	 * @return Returns <strong><code>true</code></strong> if
	 * <strong>plaincode</strong> equals 
	 * <strong>hashedcode</strong> else <strong><code>false</code></strong>.
	 */
	public boolean isPasswordMatchHashcode(String plaincode, String hashedcode) {
		boolean checkPwd = false;
		
	    String hex = makeHashcode(plaincode);
		
	    if(hashedcode.equals(hex))
	    	checkPwd = true;
		
		return checkPwd;
	}
	
	/**
	 * Create a hashcode from a plain code <code>String</code> value.<br />
	 * Making it secure with hash function by a digest <strong>SHA-256</strong> (256 bits).
	 * @param plaincode
	 * @return hashcode
	 */
	public String makeHashcode(String plaincode) {
		String hex = "";
		try {
    		MessageDigest md = MessageDigest.getInstance("SHA-256");

    	    md.update(plaincode.getBytes(StandardCharsets.UTF_8));
    	    byte[] digest = md.digest();

    	    hex = String.format("%064x", new BigInteger(1, digest));
    		
    	    System.out.println("hexint64: " + hex);    	    
		}
    	catch (Exception e) {
			e.printStackTrace();
			System.out.println("[======= See MSG CLEARly =======]\n");
			e.getMessage();
		}
		return hex;
	}
}
