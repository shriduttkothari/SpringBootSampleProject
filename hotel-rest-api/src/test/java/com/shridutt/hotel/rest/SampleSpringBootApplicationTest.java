package com.shridutt.hotel.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SampleSpringBootApplication.class)
@TestPropertySource("classpath:context-test.properties")
public class SampleSpringBootApplicationTest {

	@Autowired
	private ApplicationContext appContexContext;
	
	/**
	 * THis test just confirms that Spring Application Context can be wired up and started
	 */
	@Test
	public void contextLoads() {
		assertThat(appContexContext).isNotNull();
	}
}
