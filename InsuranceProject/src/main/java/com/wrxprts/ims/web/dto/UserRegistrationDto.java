package com.wrxprts.ims.web.dto;

public class UserRegistrationDto
{
	private String tc;
	private String password;
	
	public UserRegistrationDto()
	{
		
	}
	
	public UserRegistrationDto(String tc, String password)
	{
		super();
		this.tc = tc;
		this.password = password;
	}
	
	public String getTc()
	{
		return tc;
	}
	
	public void setTc(String tc)
	{
		this.tc = tc;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
