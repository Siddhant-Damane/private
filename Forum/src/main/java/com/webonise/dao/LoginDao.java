package com.webonise.dao;

import com.webonise.custom.exceptions.ConstraintException;
import com.webonise.custom.exceptions.DataLimitException;
import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Users;

public interface LoginDao {

	Users findByUserName(String username)throws ForumException;

	void saveuser(Users user)throws ForumException, ConstraintException, DataLimitException;
	
}
