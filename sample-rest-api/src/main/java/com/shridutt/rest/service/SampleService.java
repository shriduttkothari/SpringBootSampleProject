package com.shridutt.rest.service;

import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.exception.SampleException;

public interface SampleService {

	StringResponse getStringResponse();

	StringResponse insertStringResponse(String param1) throws SampleException;

	StringResponse foo();

}
