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
import com.wrxprts.ims.entity.HouseholdGood;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.HouseService;
import com.wrxprts.ims.service.HouseholdGoodService;
import com.wrxprts.ims.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HouseholdGoodController
{
	private UserService userService;
	private HouseService houseService;
	private HouseholdGoodService householdGoodService;
	
	public HouseholdGoodController(UserService userService, HouseService houseService,
			HouseholdGoodService householdGoodService)
	{
		super();
		this.userService = userService;
		this.houseService = houseService;
		this.householdGoodService = householdGoodService;
	}
	
	@GetMapping("/users/houses/{id}/{houseID}")
	public String listHouseGoodsForm(@PathVariable Long id, @PathVariable Long houseID, Model model)
	{
		User user = userService.getUserById(id);
		House house = houseService.getHouseById(houseID);
		List<HouseholdGood> goods = house.getGoods();
		model.addAttribute("user", user);
		model.addAttribute("house", house);
		model.addAttribute("goods", goods);
		return "house_goods";
	}
	
	@GetMapping("/users/houses/{id}/{houseID}/new")
	public String addGoodForm(@PathVariable Long id, @PathVariable Long houseID, Model model)
	{
		User user = userService.getUserById(id);
		House house = houseService.getHouseById(houseID);
		HouseholdGood good = new HouseholdGood();
		good.setHouse(house);
		model.addAttribute("user", user);
		model.addAttribute("house", house);
		model.addAttribute("good", good);
		return "addGood";
	}
	
	@PostMapping("/users/houses/{id}/{houseID}/new")
	public String saveGood(@PathVariable Long id, @Valid @ModelAttribute("house") House house,
			BindingResult bindingResult, @ModelAttribute("user") User user)
	{
		
		house.setUser(user);
		List<House> houses = user.getHouses();
		houses.add(house);
		// householdGoodService.addGood(good);
		return "redirect:/users/houses/" + id;
	}
	
}
