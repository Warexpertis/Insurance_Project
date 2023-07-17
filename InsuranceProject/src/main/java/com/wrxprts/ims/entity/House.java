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
@Table(name = "TBLHOUSE")
public class House
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HouseID", nullable = false)
	private Long id;
	
	@NotBlank(message = "Can not be empty")
	private String province;
	
	@NotBlank(message = "Can not be empty")
	private String district;
	
	@NotBlank(message = "Can not be empty")
	private String neighborhood;
	
	@NotBlank(message = "Can not be empty")
	private String aveORstrt;
	
	@NotBlank(message = "Can not be empty")
	private String aptNo;
	
	@NotBlank(message = "Can not be empty")
	private String flatNo;
	
	@PositiveOrZero
	private byte floor;
	
	@Positive
	private int size;
	
	@Min(1990)
	private short year;
	
	@Positive
	private int housePrice;
	
	private int offer;
	
	private boolean offerState;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	public House()
	{
		
	}
	
	public House(String province, String district, String neighborhood, String aveORstrt, String aptNo,
			String flatNo, byte floor, int size, short year, int housePrice, int offer, boolean offerState,
			User user)
	{
		super();
		this.province = province;
		this.district = district;
		this.neighborhood = neighborhood;
		this.aveORstrt = aveORstrt;
		this.aptNo = aptNo;
		this.flatNo = flatNo;
		this.floor = floor;
		this.size = size;
		this.year = year;
		this.housePrice = housePrice;
		this.offer = offer;
		this.offerState = offerState;
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
	
	public String getProvince()
	{
		return province;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	public String getDistrict()
	{
		return district;
	}
	
	public void setDistrict(String district)
	{
		this.district = district;
	}
	
	public String getNeighborhood()
	{
		return neighborhood;
	}
	
	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}
	
	public String getAveORstrt()
	{
		return aveORstrt;
	}
	
	public void setAveORstrt(String aveORstrt)
	{
		this.aveORstrt = aveORstrt;
	}
	
	public String getAptNo()
	{
		return aptNo;
	}
	
	public void setAptNo(String aptNo)
	{
		this.aptNo = aptNo;
	}
	
	public String getFlatNo()
	{
		return flatNo;
	}
	
	public void setFlatNo(String flatNo)
	{
		this.flatNo = flatNo;
	}
	
	public byte getFloor()
	{
		return floor;
	}
	
	public void setFloor(byte floor)
	{
		this.floor = floor;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public short getYear()
	{
		return year;
	}
	
	public void setYear(short year)
	{
		this.year = year;
	}
	
	public int getHousePrice()
	{
		return housePrice;
	}
	
	public void setHousePrice(int housePrice)
	{
		this.housePrice = housePrice;
	}
	
	public int getOffer()
	{
		return offer;
	}
	
	public void setOffer(int offer)
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
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
}
