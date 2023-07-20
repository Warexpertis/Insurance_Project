package com.wrxprts.ims.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
	public User findUserByTc(String tc)
	{
		return userRepository.findByTc(tc);
	}
	
	@Override
	public List<User> findAllUsers()
	{
		List<User> users = userRepository.findByActive(true);
		return users;
	}
	
}
