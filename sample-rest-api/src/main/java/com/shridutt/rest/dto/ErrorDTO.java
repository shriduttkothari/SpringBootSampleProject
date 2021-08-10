package com.shridutt.rest.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ErrorDTO {
	
	private String error;
	private UUID errorId;
	private OffsetDateTime timeStamp;

}
