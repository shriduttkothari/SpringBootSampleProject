package com.shridutt.rest.service;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.rest.dto.CarDTO;

public interface CarService {

	CarDTO getCarByCarType(String carType) throws CarNotFoundException;

	CarDTO save(CarDTO carDTO);
}
