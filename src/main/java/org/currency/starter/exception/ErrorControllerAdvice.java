package org.currency.starter.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {"org.dam.pharm.starter.controller"})
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class ErrorControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            DuplicateKeyException.class,
            DataIntegrityViolationException.class,
            DataAccessException.class,
            GeneralException.class,
            Exception.class
    })
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        LOG.error(e.getMessage(), e);

        if (e instanceof DuplicateKeyException) {
            return handleMessage("40001", e.getMessage());
        }

        if (e instanceof DataIntegrityViolationException) {
            return handleMessage("40002", e.getMessage());
        }

        if (e instanceof DataAccessException) {
            return handleMessage("40003", e.getMessage());
        }
        if (e instanceof GeneralException)
        {
        	return handleMessage("40004", e.getMessage());	
        }
        if(e instanceof NotValidOldPasswordException)
        {
        	return handleMessage("40005", e.getMessage());	
        }

        return handleMessage("40000", e.getMessage());
    }

    private ResponseEntity<ErrorMessage> handleMessage(String code, String message) {
    	ErrorMessage error=new ErrorMessage();
    	error.setHttp_status(code);
    	error.setMessage(message);
    	error.setErrorDetails("");
    	error.setErrorDetails("");
    	error.setDate(new Date());
        return ResponseEntity.badRequest().body(error);
    }
}