package com.shridutt.rest.mapper;

import org.springframework.stereotype.Component;

import com.shridutt.dao.model.Car;
import com.shridutt.dao.model.ElectricEngine;
import com.shridutt.dao.model.Engine;
import com.shridutt.rest.dto.CarDTO;

@Component
public class CarMapperImpl implements CarMapper {

	@Override
	public CarDTO mapToCarDTO(Car car) {

		CarDTO carDTO = new CarDTO();
		carDTO.setName(car.getName());
		Engine engine = car.getEngine();
		if(engine instanceof ElectricEngine) {
			ElectricEngine electricEngine = (ElectricEngine )engine;
			carDTO.setEngineType(electricEngine.getEngineType());
		} else {
			carDTO.setEngineType(null);
		}
		return carDTO;
	}

	@Override
	public Car mapToCar(CarDTO carDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
