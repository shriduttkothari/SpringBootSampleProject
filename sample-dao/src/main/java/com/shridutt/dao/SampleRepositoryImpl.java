package com.shridutt.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SampleRepositoryImpl implements SampleRepository {

	@Override
	public int foo() {
		throw new UnsupportedOperationException();
	}

}
