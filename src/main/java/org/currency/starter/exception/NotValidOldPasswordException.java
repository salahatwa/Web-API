package org.currency.starter.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = NOT_FOUND)
public class NotValidOldPasswordException extends RuntimeException {
	
	public NotValidOldPasswordException(String name) {
		this("Invalid Old Password '%s'", name);
	}

	public NotValidOldPasswordException(String message, Object... args) {
		super(String.format(message, args));
	}
}
