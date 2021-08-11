package com.shridutt.rest.service;

public class MathSample {
	
	
	public int doAddition(int a, int b) {
		return a+b;
	}
	
	public int doDivide(int a, int b) {
		if(b==0) {
			return 0;
		}
		return a/b;
	}
}
