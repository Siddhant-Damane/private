package com.webonise.dao;

import com.webonise.models.Users;

public interface LoginDao {

	Users findByUserName(String username);

	void saveuser(Users user);
	
}
