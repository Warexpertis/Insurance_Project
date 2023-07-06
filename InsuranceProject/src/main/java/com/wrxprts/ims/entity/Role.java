package com.wrxprts.ims.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBLROLE")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleID", nullable = false)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	public Role()
	{
		
	}
	
	public Role(String email, List<User> users)
	{
		super();
		this.email = email;
		this.users = users;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public List<User> getUsers()
	{
		return users;
	}
	
	public void setUsers(List<User> users)
	{
		this.users = users;
	}
	
}
