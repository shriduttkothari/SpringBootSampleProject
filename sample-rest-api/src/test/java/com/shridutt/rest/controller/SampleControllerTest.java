package com.shridutt.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.exception.SampleException;
import com.shridutt.rest.service.SampleService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SampleService sampleService;

	@BeforeAll
	public static void setupBeforeAll() {

	}

	@BeforeEach
	public void setupBeforeEach() {

	}

	@Test
	public void defaultHelloTest() throws Exception {
		when(sampleService.getStringResponse())
				.thenReturn(StringResponse.builder().echo("Default Hello World!").build());

		mockMvc.perform(get("/defaultHello"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.echo").value("Default Hello World!"));
	}

	@Test
	public void defaultHelloWithParamTest() throws Exception {
		when(sampleService.getStringResponse())
				.thenReturn(StringResponse.builder().echo("Default Hello World!").build());

		mockMvc.perform(get("/defaultHello").param("message", "False Message")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.echo").value("Default Hello World!"));
	}

	@Test
	public void customHelloTest() throws Exception {
		when(sampleService.insertStringResponse("Custom Hello World!"))
				.thenReturn(StringResponse.builder().echo("Custom Custom Hello World!").build());

		mockMvc.perform(post("/customHello").param("message", "Custom Hello World!")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.echo").value("Custom Custom Hello World!"));
	}

	@Test
	public void customHelloWithEmptyParamTest() throws Exception {
		when(sampleService.insertStringResponse("")).thenReturn(StringResponse.builder().echo("Custom ").build());

		mockMvc.perform(post("/customHello").param("message", "")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.echo").value("Custom "));
	}

	@Test
	public void customHelloWithoutParamTest() throws Exception {
		mockMvc.perform(post("/customHello")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void customHelloWithException() throws Exception {
		when(sampleService.insertStringResponse("invalid_value")).thenThrow(new SampleException());

		mockMvc.perform(post("/customHello").param("message", "invalid_value")).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").value("Sample Exception Occured !!"));;
	}
}
