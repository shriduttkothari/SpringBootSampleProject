package com.shridutt.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shridutt.rest.controller.SampleController;
import com.shridutt.rest.dto.ErrorDTO;

import lombok.AllArgsConstructor;

@ControllerAdvice(basePackageClasses = SampleController.class)
@AllArgsConstructor
public class SampleExceptionHandler {

	@ExceptionHandler(SampleException.class)
	public @ResponseBody ResponseEntity<ErrorDTO> handleSampleException(SampleException ex) {
		ErrorDTO errorResponse = new ErrorDTO();
		errorResponse.setError(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
