package com.wrxprts.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBLROLE")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID", nullable = false)
	private Long id;
	private String u_name;
	
	public Role()
	{
		
	}
	
	public Role(String u_name)
	{
		super();
		this.u_name = u_name;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getU_name()
	{
		return u_name;
	}
	
	public void setU_name(String u_name)
	{
		this.u_name = u_name;
	}
	
}
