package com.shridutt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.rest.di.Car;
import com.shridutt.rest.di.Engine;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private Engine petrolEngine;

	@Autowired
	@Qualifier(value = "electricEngine")
	private Engine electricEngine;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getEmployee(@RequestParam("cartype") String carType) {
		Car car = null;
		if ("petrol".equals(carType)) {
			car = new Car(petrolEngine);
			return ResponseEntity.ok(car);
		} else if ("electric".equals(carType)) {
			car = new Car(electricEngine);
			return ResponseEntity.ok(car);
		} else {
			return ResponseEntity.badRequest().body("Car type is not supported: " + carType);
		}
	}
}
