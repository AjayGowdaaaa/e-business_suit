package com.ebs.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1677919032253821965L;

	public NotFoundException (String str) {
		super(str);
	}
}
