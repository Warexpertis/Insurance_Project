package com.wrxprts.ims.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.House;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.CarRepository;
import com.wrxprts.ims.repository.UserRepository;

@SpringBootTest
public class CarServiceTest 
{
		@Autowired
		private UserRepository userRepository;
	
		@Autowired
		private CarRepository carRepository;
	
		@Autowired
		private CarService carService;
	
		
		@Test
		void testAddCar() 
		{
			// Create a new user
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -20); // set the birth date to 20 years ago
			Date birthDate = calendar.getTime();
		    User user = new User("John", "Doe", birthDate, "Istanbul", "12345678901", "john.doe@example.com",
		            "password", true, new ArrayList<Car>(), new ArrayList<House>());
		    userRepository.save(user);
			
			
			// Create a new car
		    Car car = new Car("Brand", "Model", "MotorType", (short) 2020, 10000, 20000, false, 0, null, true, user);
		    carRepository.save(car);

		    // Verify that the car was added
		    Car result = carService.getCarById(car.getId());
		    assertNotNull(result);
		    assertEquals(car, result);
		}
		
		@Test
		void testEditCar() {
		    // Create a new user
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -25); // set the birth date to 25 years ago
			Date birthDate = calendar.getTime();
		    User user = new User("John", "Doe", birthDate, "Istanbul", "12345678901", "john.doe@example.com",
		            "password", true, new ArrayList<Car>(), new ArrayList<House>());
		    userRepository.save(user);

		    // Create a new car
		    Car car = new Car("Brand", "Model", "MotorType", (short) 2020, 10000, 20000, false, 0, null, true, user);
		    carRepository.save(car);

		    // Edit the car's information
		    car.setBrand("NewBrand");
		    car.setModel("NewModel");
		    carRepository.save(car);

		    // Verify that the car's information was updated
		    Car result = carService.getCarById(car.getId());
		    assertNotNull(result);
		    assertEquals("NewBrand", result.getBrand());
		    assertEquals("NewModel", result.getModel());
		}
}
