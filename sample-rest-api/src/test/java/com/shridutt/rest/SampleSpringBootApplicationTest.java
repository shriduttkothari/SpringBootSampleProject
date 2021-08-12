package com.shridutt.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shridutt.dao.repository.SampleRepositoryImpl;
import com.shridutt.dao.repository.SampleRepositoryImpl2;
import com.shridutt.rest.service.CarServiceImpl;
import com.shridutt.rest.service.SampleServiceImpl;

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
		assertThat(appContexContext.getBean(SampleRepositoryImpl.class)).isNotNull();
		assertThat(appContexContext.getBean(SampleRepositoryImpl2.class)).isNotNull();
		assertThat(appContexContext.getBean(SampleServiceImpl.class)).isNotNull();
		assertThat(appContexContext.getBean(CarServiceImpl.class)).isNotNull();
	}
}
