package com.shridutt.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.model.Car;
import com.shridutt.dao.repository.CarRepository;
import com.shridutt.rest.dto.CarDTO;
import com.shridutt.rest.mapper.CarMapper;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

	@InjectMocks
	private CarServiceImpl classUnderTest;
	
	@Mock
	private CarRepository carRepository;
	
	@Mock
	private CarMapper carMapper;
	
	@Test
	public void test_get_car_by_car_type_should_return_empty_list_when_car_type_is_null( ) throws CarNotFoundException {
	
		when(carRepository.getCarByCarType(null)).thenReturn(new ArrayList<>());
		
		List<CarDTO> actualOutput = classUnderTest.getCarByCarType(null);
		
		assertThat(actualOutput).isEmpty();
	}
	
	@Test
	public void test_get_car_by_car_type_should_return_empty_list_when_car_type_is_not_null( ) throws CarNotFoundException {
	
		when(carRepository.getCarByCarType("petrol")).thenReturn(new ArrayList<>());
		
		List<CarDTO> actualOutput = classUnderTest.getCarByCarType("petrol");
		
		assertThat(actualOutput).isEmpty();
	}
	
	
	@Test
	public void test_get_car_by_car_type_should_not_thor_nullpointer_exception( ) throws CarNotFoundException {
	
		when(carRepository.getCarByCarType("petrol")).thenReturn(null);
		
		List<CarDTO> actualOutput = classUnderTest.getCarByCarType("petrol");
		
		assertThat(actualOutput).isEmpty();
	}
	
	@Test
	public void test_get_car_by_car_type_should_not_return_empty_list_when_car_type_is_not_null( ) throws CarNotFoundException {
	
		List<Car> listCars = new ArrayList<>();
		Car car = new Car("ALtroz", "petrol");
		listCars.add(car);
		when(carRepository.getCarByCarType("petrol")).thenReturn(listCars);
		
		CarDTO carDTO = new CarDTO();
		carDTO.setName("ALtroz");
		carDTO.setEngineType("petrol");
		
		when(carMapper.mapToCarDTO(car)).thenReturn(carDTO);
		
		List<CarDTO> actualOutput = classUnderTest.getCarByCarType("petrol");
		
		assertThat(actualOutput).isNotEmpty();
		assertThat(actualOutput.size()).isEqualTo(1);
		assertThat(actualOutput.get(0).getName()).isEqualTo("ALtroz");
		assertThat(actualOutput.get(0).getEngineType()).isEqualTo("petrol");
	}
	
	@Test
	public void test_get_car_by_car_type_should_not_return_list_with_empty_values( ) throws CarNotFoundException {
	
		List<Car> listCars = new ArrayList<>();
		Car car = new Car("ALtroz", "petrol");
		listCars.add(car);
		when(carRepository.getCarByCarType("petrol")).thenReturn(listCars);
		
		CarDTO carDTO = new CarDTO();
		carDTO.setName("ALtroz");
		carDTO.setEngineType("petrol");
		
		when(carMapper.mapToCarDTO(car)).thenReturn(null);
		
		List<CarDTO> actualOutput = classUnderTest.getCarByCarType("petrol");
		
		assertThat(actualOutput).isEmpty();
	}
	
}
