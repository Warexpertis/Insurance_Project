package com.wrxprts.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.UserService;
import com.wrxprts.ims.web.dto.UserRegistrationDto;

@Controller
public class UserController
{
	private UserService userService;
	
	public UserController(UserService userService)
	{
		super();
		this.userService = userService;
	}
	
	// Handler method to handle list students and, return model and view
	@GetMapping("/users")
	public String listUsers(Model model)
	{
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createUserForm(Model model)
	{
		// Create user object to hold user form data
		User user = new User();
		model.addAttribute("user", user);
		return "create_user";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUserForm(@PathVariable Long id, Model model)
	{
		model.addAttribute("user", userService.getUserById(id));
		return "edit_user";
	}
	
	// Handler method to handle delete user request
	@GetMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id)
	{
		userService.deleteUserById(id);
		return "redirect:/users";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}
	
	@PostMapping("/users")
	public String saveUser(@ModelAttribute("user") User user)
	{
		userService.saveUser(user);
		return "redirect:/users";
	}
	
	@PostMapping("/users/{id}")
	public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model)
	{
		// get user from database by id
		User existingUser = userService.getUserById(id);
		existingUser.setName(user.getName());
		existingUser.setSurname(user.getSurname());
		existingUser.setB_date(user.getB_date());
		existingUser.setProvince(user.getProvince());
		existingUser.setTc(user.getTc());
		existingUser.setPassword(user.getPassword());
		
		// save update user object
		userService.updateUser(existingUser);
		return "redirect:/users";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto)
	{
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
