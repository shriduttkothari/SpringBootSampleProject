package com.shridutt.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.exception.ConflictException;
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
	public List<CarDTO> getCarByCarType(String carType) throws CarNotFoundException {
		List<Car> carsFromDB = carRepository.getCarByCarType(carType);
		
		List<CarDTO> carDTOs = carsFromDB.stream()
				.map(carFromDB -> carMapper.mapToCarDTO(carFromDB))
				.collect(Collectors.toList());

		return carDTOs;
	}

	@Override
	public CarDTO save(CarDTO carDTO) throws ConflictException {
		Car car = carMapper.mapToCar(carDTO);
		Car carFromDB = carRepository.saveCar(car);
		CarDTO carDTOFromDB = carMapper.mapToCarDTO(carFromDB);
		return carDTOFromDB;
	}

}
