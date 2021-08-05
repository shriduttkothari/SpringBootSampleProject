package com.shridutt.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {

	@JsonProperty("employeeFullName")
	private String employeeName;
	private String employeeSurname;
	private Integer employeeId;
	private String employeeDepartment;
	
}
