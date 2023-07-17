package com.wrxprts.ims.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.House;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.HouseRepository;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService
{
	private UserRepository userRepository;
	private HouseRepository houseRepository;
	
	public HouseServiceImpl(UserRepository userRepository, HouseRepository houseRepository)
	{
		super();
		this.userRepository = userRepository;
		this.houseRepository = houseRepository;
	}
	
	@Override
	public List<House> getHousesById(Long id)
	{
		User user = userRepository.findById(id).orElse(null);
		if (user == null)
			return null;
		return user.getHouses();
	}
	
	@Override
	public House addHouse(House house)
	{
		return houseRepository.save(house);
	}
	
	@Override
	public House editHouse(House house)
	{
		return houseRepository.save(house);
	}
	
	@Override
	public void deleteHouse(Long houseID)
	{
		houseRepository.deleteById(houseID);
	}
	
	@Override
	public House getHouseById(Long houseID)
	{
		return houseRepository.findById(houseID).get();
	}
	
}
