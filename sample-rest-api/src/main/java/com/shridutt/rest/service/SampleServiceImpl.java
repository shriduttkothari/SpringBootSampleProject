package com.shridutt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shridutt.dao.repository.SampleRepository;
import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.exception.SampleException;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleRepository sampleRepository;
	
	@Override
	public StringResponse getStringResponse() {
		StringResponse stringResponse = StringResponse.builder().echo("Default Hello World!").build();
		return stringResponse;
	}

	@Override
	public StringResponse insertStringResponse(String param1) throws SampleException {
		if ("invalid_value" == param1) {
			throw new SampleException();
		}

		StringResponse stringResponse = StringResponse.builder().echo("Custom " + param1).build();
		return stringResponse;
	}

	@Override
	public StringResponse foo() {
		int foo = sampleRepository.foo();
		return new StringResponse("Value of is foo is: " + foo);
	}
}
