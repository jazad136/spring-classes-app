package com.jsaddlercs.classapp.exception;

import com.jsaddlercs.classapp.response.ErrorResponse;

public class CLAException extends RuntimeException { 
	
	public final ErrorResponse errorResponse;
	public CLAException(String errorMessage) { 
		super(errorMessage);
		this.errorResponse = new ErrorResponse(errorMessage);
	}
	
	public CLAException(String errorMessage, Throwable cause) { 
		super(errorMessage, cause);
		this.errorResponse = new ErrorResponse(errorMessage);
	}
	
}