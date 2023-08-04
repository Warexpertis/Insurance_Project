package com.wrxprts.ims.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.House;
import com.wrxprts.ims.entity.HouseholdGood;
import com.wrxprts.ims.repository.HouseRepository;
import com.wrxprts.ims.repository.HouseholdGoodRepository;
import com.wrxprts.ims.repository.UserRepository;
import com.wrxprts.ims.service.HouseholdGoodService;

@Service
public class HouseholdGoodServiceImpl implements HouseholdGoodService
{
	private UserRepository userRepository;
	private HouseRepository houseRepository;
	private HouseholdGoodRepository householdGoodRepository;
	
	public HouseholdGoodServiceImpl(UserRepository userRepository, HouseRepository houseRepository,
			HouseholdGoodRepository householdGoodRepository)
	{
		super();
		this.userRepository = userRepository;
		this.houseRepository = houseRepository;
		this.householdGoodRepository = householdGoodRepository;
	}
	
	@Override
	public List<HouseholdGood> getGoodsById(Long houseID)
	{
		House house = houseRepository.findById(houseID).orElse(null);
		if (house == null)
			return null;
		return house.getGoods();
	}
	
	@Override
	public HouseholdGood addGood(HouseholdGood good)
	{
		return householdGoodRepository.save(good);
	}
	
}
