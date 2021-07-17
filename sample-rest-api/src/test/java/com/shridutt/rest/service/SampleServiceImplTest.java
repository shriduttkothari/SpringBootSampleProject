package com.shridutt.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.exception.SampleException;

import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class SampleServiceImplTest {

	@InjectMocks
	private SampleServiceImpl classUnderTest;

	@Test
	public void test_get_string_response() {
		StringResponse actual = classUnderTest.getStringResponse();

		assertThat(actual.getEcho()).isEqualTo("Default Hello World!");
	}

	@SneakyThrows
	@Test
	public void test_insert_string_response() {
		StringResponse actual = classUnderTest.insertStringResponse("Text");

		assertThat(actual.getEcho()).isEqualTo("Custom Text");
	}

	@SneakyThrows
	@Test
	public void test_insert_string_response_throws_sample_exception() {
		
		assertThatExceptionOfType(SampleException.class)
				.isThrownBy(() -> classUnderTest.insertStringResponse("invalid_value"))
				.withMessage("Sample Exception Occured !!");
	}
}
