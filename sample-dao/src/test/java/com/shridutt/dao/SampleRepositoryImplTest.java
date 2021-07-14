package com.shridutt.dao;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SampleRepositoryImplTest {

	@InjectMocks
	private SampleRepositoryImpl classUnderTest;

	@Test
	public void testFoo() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> classUnderTest.foo());
	}
}
