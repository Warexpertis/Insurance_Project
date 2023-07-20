package com.wrxprts.ims.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrxprts.ims.cmbbx.CarList;
import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.CarService;
import com.wrxprts.ims.service.UserService;

import jakarta.validation.Valid;

@Controller
public class CarController
{
	private UserService userService;
	private CarService carService;
	
	public CarController(UserService userService, CarService carService)
	{
		super();
		this.userService = userService;
		this.carService = carService;
	}
	
	// Handler method to handle list the cars of current user
	@GetMapping("/users/cars/{id}")
	public String listCustCarsForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		List<Car> cars = carService.getCarsById(id);
		model.addAttribute("user", user);
		model.addAttribute("cars", cars);
		return "user_cars";
	}
	
	// Handler method to handle adding a new car to current user
	@GetMapping("/users/cars/{id}/new")
	public String addCarForm(@PathVariable Long id, Model model)
			throws StreamReadException, DatabindException, IOException
	{
		User user = userService.getUserById(id);
		Car car = new Car();
		car.setUser(user);
		Resource resource = new ClassPathResource("car-list.json");
		ObjectMapper mapper = new ObjectMapper();
		List<CarList> carList = mapper.readValue(resource.getInputStream(), new TypeReference<List<CarList>>()
		{
		});
		
		// Add carLiist to model
		model.addAttribute("carList", carList);
		model.addAttribute("user", user);
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@PostMapping("/users/cars/{id}/new")
	public String saveCar(@PathVariable Long id, @Valid @ModelAttribute("car") Car car, BindingResult bindingResult,
			@ModelAttribute("user") User user)
	{
		if (bindingResult.hasErrors())
			return "addCar";
		car.setUser(user);
		List<Car> cars = user.getCars();
		cars.add(car);
		carService.saveCar(car);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/edit/{carID}")
	public String editCarForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		User user = userService.getUserById(id);
		Car car = carService.getCarById(carID);
		// car.setUser(user);
		model.addAttribute("car", car);
		model.addAttribute("user", user);
		return "editCar";
	}
	
	@PostMapping("/users/cars/{id}/edit/{carID}")
	public String editCar(@PathVariable Long id, @PathVariable Long carID, @Valid @ModelAttribute("car") Car car,
			BindingResult bindingResult, Model model)
	{
		User user = userService.getUserById(id);
		Car existingCar = carService.getCarById(carID);
		if (bindingResult.hasErrors())
		{
			car.setId(carID);
			car.setUser(user);
			model.addAttribute("user", user);
			return "editCar";
		}
		existingCar.setBrand(car.getBrand());
		existingCar.setModel(car.getModel());
		existingCar.setMotorType(car.getMotorType());
		existingCar.setYear(car.getYear());
		existingCar.setMileage(car.getMileage());
		
		carService.editCar(existingCar);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/{carIdx}+{carID}")
	public String deleteCar(@PathVariable("id") Long id, @PathVariable("carIdx") int carIdx,
			@PathVariable("carID") Long carID)
	{
		User user = userService.getUserById(id);
		List<Car> cars = user.getCars();
		cars.remove(carIdx);
		return "redirect:/users/cars/" + id;
	}
	
	@GetMapping("/users/cars/{id}/{carID}/traffic_insurance")
	public String trafficInsuranceForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("value", carService.carOffer(carID));
		return "traffic_insurance";
	}
}
