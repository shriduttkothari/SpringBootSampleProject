package com.shridutt.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class StringResponse {

	//@JsonProperty("new_echo")
	private String echo;
}
