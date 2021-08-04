package com.shridutt.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@GetMapping
	public ResponseEntity<String> getEmployee(@RequestParam("employeeId") int employeeNumber) {
		
		return ResponseEntity.ok("Employee "+ employeeNumber);
	}

}
