package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.Car;

public interface CarService
{
	List<Car> getCarsById(Long id);
	
	Car getCarById(Long carID);
	
	Car saveCar(Car car);
	
	Car editCar(Car car);
	
	String carOffer(Long carID);
	
	boolean carExists(String model, Short year, int mileage);
}
