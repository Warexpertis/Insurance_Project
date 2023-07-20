package com.wrxprts.ims.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.CarRepository;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.CarService;

@Service
public class CarServiceImpl implements CarService
{
	// private UserRepository userRepository;
	private UserRepository userRepository;
	private CarRepository carRepository;
	
	public CarServiceImpl(UserRepository userRepository, CarRepository carRepository)
	{
		super();
		this.userRepository = userRepository;
		this.carRepository = carRepository;
	}
	
	@Override
	public List<Car> getCarsById(Long id)
	{
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
			return null;
		return user.getCars();
	}
	
	@Override
	public Car getCarById(Long carID)
	{
		return carRepository.findById(carID).get();
	}
	
	@Override
	public Car saveCar(Car car)
	{
		return carRepository.save(car);
	}
	
	@Override
	public Car editCar(Car car)
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
