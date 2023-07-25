package com.wrxprts.ims.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.UserService;

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
	public List<User> findAllUsers()
	{
		List<User> users = userRepository.findByActive(true);
		return users;
	}
	
	@Override
	public boolean emailExists(String email)
	{
		return userRepository.findByEmail(email).isPresent();
	}
	
	@Override
	public boolean tcExists(String tc)
	{
		return userRepository.findByTc(tc).isPresent();
	}
	
	@Override
	public boolean userExists(String name, String surname, Date b_date, String province)
	{
		return userRepository.findByNameAndSurnameAndBirthDateAndProvince(name, surname, b_date, province)
				.isPresent();
	}
	
}
