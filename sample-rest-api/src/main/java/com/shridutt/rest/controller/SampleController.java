package com.shridutt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.rest.di.Car;
import com.shridutt.rest.di.ElectricEngine;
import com.shridutt.rest.di.PetrolEngine;
import com.shridutt.rest.dto.CustomRequest;
import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;

	private PetrolEngine petrolEngine;
	private ElectricEngine electricEngine;
	
	@RequestMapping(value = "/defaultHello", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<StringResponse> getEcho() {
		
		
		Car altrozCar1 = new Car(petrolEngine);
		Car altrozCar2 = new Car(electricEngine);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		StringResponse stringResponse = sampleService.getStringResponse();
		return ResponseEntity.ok(stringResponse);
	}

	@RequestMapping(value = "/customHello", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<StringResponse> insertEcho(@RequestParam("message") String param1) throws Exception {
		StringResponse stringResponse = sampleService.insertStringResponse(param1);
		return ResponseEntity.ok(stringResponse);
	}
	
	@RequestMapping(value = "/multipleFormatHello", method = RequestMethod.GET, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<StringResponse> getEchoInMultipleFormat() {
		StringResponse stringResponse = sampleService.getStringResponse();
		return ResponseEntity.ok(stringResponse);
	}
	
	@RequestMapping(value = "/multipleFormatCustomHello", method = RequestMethod.POST, 
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<StringResponse> insertEcho(@RequestBody CustomRequest mycustomBody) throws Exception {
		StringResponse stringResponse = sampleService.insertStringResponse(mycustomBody.getEcho());
		return ResponseEntity.ok(stringResponse);
	}
}
