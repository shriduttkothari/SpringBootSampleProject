package com.shridutt.dao;

import com.shridutt.dao.model.Car;

public interface CarRepository {

	Car getCarByCarType(String carType);
	
	Car saveCar(Car car);
}
