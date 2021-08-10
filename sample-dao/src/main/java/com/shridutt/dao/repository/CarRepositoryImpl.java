package com.shridutt.dao.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.exception.ConflictException;
import com.shridutt.dao.model.Car;

@Repository
public class CarRepositoryImpl implements CarRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Car> getCarByCarType(String carType) throws CarNotFoundException {
		return getCar(carType);
	}

	@Override
	public Car saveCar(Car car) throws ConflictException {
		try {
			getCar(car.getEngineType());
		} catch (CarNotFoundException e) {
			String queryString = "INSERT INTO car (car_name,engine_type) VALUES ('"+car.getName()+"','"+car.getEngineType()+"');";
			int rowsAffected = jdbcTemplate.update(queryString);

			if (rowsAffected > 0) {
				return getCar(car.getName(), car.getEngineType());
			} else {
				return null;
			}
		} 
		throw new ConflictException();
		
	}

	private List<Car> getCar(String carType) throws CarNotFoundException {
		String queryString = "SELECT * FROM car WHERE engine_type LIKE '"+carType+"';";
		List<Car> carList = jdbcTemplate.query(queryString,  new RowMapper<Car>() {
			@Override
			public Car mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}
		});
		
		if (null == carList || carList.isEmpty()) {
			throw new CarNotFoundException();
		}

		return carList;

	}

	private Car getCar(String carName, String carType) {
		String queryString = "SELECT * FROM car WHERE car_name LIKE '"+carName+"' AND engine_type LIKE '"+carType+"' LIMIT 1;";
		Car car = jdbcTemplate.queryForObject(queryString, new RowMapper<Car>() {
			@Override
			public Car mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}
		});

		return car;
	}

}
