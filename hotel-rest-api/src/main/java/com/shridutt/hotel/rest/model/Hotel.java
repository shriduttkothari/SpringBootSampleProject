package com.shridutt.hotel.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @Column(length=1000)
	private String address;
	@Column(length=1000)
	private String categories;
	@Column(length=500)
	private String city;
	private String country;
	@Column(length=50)
	private String latitude;
	@Column(length=50)
	private String longitude;
	@Column(length=1000)
	private String name;
	@Column(length=20)
	private String postalcode;
	@Column(length=50)
	private String province;
	private String ratings;
	@Column(length=2000)
	private String reviewText;
	@Column(length=1000)
	private String reviewTitle;
	@Column(length=100)
	private String reviewUsername;
	
}
