package com.wrxprts.ims.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HouseholdGood
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HouseholdGoodID", nullable = false)
	private Long id;
	
	private String category;
	
	private String brand;
	
	private String model;
	
	private Short year;
	
	private String price;
	
	private double offer;
	
	private boolean offerState = false;
	
	private LocalDate offerDate;
	
	private boolean active = true;
	
	@ManyToOne
	@JoinColumn(name = "house_id")
	private House house;
	
	public HouseholdGood()
	{
		
	}
	
	public HouseholdGood(String category, String brand, String model, Short year, String price, double offer,
			boolean offerState, LocalDate offerDate, boolean active, House house)
	{
		super();
		this.category = category;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
		this.offer = offer;
		this.offerState = offerState;
		this.offerDate = offerDate;
		this.active = active;
		this.house = house;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getBrand()
	{
		return brand;
	}
	
	public void setBrand(String brand)
	{
		this.brand = brand;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public void setModel(String model)
	{
		this.model = model;
	}
	
	public Short getYear()
	{
		return year;
	}
	
	public void setYear(Short year)
	{
		this.year = year;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public double getOffer()
	{
		return offer;
	}
	
	public void setOffer(double offer)
	{
		this.offer = offer;
	}
	
	public boolean isOfferState()
	{
		return offerState;
	}
	
	public void setOfferState(boolean offerState)
	{
		this.offerState = offerState;
	}
	
	public LocalDate getOfferDate()
	{
		return offerDate;
	}
	
	public void setOfferDate(LocalDate offerDate)
	{
		this.offerDate = offerDate;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public House getHouse()
	{
		return house;
	}
	
	public void setHouse(House house)
	{
		this.house = house;
	}
	
}
