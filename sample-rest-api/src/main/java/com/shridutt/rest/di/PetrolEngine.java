package com.shridutt.rest.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Primary
@Data
public class PetrolEngine implements Engine {

	private String engineType = "Petrol";

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

}
