package org.currency.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GeneralException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralException(String message) {
		super(message);
	}
	
	public GeneralException(String message,Throwable throwable) {
		super(message,throwable);
	}

}
