package com.shridutt.dao.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ElectricEngine implements Engine {

	private String engineType = "Electric";
	
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

}
