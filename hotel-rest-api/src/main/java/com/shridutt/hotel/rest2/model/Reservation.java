package com.shridutt.hotel.rest2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * THis class is not being used currently
 * 
 * 
 *
 */
@Entity
@Table(name = "reservation")
public class Reservation {

	// reservation fields and annotate with it's column to connect to jpa entity
	// manager

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private int id;
	
	@Column(name = "reservation_room")
	private String room;

	@Column(name = "reservation_price")
	private int price;

	@Column(name = "reservation_num_of_rooms")
	private int rooms;

	@Column(name = "reservation_num_of_persons")
	private int persons;

	@Column(name = "reservation_num_of_children")
	private int children;

	@Column(name = "reservation_open_buffet")
	private String openBuffet;

	@Column(name = "reservation_from_date")
	private Date arrivalDate;

	@Column(name = "reservation_stay_days")
	private int stayDays;

	@Column(name = "reservation_user_id")
	private int userId;
}