package com.wrxprts.ims.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.Role;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.CarRepository;
import com.wrxprts.ims.repository.RoleRepository;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.UserService;
import com.wrxprts.ims.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;
	private CarRepository carRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			CarRepository carRepository, PasswordEncoder passwordEncoder)
	{
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.carRepository = carRepository;
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
	public void deleteUserById(Long id)
	{
		userRepository.deleteById(id);
	}
	
	@Override
	public void registerUser(UserDto userDto)
	{
		User user = new User();
		user.setTc(userDto.getTc());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Role role = roleRepository.findByEmail("ROLE_USER");
		if (role == null)
		{
			role = checkRoleExist();
		}
		
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}
	
	@Override
	public User findUserByTc(String tc)
	{
		return userRepository.findByTc(tc);
	}
	
	@Override
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}
	
	private Role checkRoleExist()
	{
		Role role = new Role();
		role.setEmail("ROLE_USER");
		return roleRepository.save(role);
	}
	
	@Override
	public List<Car> getCarsByUserId(Long id)
	{
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
			return null;
		return user.getCars();
	}
	
	@Override
	public void addCar(Long id, Car car)
	{
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
		{
			return;
		}
		List<Car> cars = user.getCars();
		cars.add(car);
		userRepository.save(user);
	}
	
	@Override
	public void deleteCar(Long carID)
	{
		carRepository.deleteById(carID);
	}
	
	@Override
	public Car getCarById(Long carID)
	{
		return carRepository.findById(carID).get();
	}
	
	@Override
	public Car updateCar(Car car)
	{
		return carRepository.save(car);
	}
	
}
