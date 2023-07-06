package com.wrxprts.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>
{
	
}
