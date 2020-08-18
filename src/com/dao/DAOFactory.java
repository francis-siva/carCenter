package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

	// Database Connection parameters from external Configuration file (dao.properties)
	private static final String FILE_PROPERTIES = "/com/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER = "user";
	private static final String PROPERTY_PASSWORD = "password";
	// Database additional Settings parameters
	private static final String PROPERTY_ZERO_DT_BEHAVIOR = "zeroDateTimeBehavior";
	private static final String PROPERTY_SERVER_TIMEZONE = "serverTimezone";
	
	// FILE_PROPERTIES parameters' value in the following private fields
	private String url;
	private String user;
	private String password;
	
	
	DAOFactory(String url, String user, String password) {
		this.url      = url;
		this.user     = user;
		this.password = password;
	}
	
	/**
	 * Gives a DAOFactory Instance which loads necessary properties to particular Database communication.
	 * @return
	 * @throws DAOConfigurationException
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {

		Properties properties = new Properties();		
		String url;
		String driver;
		String user;
		String password;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream	fileProperties = classLoader.getResourceAsStream(FILE_PROPERTIES);
		
		if(fileProperties == null) {
			throw new DAOConfigurationException("The File " + FILE_PROPERTIES + " is not found !");
		}
		
		// Connection Setup
		try {
			properties.load(fileProperties);
			
			url  = properties.getProperty(PROPERTY_URL) + "?";
			url += PROPERTY_ZERO_DT_BEHAVIOR + "=" + properties.getProperty(PROPERTY_ZERO_DT_BEHAVIOR) + "&";
			url += PROPERTY_SERVER_TIMEZONE + "=" + properties.getProperty(PROPERTY_SERVER_TIMEZONE);
			
			driver   = properties.getProperty(PROPERTY_DRIVER);
			user     = properties.getProperty(PROPERTY_USER);
			password = properties.getProperty(PROPERTY_PASSWORD);
		}
		catch (Exception e) {
			throw new DAOConfigurationException(FILE_PROPERTIES + " properties  file is not loading !", e);
		}
		
		// Loading JDBC Driver for the establishment of connection
		try {
			Class.forName(driver);
		}
		catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("The Driver is not found in the classpath !", e);
		}
		
		DAOFactory instance = new DAOFactory(url, user, password);
		
		return instance;
	}
	
	/**
	 * Returns the Connection to do processes on Database only once DAOFactory instance retrieved.
	 * @return Connection
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public UserDao getUserDAO() {
		return new UserDaoImpl(this);
	}
}
