package com.wrxprts.ims.service.impl;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.repository.CarRepository;
import com.wrxprts.ims.service.CarService;

@Service
public class CarServiceImpl implements CarService
{
	// private UserRepository userRepository;
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository)
	{
		super();
		this.carRepository = carRepository;
	}
	
	@Override
	public Car saveCar(Long id, Car car)
	{
		return carRepository.save(car);
	}
	
}
