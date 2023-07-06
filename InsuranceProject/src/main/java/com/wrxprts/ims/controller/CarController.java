package com.wrxprts.ims.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.UserService;

@Controller
public class CarController
{
	private UserService userService;
	
	public CarController(UserService userService)
	{
		super();
		this.userService = userService;
	}
	
	// Handler method to handle adding a new car to current user
	@GetMapping("/users/cars/{id}/new")
	public String addCarForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("car", new Car());
		return "addCar";
	}
	
	@PostMapping("/users/cars/{id}/new")
	public String saveCar(@PathVariable Long id, @ModelAttribute("car") Car car)
	{
		car.setId(null);
		userService.addCar(id, car);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/edit/{carID}")
	public String editCarForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		model.addAttribute("car", userService.getCarById(carID));
		return "editCar";
	}
	
	@PostMapping("/users/cars/{id}/edit/{carID}")
	public String editCar(@PathVariable Long id, @PathVariable Long carID, @ModelAttribute("car") Car car,
			Model model)
	{
		Car existingCar = userService.getCarById(carID);
		existingCar.setBrand(car.getBrand());
		existingCar.setModel(car.getModel());
		existingCar.setMotorType(car.getMotorType());
		existingCar.setYear(car.getYear());
		existingCar.setMileage(car.getMileage());
		
		userService.updateCar(existingCar);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/{carIdx}+{carID}")
	public String deleteCar(@PathVariable("id") Long id, @PathVariable("carIdx") int carIdx,
			@PathVariable("carID") Long carID)
	{
		User user = userService.getUserById(id);
		List<Car> cars = user.getCars();
		cars.remove(carIdx);
		userService.deleteCar(carID);
		// userService.saveUser(user);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/{carID}/traffic_insurance")
	public String trafficInsuranceForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		Car car = userService.getCarById(carID);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		double offer = year - car.getYear() + Integer.parseInt(car.getMileage()) * 0.15;
		model.addAttribute("value", offer);
		return "traffic_insurance";
	}
}
