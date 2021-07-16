package com.shridutt.hotel.rest2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String type;
	private BigDecimal pricePerNight;
	private int roomSize;
	private boolean wiFi;
	private boolean airCondition;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "motel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Motel motel;
	
	@Column(length=10000)
	private HashMap<LocalDate, Boolean> availabilityHashMap;

}
