package com.shridutt.rest.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MathSampleTest {

	private static MathSample classUnderTest;

	@BeforeAll
	public static void setUp() {
		classUnderTest = new MathSample();
	}

	@Test
	public void test_do_addition_returns_correct_response() {

		int actualOutput = classUnderTest.doAddition(0, 0);

		assertThat(actualOutput).isEqualTo(0);
	}

	@Test
	public void test_do_divide_returns_coorect_response() {

		int actualOutput = classUnderTest.doDivide(10, 2);

		assertThat(actualOutput).isEqualTo(5);
	}

	@Test
	public void test_do_divide_returns_coorect_response_fraction() {

		int actualOutput = classUnderTest.doDivide(9, 2);

		assertThat(actualOutput).isEqualTo(4);
	}

	@Test
	public void test_do_divide_returns_coorect_response_whenfist_parameter_is_0() {

		int actualOutput = classUnderTest.doDivide(2, 0);

		assertThat(actualOutput).isEqualTo(0);
	}
}
