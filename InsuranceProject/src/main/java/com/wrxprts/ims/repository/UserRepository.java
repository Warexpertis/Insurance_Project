package com.wrxprts.ims.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	
	List<User> findByActive(boolean active);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByTc(String tc);
	
	List<User> findByEmailIn(List<String> emails);
	
	Optional<User> findByNameAndSurnameAndBirthDateAndProvince(String name, String surname, Date b_date,
			String province);
}
