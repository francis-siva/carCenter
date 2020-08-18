package com.dao;

import com.model.User;

public interface UserDao {

	void create(User user) throws DAOException;
	
	User find(String email) throws DAOException;
}
