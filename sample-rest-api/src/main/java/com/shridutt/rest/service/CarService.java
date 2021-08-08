package com.shridutt.rest.service;

import java.util.List;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.rest.dto.CarDTO;

public interface CarService {

	List<CarDTO> getCarByCarType(String carType) throws CarNotFoundException;

	CarDTO save(CarDTO carDTO);
}
