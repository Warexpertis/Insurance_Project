package com.wrxprts.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.service.UserService;
import com.wrxprts.ims.web.dto.UserDto;

import jakarta.validation.Valid;

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
	
	// Handler method to handle delete user request
	@GetMapping("/users/deactivate/{id}")
	public String deleteUser(@PathVariable Long id)
	{
		User user = userService.getUserById(id);
		user.setActive(false);
		List<Car> cars=user.getCars();
		for(Car car:cars) car.setActive(false);
		userService.saveUser(user);
		return "redirect:/users";
	}
	
	// Handler method to handle user registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserDto());
		return "register";
	}
	
	@PostMapping("/users/new")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
	{
		boolean emailExists = userService.emailExists(user.getEmail());
		boolean tcExists = userService.tcExists(user.getTc());
		boolean userExists = userService.userExists(user.getName(), user.getSurname(), user.getBirthDate(),
				user.getProvince());
		if (emailExists)
			bindingResult.rejectValue("email", "error.user", "This email already belongs to an user");
		if (tcExists)
			bindingResult.rejectValue("tc", "error.user", "This tc already belongs to an user");
		if (userExists)
			bindingResult.reject("error.user", "This user already exists");
		
		if (bindingResult.hasErrors())
			return "create_user";
		
		user.setActive(true);
		userService.saveUser(user);
		return "redirect:/users";
		
	}
	
	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") User user,
			BindingResult bindingResult)
	{
		
		if (bindingResult.hasErrors())
			return "edit_user";
		
		// get user from database by id
		User existingUser = userService.getUserById(id);
		existingUser.setName(user.getName());
		existingUser.setSurname(user.getSurname());
		existingUser.setBirthDate(user.getBirthDate());
		existingUser.setProvince(user.getProvince());
		existingUser.setTc(user.getTc());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		
		// save update user object
		userService.updateUser(existingUser);
		return "redirect:/users";
	}
	
	@GetMapping("/users/{id}/assets")
	public String custAssetsForm(@PathVariable Long id, Model model)
	{
		User cust = userService.getUserById(id);
		model.addAttribute("cust", cust);
		return "customer_page";
	}
	
	// Handler method to handle login request
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
}
