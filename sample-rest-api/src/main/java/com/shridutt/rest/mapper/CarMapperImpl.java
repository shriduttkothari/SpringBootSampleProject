package com.shridutt.rest.mapper;

import org.springframework.stereotype.Component;

import com.shridutt.dao.model.Car;
import com.shridutt.rest.dto.CarDTO;

@Component
public class CarMapperImpl implements CarMapper {

	@Override
	public CarDTO mapToCarDTO(Car car) {
		if(null != car) {
			CarDTO carDTO = new CarDTO();
			carDTO.setName(car.getName());
			carDTO.setEngineType(car.getEngineType());
			return carDTO;
		} else {
			return null;
		}
	}

	@Override
	public Car mapToCar(CarDTO carDTO) {
		Car car = new Car(carDTO.getName(), carDTO.getEngineType());
		return car;
	}

}
