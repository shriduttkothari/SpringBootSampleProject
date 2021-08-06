package com.shridutt.rest.dto;

import com.shridutt.dao.model.Engine;

import lombok.Data;

@Data
public class CarDTO {
	
	private String name;
	private String engineType;
}
