package com.shridutt.dao.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class Car {

	private String name = "Altroz";
	private Engine engine;
	
	public Car(Engine engine) {
		this.engine = engine;
	}
	
}
