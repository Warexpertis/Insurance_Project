package com.wrxprts.ims.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wrxprts.ims.entity.House;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.HouseService;
import com.wrxprts.ims.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HouseController
{
	private UserService userService;
	private HouseService houseService;
	
	public HouseController(UserService userService, HouseService houseService)
	{
		super();
		this.userService = userService;
		this.houseService = houseService;
	}
	
	@GetMapping("/users/houses/{id}")
	public String listCustHousesForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		List<House> houses = houseService.getHousesById(id);
		model.addAttribute("user", user);
		model.addAttribute("houses", houses);
		return "user_houses";
	}
	
	@GetMapping("/users/houses/{id}/new")
	public String addHouseForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		House house = new House();
		house.setUser(user);
		model.addAttribute("user", user);
		model.addAttribute("house", house);
		return "addHouse";
	}
	
	@PostMapping("/users/houses/{id}/new")
	public String saveHouse(@PathVariable Long id, @Valid @ModelAttribute("house") House house,
			BindingResult bindingResult, @ModelAttribute("user") User user)
	{
		if (bindingResult.hasErrors())
			return "addHouse";
		house.setUser(user);
		List<House> houses = user.getHouses();
		houses.add(house);
		houseService.addHouse(house);
		return "redirect:/users/houses/" + id;
	}
	
	@GetMapping("/users/houses/{id}/edit/{houseID}")
	public String editHouseForm(@PathVariable Long id, @PathVariable Long houseID, Model model)
	{
		User user = userService.getUserById(id);
		model.addAttribute("house", houseService.getHouseById(houseID));
		model.addAttribute("user", user);
		return "editHouse";
	}
	
	@PostMapping("/users/houses/{id}/edit/{houseID}")
	public String editHouse(@PathVariable Long id, @PathVariable Long houseID,
			@Valid @ModelAttribute("house") House house, Model model, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
			return "editHouse";
		House existingHouse = houseService.getHouseById(houseID);
		existingHouse.setProvince(house.getProvince());
		existingHouse.setDistrict(house.getDistrict());
		existingHouse.setNeighborhood(house.getNeighborhood());
		existingHouse.setAveORstrt(house.getAveORstrt());
		existingHouse.setAptNo(house.getAptNo());
		existingHouse.setFlatNo(house.getFlatNo());
		existingHouse.setFloor(house.getFloor());
		existingHouse.setSize(house.getSize());
		existingHouse.setYear(house.getYear());
		existingHouse.setHousePrice(house.getHousePrice());
		
		houseService.editHouse(existingHouse);
		return "redirect:/users/houses/" + id;
	}
	
	@GetMapping("/users/houses/{id}/{houseIdx}+{houseID}")
	public String deleteHouse(@PathVariable("id") Long id, @PathVariable("houseIdx") int houseIdx,
			@PathVariable("houseID") Long houseID)
	{
		User user = userService.getUserById(id);
		List<House> houses = user.getHouses();
		houses.remove(houseIdx);
		houseService.deleteHouse(houseID);
		return "redirect:/users/house/" + id;
	}
	
}