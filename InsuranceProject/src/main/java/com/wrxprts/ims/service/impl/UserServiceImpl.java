package com.wrxprts.ims.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.Role;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.UserService;
import com.wrxprts.ims.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository)
	{
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@Override
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}
	
	@Override
	public User getUserById(Long id)
	{
		return userRepository.findById(id).get();
	}
	
	@Override
	public User updateUser(User user)
	{
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUserById(Long id)
	{
		userRepository.deleteById(id);
	}
	
	@Override
	public User save(UserRegistrationDto registrationDto)
	{
		User user = new User(registrationDto.getTc(), registrationDto.getPassword(),
				Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	
}
