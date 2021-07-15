package com.shridutt.hotel.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
	private int code;
	private String message;

}
