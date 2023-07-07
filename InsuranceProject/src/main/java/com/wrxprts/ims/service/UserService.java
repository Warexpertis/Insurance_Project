package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.web.dto.UserDto;

public interface UserService
{
	// Admin Page CRUD
	
	List<User> findAllUsers();
	
	User saveUser(User user);
	
	void addCar(Long id, Car asset);
	
	Car updateCar(Car car);
	
	void deleteCar(Long carID);
	
	User getUserById(Long id);
	
	Car getCarById(Long carID);
	
	List<Car> getCarsByUserId(Long id);
	
	String carOffer(Long carID);
	
	User updateUser(User user);
	
	void deleteUserById(Long id);
	
	// Spring Security
	
	void registerUser(UserDto userDto);
	
	User findUserByTc(String tc);
}
