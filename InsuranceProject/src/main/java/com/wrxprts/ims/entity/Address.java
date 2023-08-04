package com.wrxprts.ims.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



public class Address {
	/*
	@Id
	private Long id;
	
    private Province province;
    private District district;
    private Neighborhood neighborhood;
    private Street street;
    
    

    
    @Entity
    @Table(name = "provinces")
    public static class Province {
    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    	
    	@Column(name = "name")
        private String name;
        
        @OneToMany(mappedBy = "province")
        private List<District> districts;

        // getters and setters
    	
        public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<District> getDistricts() {
			return districts;
		}
		public void setDistricts(List<District> districts) {
			this.districts = districts;
		}
		
    }
    
    @Entity
    @Table(name = "districts")
    public static class District {
    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    	
    	@Column(name = "name")
        private String name;
        
        @ManyToOne
        @JoinColumn(name = "province_id")
        private Province province;
        
        @OneToMany(mappedBy = "district")
        private List<Neighborhood> neighborhoods;
        
        // getters and setters
        
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Province getProvince() {
			return province;
		}
		public void setProvince(Province province) {
			this.province = province;
		}
		public List<Neighborhood> getNeighborhoods() {
			return neighborhoods;
		}
		public void setNeighborhoods(List<Neighborhood> neighborhoods) {
			this.neighborhoods = neighborhoods;
		}
        
    }

    @Entity
    @Table(name = "neighborhoods")
    public static class Neighborhood {
    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    	
    	@Column(name = "name")
        private String name;
    	
    	@ManyToOne
        @JoinColumn(name = "district_id")
        private District district;
    	
    	@OneToMany(mappedBy = "neighborhood")
        private List<Street> streets;
        
        // getters and setters
        
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public District getDistrict() {
			return district;
		}
		public void setDistrict(District district) {
			this.district = district;
		}
		public List<Street> getStreets() {
			return streets;
		}
		public void setStreets(List<Street> streets) {
			this.streets = streets;
		}
        
    }

    @Entity
    @Table(name = "streets")
    public static class Street {
    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    	
        @Column(name = "name")
        private String name;
        
        @ManyToOne
        @JoinColumn(name = "neighborhood_id")
        private Neighborhood neighborhood;
      
     // getters and setters
        
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Neighborhood getNeighborhood() {
			return neighborhood;
		}
		public void setNeighborhood(Neighborhood neighborhood) {
			this.neighborhood = neighborhood;
		}
        
    }*/
}
