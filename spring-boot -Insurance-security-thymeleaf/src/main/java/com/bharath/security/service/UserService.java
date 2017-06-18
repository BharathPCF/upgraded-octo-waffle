package com.bharath.security.service;

import com.bharath.model.security.User;

public interface UserService {
	public void saveUser(User user);
	public User findUserByEmail(String email);
	
}
