package com.shridutt.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shridutt.dao.model.Car;
import com.shridutt.dao.model.Engine;

@Repository
public class CarRepositoryImpl implements CarRepository {

	@Autowired
	private Engine petrolEngine;
	
	@Autowired
	private Engine electricEngine;

	@Override
	public Car getCarByCarType(String carType) {
		Car car = null;
		if ("petrol".equals(carType)) {
			car = new Car(petrolEngine);
			return car;
		} else if ("electric".equals(carType)) {
			car = new Car(electricEngine);
			return car;
		} else {
			return null;
		}
	}

	@Override
	public Car saveCar(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

}
