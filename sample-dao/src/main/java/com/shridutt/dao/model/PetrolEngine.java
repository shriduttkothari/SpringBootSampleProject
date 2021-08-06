package com.shridutt.dao.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PetrolEngine implements Engine {

	private String engineType = "Petrol";

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

}
