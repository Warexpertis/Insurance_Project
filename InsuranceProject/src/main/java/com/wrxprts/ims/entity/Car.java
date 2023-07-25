package com.wrxprts.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "TBLCAR")
public class Car
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CarID", nullable = false)
	private Long id;
	
	@NotBlank
	private String brand;
	
	@NotBlank
	private String model;
	
	@NotBlank
	private String motorType;
	
	@Min(1990)
	private Short year;
	
	@PositiveOrZero
	private int mileage;
	
	@Positive
	private int carPrice;
	
	private boolean offerState = false;
	
	private double offer;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	public Car()
	{
		
	}
	
	public Car(String brand, String model, String motorType, Short year, int mileage, int carPrice,
			boolean offerState, int offer, User user)
	{
		super();
		this.brand = brand;
		this.model = model;
		this.motorType = motorType;
		this.year = year;
		this.mileage = mileage;
		this.carPrice = carPrice;
		this.offerState = offerState;
		this.offer = offer;
		this.user = user;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
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
	
	public String getMotorType()
	{
		return motorType;
	}
	
	public void setMotorType(String motorType)
	{
		this.motorType = motorType;
	}
	
	public Short getYear()
	{
		return year;
	}
	
	public void setYear(Short year)
	{
		this.year = year;
	}
	
	public int getMileage()
	{
		return mileage;
	}
	
	public void setMileage(int mileage)
	{
		this.mileage = mileage;
	}
	
	public int getCarPrice()
	{
		return carPrice;
	}
	
	public void setCarPrice(int carPrice)
	{
		this.carPrice = carPrice;
	}
	
	public boolean isOfferState()
	{
		return offerState;
	}
	
	public void setOfferState(boolean offerState)
	{
		this.offerState = offerState;
	}
	
	public double getOffer()
	{
		return offer;
	}
	
	public void setOffer(double offer)
	{
		this.offer = offer;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
}
