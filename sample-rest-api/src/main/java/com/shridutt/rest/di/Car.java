package com.shridutt.rest.di;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Car {

	private String name = "Altroz";
	private Engine engine;
	
	public Car(Engine engine) {
		this.engine = engine;
	}
	
	@Bean
	public ElectricEngine electricEngine() {
		ElectricEngine electricEngine = new ElectricEngine(100);
		return electricEngine;
	}
}
