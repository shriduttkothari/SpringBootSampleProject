package com.shridutt.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class SampleRepositoryImpl implements SampleRepository {

	@Override
	public int foo() {
		throw new UnsupportedOperationException();
	}

}
