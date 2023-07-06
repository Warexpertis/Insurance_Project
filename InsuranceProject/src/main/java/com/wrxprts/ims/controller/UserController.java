package com.wrxprts.ims.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.UserService;
import com.wrxprts.ims.web.dto.UserDto;

@Controller
public class UserController
{
	private UserService userService;
	
	public UserController(UserService userService)
	{
		super();
		this.userService = userService;
	}
	
	// Handler method to handle home page request
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	// Handler method to handle list students and, return model and view
	@GetMapping("/users")
	public String listUsers(Model model)
	{
		model.addAttribute("users", userService.findAllUsers());
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
	
	// Handler method to handle list the cars of current user
	@GetMapping("/users/cars/{id}")
	public String listUserCarsForm(@PathVariable Long id, Model model)
	{
		User user = userService.getUserById(id);
		List<Car> cars = userService.getCarsByUserId(id);
		model.addAttribute("user", user);
		model.addAttribute("cars", cars);
		return "user_cars";
	}
	
	// Handler method to handle delete user request
	@GetMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id)
	{
		userService.deleteUserById(id);
		return "redirect:/users";
	}
	
	// Handler method to handle user registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserDto());
		return "register";
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
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		
		// save update user object
		userService.updateUser(existingUser);
		return "redirect:/users";
	}
	
	// Handler method to handle user registration form submit request
	@PostMapping("/register/save")
	public String registerUserAccount(@Validated @ModelAttribute("user") UserDto userDto, BindingResult result,
			Model model)
	{
		User existingUser = userService.findUserByTc(userDto.getTc());
		
		if (existingUser != null && existingUser.getTc() != null && !existingUser.getTc().isEmpty())
		{
			result.rejectValue("tc", null, "There is already an account registered with the same TC");
		}
		
		if (result.hasErrors())
		{
			model.addAttribute("user", userDto);
			return "/register";
		}
		
		userService.registerUser(userDto);
		return "redirect:/register?success";
	}
	
	// Handler method to handle login request
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
}
