package com.shridutt.rest.di;

import lombok.Data;

@Data
public class ElectricEngine implements Engine {

	private int hoursePower;
	private String engineType = "Electric";
	
	public ElectricEngine(int hoursePower) {
		this.hoursePower = hoursePower;
	}
	
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

}
