package com.shridutt.rest.mapper;

import com.shridutt.dao.model.Car;
import com.shridutt.rest.dto.CarDTO;

public interface CarMapper {
	
	CarDTO mapToCarDTO(Car car);
	
	Car mapToCar(CarDTO carDTO);

}
