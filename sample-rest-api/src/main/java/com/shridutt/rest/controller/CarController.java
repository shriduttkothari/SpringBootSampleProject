package com.shridutt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.rest.dto.CarDTO;
import com.shridutt.rest.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CarDTO>> getCar(@RequestParam("cartype") String carType) throws CarNotFoundException {
		List<CarDTO> carDTOList = carService.getCarByCarType(carType);
		return ResponseEntity.ok(carDTOList);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CarDTO> saveCar(@RequestBody CarDTO carDTO) {
		
		CarDTO carDTOSaved = carService.save(carDTO);
		
		return ResponseEntity.ok(carDTOSaved);
	}
}
