package com.shridutt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.rest.di.Car;
import com.shridutt.rest.di.Engine;
import com.shridutt.rest.dto.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	private Engine petrolEngine1;
	
	@Autowired
	@Qualifier(value="electricEngine")
	private Engine electricEngine;
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> getEmployee(@RequestParam("employeeId") int employeeNumber) {
	
		
		
		
		Car altrozCar1 = new Car(petrolEngine1);
		Car altrozCar2 = new Car(electricEngine);
		
		
		
		
		
		
		Employee employee = Employee.builder()
								.employeeId(employeeNumber)
								.employeeName("Shridutt")
								.employeeSurname("Kothari")
								.employeeDepartment("Keystone")
								.build();
		
		
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		
		//saved in DB
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee) {
		
		//deleted in DB
		return ResponseEntity.ok("Employee is deleted sucessfully: " + employee.getEmployeeName());
	}

}
