package com.wrxprts.ims.service;

import java.util.List;

import com.wrxprts.ims.entity.HouseholdGood;

public interface HouseholdGoodService
{
	List<HouseholdGood> getGoodsById(Long id);
	
	HouseholdGood addGood(HouseholdGood good);
}
