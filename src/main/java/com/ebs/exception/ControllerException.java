package com.ebs.exception;

import lombok.Data;

@Data
public class ControllerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7689723270514874053L;
	
	
	private String errorCode;
	private String errorMessage;
	
	public ControllerException(){}
	public ControllerException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
