package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtility {

	public static PreparedStatement initializePreparedStatement(Connection connection, String sql,
			boolean returnGeneratedKey, Object... objects) throws SQLException {

		int generatedKeyFlag = (returnGeneratedKey) ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedKeyFlag);
		for(int i = 0; i < objects.length; i++) {
			preparedStatement.setObject(i +1, objects[i]);
		}
		return preparedStatement;
	}

	/* Best Practice to Release Resources from Server */
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {			
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Failed to close ResultSet: "+ e.getMessage());
			}
		}
		
	}

	public static void closePreparedStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			}
			catch (SQLException e) {
				System.out.println("Failed to close Statement: "+ e.getMessage());
			}
		}
	}

	public static void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				System.out.println("Failed to close Connection: "+ e.getMessage());
			}
		}
	}
	
	/**
	 * Close Statement and Connection object of database communication and release Server's resources.
	 * <p>Helpful to use with some query where result (ResultSet) is unnecessary.</p>
	 * @param statement Statement/ PreparedStatement
	 * @param connection Connection
	 */
	public static void closeStatementAndConnection(Statement statement, Connection connection) {
		closePreparedStatement(statement);
		closeConnection(connection);
	}
	
	/**
	 * Close all elements required in process of database communication and release Server's resources.
	 * @param resultSet ResultSet
	 * @param statement Statement/ PreparedStatement
	 * @param connection Connection
	 */
	public static void closeAllDbRelativeRessources(ResultSet resultSet, Statement statement, Connection connection) {
		closeResultSet(resultSet);
		closePreparedStatement(statement);
		closeConnection(connection);
	}
}
