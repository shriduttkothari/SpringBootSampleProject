package com.shridutt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.model.Car;
import com.shridutt.dao.repository.CarRepository;
import com.shridutt.rest.dto.CarDTO;
import com.shridutt.rest.mapper.CarMapper;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarMapper carMapper;
	
	@Override
	public CarDTO getCarByCarType(String carType) throws CarNotFoundException {
		Car carFromDB = carRepository.getCarByCarType(carType);
		CarDTO carDTO = carMapper.mapToCarDTO(carFromDB);
		return carDTO;
	}

	@Override
	public CarDTO save(CarDTO carDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
