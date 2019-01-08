package org.currency.starter.config;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.currency.starter.exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * This class is used to return a 401 unauthorized error to clients that try to
 * access a protected resource without proper authentication. It implements
 * Spring Security’s AuthenticationEntryPoint interface.
 * 
 * @author salah
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Autowired
	private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {

//		ApiError error = new ApiError(HttpStatus.FORBIDDEN, e.getLocalizedMessage(), e.getMessage());

		ErrorMessage error=new ErrorMessage();
    	error.setHttp_status(HttpStatus.FORBIDDEN.toString());
    	error.setMessage(e.getLocalizedMessage());
    	error.setErrorDetails("");
    	error.setErrorDetails("");
    	error.setDate(new Date());
    	
		try {
			String json = jackson2JsonObjectMapper.toJson(error);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.getWriter().write(json);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}