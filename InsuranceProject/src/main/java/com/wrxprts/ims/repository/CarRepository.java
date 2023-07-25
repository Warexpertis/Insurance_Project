package com.wrxprts.ims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>
{
	Optional<Car> findByModelAndYearAndMileage(String model, Short year, int mileage);
}
