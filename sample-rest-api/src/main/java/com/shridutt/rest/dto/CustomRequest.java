package com.shridutt.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class CustomRequest {

	private String echo;
}
