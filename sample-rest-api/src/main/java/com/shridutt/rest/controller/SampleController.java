package com.shridutt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shridutt.rest.dto.StringResponse;
import com.shridutt.rest.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@RequestMapping(value = "/defaultHello", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<StringResponse> getEcho() {
		StringResponse stringResponse = sampleService.getStringResponse();
		return ResponseEntity.ok(stringResponse);
	}

	@RequestMapping(value = "/customHello", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<StringResponse> insertEcho(@RequestParam("message") String param1) throws Exception {
		StringResponse stringResponse = sampleService.insertStringResponse(param1);
		return ResponseEntity.ok(stringResponse);
	}
}
