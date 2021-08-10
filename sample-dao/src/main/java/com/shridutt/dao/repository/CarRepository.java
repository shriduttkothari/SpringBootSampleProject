package com.shridutt.dao.repository;

import java.util.List;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.exception.ConflictException;
import com.shridutt.dao.model.Car;

public interface CarRepository {

	List<Car> getCarByCarType(String carType) throws CarNotFoundException;
	
	Car saveCar(Car car) throws ConflictException;
}
