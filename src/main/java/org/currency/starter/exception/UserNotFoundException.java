package org.currency.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		this("Could not find User with id '%s'", id);
	}

	public UserNotFoundException(String name) {
		this("Could not find User with name '%s'", name);
	}

	public UserNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}
}