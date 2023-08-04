package com.wrxprts.ims.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
	@GetMapping("/users/{id}/cars")
	public String listCustCarsForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		List<Car> cars = user.getCars();
		model.addAttribute("user", user);
		model.addAttribute("cars", cars);
		return "user_cars";
	}
	
	// Handler method to handle adding a new car to current user
	@GetMapping("/users/{id}/cars/new")
	public String addCarForm(@PathVariable Long id, Model model)
			throws StreamReadException, DatabindException, IOException
	{
		User user = userService.getUserById(id);
		Car car = new Car();
		//car.setUser(user);
		Resource resource = new ClassPathResource("car-list.json");
		ObjectMapper mapper = new ObjectMapper();
		List<CarList> carList = mapper.readValue(resource.getInputStream(), new TypeReference<List<CarList>>()
		{
		});
		
		// Add carList to model
		model.addAttribute("carList", carList);
		model.addAttribute("user", user);
		model.addAttribute("car", car);
		
		return "addCar";
	}
	
	@PostMapping("/users/{id}/cars/new")
	public String saveCar(@PathVariable Long id, @Valid @ModelAttribute("car") Car car, BindingResult bindingResult,
			@ModelAttribute("user") User user, Model model) throws IOException
	{
		car.setId(null);
		boolean carExists = carService.carExists(car.getModel(), car.getYear(), car.getMileage());
		if (carExists)
			bindingResult.reject("error.car", "This car has already been added");
		if (bindingResult.hasErrors())
		{
			Resource resource = new ClassPathResource("car-list.json");
			ObjectMapper mapper = new ObjectMapper();
			List<CarList> carList = mapper.readValue(resource.getInputStream(),
					new TypeReference<List<CarList>>()
					{
					});
			// Add carList to model
			model.addAttribute("carList", carList);
			return "addCar";
		}
		car.setUser(user);
		List<Car> cars = user.getCars();
		cars.add(car);
		car.setActive(true);
		carService.saveCar(car);
		return "redirect:/users/" + id + "/cars";
	}
	
	@GetMapping("/users/{id}/cars/edit/{carID}")
	public String editCarForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		User user = userService.getUserById(id);
		Car car = carService.getCarById(carID);
		model.addAttribute("car", car);
		model.addAttribute("user", user);
		return "editCar";
	}
	
	@PostMapping("/users/{id}/cars/edit/{carID}")
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
		existingCar.setCarPrice(car.getCarPrice());
		
		carService.editCar(existingCar);
		return "redirect:/users/" + id + "/cars";
	}
	
	@GetMapping("/users/{id}/cars/{carIdx}+{carID}")
	public String deleteCar(@PathVariable("id") Long id, @PathVariable("carIdx") int carIdx,
			@PathVariable("carID") Long carID)
	{
		Car car = carService.getCarById(carID);
		car.setActive(false);
		carService.saveCar(car);
		return "redirect:/users/" + id + "/cars";
	}
	
	@GetMapping("/users/{id}/cars/{carID}/traffic_insurance")
	public String trafficInsuranceForm(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		User user = userService.getUserById(id);
		Car car = carService.getCarById(carID);
		model.addAttribute("user", user);
		model.addAttribute("car", car);
		model.addAttribute("value", carService.carOffer(carID));
		return "traffic_insurance";
	}
	
	@GetMapping("/users/{id}/cars/{carID}/offer/accept")
	public String acceptOffer(@PathVariable Long id, @PathVariable Long carID)
	{
		Car car = carService.getCarById(id);
		String offer = carService.carOffer(carID).replace(',', '.');
		car.setOfferState(true);
		car.setOffer(Double.parseDouble(offer));
		car.setOfferDate(LocalDate.now());
		carService.saveCar(car);
		return "redirect:/users/" + id + "/cars";
	}
	
	@GetMapping("/users/{id}/cars/{carID}/offer/deny")
	public String denyOffer(@PathVariable Long id, @PathVariable Long carID, Model model)
	{
		Car car = carService.getCarById(id);
		double refund = 0;
		if (car.isOfferState())
		{
			LocalDate acceptanceDate = car.getOfferDate();
			LocalDate currentDate = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(acceptanceDate, currentDate);
			refund = (car.getOffer() - car.getYear() * 2.5) * (daysBetween + 1) / 100;
			car.setOfferState(false);
			carService.saveCar(car);
			
		}
		return "redirect:/users/" + id + "/cars/" + carID + "/traffic_insurance" + "?refund=" + refund;
		
	}
}
