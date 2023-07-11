package com.wrxprts.ims.service.impl;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
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
		User user = userRepository.findById(id).get();
		List<Car> cars = user.getCars();
		for (Car car : cars)
		{
			if (car.getUser() != null)
			{
				car.setUser(null);
				carRepository.save(car);
			}
		}
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
		car.setUser(user);
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
	
	@Override
	public String carOffer(Long carID)
	{
		Car car = carRepository.findById(carID).get();
		int age = car.getYear() - Calendar.getInstance().get(Calendar.YEAR);
		double coef;
		switch (car.getMotorType())
		{
			case "Gasoline":
				coef = 1;
				break;
			case "Diesel":
				coef = 1.1;
				break;
			case "Hybrid":
				coef = 1.2;
				break;
			case "Electric":
				coef = 1.2;
				break;
			default:
				coef = 1;
		}
		double offer = coef * (car.getCarPrice() * (0.009 + age * 0.0001 + car.getMileage() * 0.0000005));
		DecimalFormat decfor = new DecimalFormat("0.00");
		return decfor.format(offer);
	}
	
}
