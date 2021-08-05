package com.shridutt.rest.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Primary
public class PetrolEngine implements Engine {

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

}
