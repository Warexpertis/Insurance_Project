package com.wrxprts.ims.entity;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "TBLUSER", uniqueConstraints = @UniqueConstraint(columnNames = "UserTC"))
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID", nullable = false)
	private Long id;
	
	@Column(name = "UserName", length = 15)
	private String name;
	
	@Column(name = "UserSurname", length = 10)
	private String surname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "UserBirthDate")
	private Date b_date;
	
	@Column(name = "UserProvince", length = 13)
	private String province;
	
	@Column(name = "UserTC", columnDefinition = "char(11)")
	private String tc;
	
	@Column(name = "UserPassword", length = 40)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "UserID"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "UserID"))
	private Collection<Role> roles;
	
	public User()
	{
		
	}
	
	public User(String tc, String password, Collection<Role> roles)
	{
		super();
		this.tc = tc;
		this.password = password;
		this.roles = roles;
	}
	
	public User(String name, String surname, Date b_date, String province, String tc, String password,
			Collection<Role> roles)
	{
		super();
		this.name = name;
		this.surname = surname;
		this.b_date = b_date;
		this.province = province;
		this.tc = tc;
		this.password = password;
		this.roles = roles;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public Date getB_date()
	{
		return b_date;
	}
	
	public void setB_date(Date b_date)
	{
		this.b_date = b_date;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
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
	
	public Collection<Role> getRoles()
	{
		return roles;
	}
	
	public void setRoles(Collection<Role> roles)
	{
		this.roles = roles;
	}
	
}
