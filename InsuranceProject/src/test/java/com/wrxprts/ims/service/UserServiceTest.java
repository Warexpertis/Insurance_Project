package com.wrxprts.ims.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wrxprts.ims.entity.Car;
import com.wrxprts.ims.entity.House;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class UserServiceTest
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	User user1, user2;
	
	@Test
	void testFindAllUsers()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20); // set the birth date to 10 years ago
		Date birthDate = calendar.getTime();
		user1 = new User("John", "Doe", birthDate, "Istanbul", "12345678903", "johp.doe@example.com",
				"password", true, new ArrayList<Car>(), new ArrayList<House>());
		user2 = new User("Jane", "Doe", birthDate, "Istanbul", "12345678906", "janes.doe@example.com",
				"password", true, new ArrayList<Car>(), new ArrayList<House>());
		userRepository.save(user1);
		userRepository.save(user2);
		
		List<String> emails = Arrays.asList(user1.getEmail(), user2.getEmail());
	    List<User> result = userRepository.findByEmailIn(emails);
		assertThat(result).containsExactlyInAnyOrder(user1, user2);
	}
	
	@BeforeEach
	@Test
	void testBirthDateValidation() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -10); // set the birth date to 10 years ago
		Date birthDate = calendar.getTime();
		
		User user = new User("John", "Doe", birthDate, "Istanbul", "12345678904", "john.dog@example.com",
				"password", true, new ArrayList<Car>(), new ArrayList<House>());
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertThat(violations).isNotEmpty();
	}
	
	@Test
	void testAddUser() {
	    // Create a new user
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20); // set the birth date to 10 years ago
		Date birthDate = calendar.getTime();
	    User user = new User("John", "Test", birthDate, "Istanbul", "12345234501", "john.dot@example.com",
	            "password", true, new ArrayList<Car>(), new ArrayList<House>());
	    userRepository.save(user);

	    // Verify that the user was added
	    User result = userService.getUserById(user.getId());
	    assertNotNull(result);
	    assertEquals(user, result);
	}

	@Test
	void testEditUser() {
	    // Create a new user
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20); // set the birth date to 10 years ago
		Date birthDate = calendar.getTime();
	    User user = new User("John", "Doe", birthDate, "Istanbul", "12345458901", "john.test@example.com",
	            "password", true, new ArrayList<Car>(), new ArrayList<House>());
	    userRepository.save(user);

	    // Edit the user's information
	    user.setName("Jane");
	    user.setSurname("Smith");
	    userRepository.save(user);

	    // Verify that the user's information was updated
	    User result = userService.getUserById(user.getId());
	    assertNotNull(result);
	    assertEquals("Jane", result.getName());
	    assertEquals("Smith", result.getSurname());
	}
	
}
