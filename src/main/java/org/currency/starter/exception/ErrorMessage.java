package org.currency.starter.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ErrorMessage {
	private String reason;

	private String message;
	
	private String errorDetails;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date Date;

	private String http_status;

	public ErrorMessage() {
	}


	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getHttp_status() {
		return http_status;
	}

	public void setHttp_status(String http_status) {
		this.http_status = http_status;
	}
}