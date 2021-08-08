package com.shridutt.dao.model;

import lombok.Data;

@Data
public class Car {

	private int id;
	private String name;
	private String engineType;

	public Car(String name, String engineType) {
		this.name = name;
		this.engineType = engineType;
	}
	
	public Car(int id, String name, String engineType) {
		this.id = id;
		this.name = name;
		this.engineType = engineType;
	}
}
