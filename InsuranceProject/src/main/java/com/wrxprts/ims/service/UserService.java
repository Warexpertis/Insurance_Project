package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.web.dto.UserRegistrationDto;

public interface UserService
{
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User getUserById(Long id);
	
	User updateUser(User user);
	
	void deleteUserById(Long id);
	
	//
	
	User save(UserRegistrationDto registrationDto);
}
