package com.wrxprts.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wrxprts.ims.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>
{
	
}
