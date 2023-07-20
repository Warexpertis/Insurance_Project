package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.User;

public interface UserService
{
	// Admin Page CRUD
	
	List<User> findAllUsers();
	
	User getUserById(Long id);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	// Spring Security
	
	User findUserByTc(String tc);
}
