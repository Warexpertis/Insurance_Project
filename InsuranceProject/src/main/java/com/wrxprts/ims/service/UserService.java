package com.wrxprts.ims.service;

import java.util.Date;
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
	
	boolean tcExists(String tc);
	
	boolean emailExists(String email);
	
	boolean userExists(String name, String surname, Date b_date, String province);
}
