package com.wrxprts.ims.web.dto;

public class UserDto
{
	private String tc;
	
	private String email;
	
	private String password;
	
	public UserDto()
	{
		
	}
	
	public UserDto(String tc, String email, String password)
	{
		super();
		this.tc = tc;
		this.email = email;
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
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
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
