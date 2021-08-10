package com.shridutt.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.shridutt")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:dao-application.properties")
@PropertySource("classpath:dao-application-h2.properties")
@PropertySource("classpath:dao-application-mysql.properties")
public class SampleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringBootApplication.class, args);
	}

}
