package com.shridutt.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.shridutt.dao.exception.CarNotFoundException;
import com.shridutt.rest.dto.CarDTO;
import com.shridutt.rest.service.CarService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarService carService;

	@Test
	public void test_get_endpoint_empty() throws Exception {
		when(carService.getCarByCarType("petrol")).thenReturn(new ArrayList<>());
		
		RequestBuilder operation = get("/car?cartype=electric");
		ResultActions resultActions = this.mockMvc.perform(operation);
		
		resultActions.andDo(print());
		resultActions.andExpect(status().is(200));
	}
	
	@Test
	public void test_get_endpoint_valid_body() throws Exception {
		List<CarDTO> carDTOList = new ArrayList<CarDTO>();
		CarDTO carDTO = new CarDTO();
		carDTO.setEngineType("petrol");
		carDTO.setName("Altroz");
		carDTOList.add(carDTO);
		
		when(carService.getCarByCarType("petrol")).thenReturn(carDTOList);
		
		RequestBuilder operation = get("/car?cartype=petrol")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions resultActions = this.mockMvc.perform(operation);
		
		resultActions.andDo(print());
		resultActions.andExpect(status().is(200));
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(jsonPath("[0].engineType").value("petrol"));
		resultActions.andExpect(jsonPath("[0].name").value("Altroz"));
	}
	
	
	@Test
	public void test_get_endpoint_invalid_body_500() throws Exception {
		
		when(carService.getCarByCarType("petrol")).thenThrow(new NullPointerException());
		
		RequestBuilder operation = get("/car?cartype=petrol")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions resultActions = this.mockMvc.perform(operation);
		
		resultActions.andDo(print());
		resultActions.andExpect(status().is(500));
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(jsonPath("timeStamp").exists());
		resultActions.andExpect(jsonPath("errorId").exists());
		resultActions.andExpect(jsonPath("error").value("Internal Server Error, Something went wrong on server!!"));
	}
	
	@Test
	public void test_get_endpoint_invalid_body_404() throws Exception {
		
		when(carService.getCarByCarType("petrol")).thenThrow(new CarNotFoundException());
		
		RequestBuilder operation = get("/car?cartype=petrol")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions resultActions = this.mockMvc.perform(operation);
		
		resultActions.andDo(print());
		resultActions.andExpect(status().is(404));
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(jsonPath("timeStamp").exists());
		resultActions.andExpect(jsonPath("errorId").exists());
		resultActions.andExpect(jsonPath("error").value("Car Not Found In Database !!"));
	}
	
	@Test
	public void test_post_endpoint_() throws Exception {
		CarDTO carDTO = new CarDTO();
		carDTO.setEngineType("petrol");
		carDTO.setName("altroz");
		
		when(carService.save(carDTO)).thenReturn(carDTO);
		
		RequestBuilder operation = post("/car").content("{\r\n"
				+ "    \"name\": \"altroz\",\r\n"
				+ "    \"engineType\": \"petrol\"\r\n"
				+ "}")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions resultActions = this.mockMvc.perform(operation);
		
		resultActions.andDo(print());
		resultActions.andExpect(status().is(200));
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(jsonPath("engineType").value("petrol"));
		resultActions.andExpect(jsonPath("name").value("altroz"));
	}
}
