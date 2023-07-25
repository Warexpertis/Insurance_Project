package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.House;

public interface HouseService
{
	List<House> getHousesById(Long id);
	
	House getHouseById(Long houseID);
	
	House addHouse(House house);
	
	House editHouse(House house);
	
	String houseOffer(Long carID);
}
