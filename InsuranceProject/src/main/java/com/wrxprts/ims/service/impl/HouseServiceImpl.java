package com.wrxprts.ims.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
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
	public House getHouseById(Long houseID)
	{
		return houseRepository.findById(houseID).get();
	}
	
	@Override
	public String houseOffer(Long houseID)
	{
		House house = houseRepository.findById(houseID).get();
		int age = house.getYear() - Calendar.getInstance().get(Calendar.YEAR);
		double offer = house.getHousePrice() * -1
				* (age * 0.0002 + house.getSize() * 0.0000003 + house.getFloor() * 0.00000006);
		DecimalFormat decfor = new DecimalFormat("0.00");
		return decfor.format(offer);
	}
	
}
