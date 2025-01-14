package com.wrxprts.ims.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.wrxprts.ims.validator.AgeConstraint;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TBLUSER", uniqueConstraints = @UniqueConstraint(columnNames = "UserTC"))
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID", nullable = false)
	private Long id;
	
	@Size(max = 15, message = "Max allowed length is 15")
	@NotBlank(message = "Can not be empty")
	@Column(name = "UserName", length = 15)
	private String name;
	
	@Size(max = 10, message = "Max allowed length is 10")
	@NotBlank(message = "Can not be empty")
	@Column(name = "UserSurname", length = 10)
	private String surname;
	
	@NotNull(message = "Can not be empty")
	@AgeConstraint(message = "Costumer must be older than 18")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "UserBirthDate")
	private Date birthDate;
	
	@NotBlank(message = "Can not be empty")
	@Column(name = "UserProvince", length = 13)
	private String province;
	
	@NotBlank(message = "Can not be empty")
	@Email(message = "Enter a valid email")
	@Column(name = "UserEmail", unique = true)
	private String email;
	
	@Size(min = 11, message = "Enter a valid TC")
	@Digits(integer = 11, fraction = 0, message = "Enter a valid TC")
	@Column(name = "UserTC", columnDefinition = "char(11)")
	private String tc;
	
	@Size(min = 8, message = "Min allowed length is 8")
	@NotBlank(message = "Can not be empty")
	@Column(name = "UserPassword")
	private String password;
	
	private boolean active = true;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Car> cars = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<House> houses = new ArrayList<>();
	
	public User()
	{
		
	}
	
	public User(String tc, String email, String password)
	{
		super();
		this.tc = tc;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String surname, Date birthDate, String province, String tc, String email,
			String password, boolean active, List<Car> cars, List<House> houses)
	{
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.province = province;
		this.tc = tc;
		this.email = email;
		this.password = password;
		this.active = active;
		this.cars = cars;
		this.houses = houses;
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
	
	public Date getBirthDate()
	{
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
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
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public List<Car> getCars()
	{
		return cars.stream().filter(Car::isActive).collect(Collectors.toList());
	}
	
	public void setCars(List<Car> cars)
	{
		this.cars = cars;
	}
	
	public List<House> getHouses()
	{
		return houses;
	}
	
	public void setHouses(List<House> houses)
	{
		this.houses = houses;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    User user = (User) obj;
	    return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
	
}
