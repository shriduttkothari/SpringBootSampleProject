package com.shridutt.rest.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.dao.exception.ConflictException;
import com.shridutt.rest.controller.CarController;
import com.shridutt.rest.dto.ErrorDTO;

@ControllerAdvice(basePackageClasses = CarController.class)
public class CarExceptionHandler {
	
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleCarNotFoundException(CarNotFoundException carNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setErrorId(UUID.randomUUID());
		errorDTO.setError("Car Not Found In Database !!");
		
		ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler(InvalidCarTypeException.class)
	public ResponseEntity<ErrorDTO> handleInvalidCarTypeException(InvalidCarTypeException invalidCarTypeException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setErrorId(UUID.randomUUID());
		errorDTO.setError("CarType is Invalid !!");
		
		ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorDTO> handleConflictException(ConflictException conflictException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setErrorId(UUID.randomUUID());
		errorDTO.setError("Car Already Exists In Database !!");
		
		ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.CONFLICT);
		return responseEntity;
	}
	
}
